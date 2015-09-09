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

	public LoginMB() {
	}

	public String loginEmployees() {
		System.out.println("login");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			System.out.println(email + " " + password);
			// System.out.println(request.getParameter(email));
			// System.out.println(request.getParameter(password));
			// System.out.println("Principal "
			// + FacesContext.getCurrentInstance().getExternalContext()
			// .getUserPrincipal());
			// System.out.println("String at "
			// + FacesContext.getCurrentInstance().getExternalContext()
			// .getAuthType());
			// System.out.println("String ru "
			// + FacesContext.getCurrentInstance().getExternalContext()
			// .getRemoteUser());
			// Principal userprincipal = request.getUserPrincipal();
			// if (request.getUserPrincipal() != null) {
			// request.logout();
			// }

			request.login(email, password);

		} catch (ServletException e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage() + " "
					+ e.getStackTrace().toString());
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(
					"Login failure: wrong email/password"));

			return null;
		}

		return doLoginEmployees();
	}

	public String doLoginEmployees() {
		System.out.println("do login");
		log.info("Doing Login");
		log.info("Doing Login for: " + email);

		UserEntity u = applicationMB.findUserByEmail(email);
		if (u != null) {
			applicationMB.setFullName(u.getFirstName() + " " + u.getLastName());
			actUser.setFirstName(u.getFirstName());
			actUser.setLastName(u.getLastName());
			actUser.setCurrentUser(u);
			email = "";
			// ir buscar role para por no caminho da pagina
			// "/pages/"+roleUser+"/"+roleUser+"Page?faces-redirect=true";
			return "/pages/admin/AdminPage?faces-redirect=true";
		}
		return "/LoginEmployees?faces-redirect=true";

	}

	public String loginCandidates() {
		System.out.println("login");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			System.out.println(email + " " + password);
			// System.out.println(request.getParameter(email));
			// System.out.println(request.getParameter(password));
			// System.out.println("Principal "
			// + FacesContext.getCurrentInstance().getExternalContext()
			// .getUserPrincipal());
			// System.out.println("String at "
			// + FacesContext.getCurrentInstance().getExternalContext()
			// .getAuthType());
			// System.out.println("String ru "
			// + FacesContext.getCurrentInstance().getExternalContext()
			// .getRemoteUser());
			// Principal userprincipal = request.getUserPrincipal();
			// if (request.getUserPrincipal() != null) {
			// request.logout();
			// }

			request.login(email, password);

		} catch (ServletException e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage() + " "
					+ e.getStackTrace().toString());
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(
					"Login failure: wrong email/password"));

			return null;
		}

		return doLoginCandidates();
	}

	public String doLoginCandidates() {
		System.out.println("do login");
		log.info("Doing Login");
		log.info("Doing Login for: " + email);
		CandidateEntity c = applicationMB.findCandidateByEmail(email);

		// UserEntity u = applicationMB.findUserByEmail(email);
		if (c != null) {
			applicationMB.setFullName(c.getFirstName() + " " + c.getLastName());
			actUser.setFirstName(c.getFirstName());
			actUser.setLastName(c.getLastName());
			actUser.setCurrentCandidate(c);
			;
			email = "";
			return "/pages/candidate/CandidatePage?faces-redirect=true";
		}
		return "/LoginCandidates?faces-redirect=true";

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