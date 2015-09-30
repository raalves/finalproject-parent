package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Named
@SessionScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 938085188888717920L;
	private static final Logger log = LoggerFactory.getLogger(LoginMB.class);
	// private FacesContext context = FacesContext.getCurrentInstance();
	// private HttpServletRequest request = (HttpServletRequest) context
	// .getExternalContext().getRequest();

	private String email;
	private String password;
	@Inject
	private ApplicationMB applicationMB;
	@Inject
	private ActiveUserMB actUser;
	@Inject
	private AreaNameMB areaName;
	@Inject
	private ApplyMB applyMB;

	public LoginMB() {
	}

	public String loginEmployees() {
		System.out.println("login");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.login(email, password);
		} catch (ServletException e) {
			log.error(e.getMessage());
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Login failure: wrong email/password!", null));
			return null;
		}
		return doLoginEmployees();
	}

	public String doLoginEmployees() {
		System.out.println("login");

		System.out.println("do login");
		log.info("Doing Login");
		log.info("Doing Login for: " + email);
		UserEntity u = applicationMB.findUserByEmail(email);
		if (u != null) {
			actUser.setCurrentUser(u);
			actUser.setFullName(u.getFirstName() + " " + u.getLastName());
			System.out.println(actUser.getFullName());
			String path = actUser.searchUserRoles().get(0).getRole().toString();
			String pagePath = path.charAt(0) + path.substring(1).toLowerCase();
			actUser.showTabs();
			actUser.seeIfIsAdmin();
			return "/pages/" + path.toLowerCase() + "/" + pagePath
					+ "Page?faces-redirect=true";
		}
		// log.error(e.getMessage());
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Login failure: wrong area login", null));
		logout();
		return "/LoginEmployees?faces-redirect=true";
	}

	public String loginCandidates() {
		System.out.println("login");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			System.out.println(email + " " + password);
			request.login(email, password);
		} catch (ServletException e) {
			log.error(e.getMessage());
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Login failure: wrong email/password!", null));
			return null;
		}
		return doLoginCandidates();
	}

	public String doLoginCandidates() {
		System.out.println("do login");
		log.info("Doing Login");
		log.info("Doing Login for: " + email);
		CandidateEntity c = applicationMB.findCandidateByEmail(email);

		if (c != null) {
			actUser.setCurrentCandidate(c);
			actUser.setFullName(c.getFirstName() + " " + c.getLastName());
			areaName.setAreaName("Candidate");
			actUser.showCandTabs();

			return "/pages/candidate/CandidatePage?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Login failure: wrong area login", null));
		logout();
		return "/LoginCandidates?faces-redirect=true";

	}

	public String doLogout() {
		System.out.println("dologout");
		actUser.hideTabs();
		email = null;

		return "/Home.xhtml?faces-redirect=true";
	}

	public String logout() {
		log.info("Doing logout for user :" + actUser.getEmail());
		System.out.println("logout context");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
			actUser.setCurrentCandidate(null);
			actUser.setCurrentUser(null);

		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Logout failed"));
			return null;
		}
		return doLogout();
	}

	// Getters and Setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ActiveUserMB getActUser() {
		return actUser;
	}

	public void setActUser(ActiveUserMB actUser) {
		this.actUser = actUser;
	}

	public ApplicationMB getApplicationMB() {
		return applicationMB;
	}

	public void setApplicationMB(ApplicationMB applicationMB) {
		this.applicationMB = applicationMB;
	}

}