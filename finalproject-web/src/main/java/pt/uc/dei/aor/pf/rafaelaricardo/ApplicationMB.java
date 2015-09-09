package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Named
@SessionScoped
public class ApplicationMB implements Serializable {

	private static final long serialVersionUID = -2833088453201071672L;
	private static final Logger log = LoggerFactory
			.getLogger(ApplicationMB.class);

	@EJB
	private CandidateFacade candidateFacade;
	@EJB
	private CandidatureFacade candidatureFacade;
	@EJB
	private GuideFacade guideFacade;
	@EJB
	private InterviewFacade interviewFacade;
	@EJB
	private PositionFacade positionFacade;
	@EJB
	private RoleFacade roleFacade;
	@EJB
	private UserFacade userFacade;

	private String firstName;
	private String lastName;
	private String fullName;
	private String password;
	private String repeatPassword;

	// Construtor
	public ApplicationMB() {
	}

	// User stuff

	public void goToProfile() {

	}

	public UserEntity findUserByEmail(String email) {
		System.out.println("fin user by email");
		try {
			return userFacade.findUserByEmail(email);
		} catch (EJBException e) {
			String errorMsg = "Error finding user by email: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage("messagesLoginEmp",
					new FacesMessage(errorMsg));
		}
		return null;
	}

	public CandidateEntity findCandidateByEmail(String email) {
		System.out.println("find candidate by email");
		try {
			// return userFacade.findUserByEmail(email);
			return candidateFacade.findCandidateByEmail(email);
		} catch (EJBException e) {
			String errorMsg = "Error finding user by email: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage("messagesLoginEmp",
					new FacesMessage(errorMsg));
		}
		return null;
	}

	// Getters and Setters

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

}
