package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;

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

	public UserEntity addUser(UserEntity creator, String firstName,
			String lastName, String email, String password,
			ArrayList<RoleEntity> roles) {

		try {
			UserEntity u = userFacade.addUser(creator, firstName, lastName,
					email, password, roles);
			return u;
		} catch (EJBException e) {
			String errorMsg = "Error adding user " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(errorMsg));
			return null;
		}
	}

	public InterviewEntity addInterview(CandidatureEntity candidature,
			Date interviewDate, List<UserEntity> interviewers) {

		try {
			InterviewEntity interv = interviewFacade.addInterview(candidature,
					interviewDate);
			if (interv != null) {
				userFacade.addInterviewToUser(interviewers, interv);
			}
			return interv;
		} catch (EJBException e) {
			String errorMsg = "An error ocurred when creating this interview: "
					+ e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					"msgNewInterview",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, e
							.getMessage()));
			return null;
		}
	}

	public CandidatureEntity addCandidature(CandidateEntity candLog,
			PositionEntity positionSelect, String cvPath,
			String motivationLetter, Date candidatureDate,
			Set<Source> sourcesSelect) {

		try {
			CandidatureEntity cand = candidatureFacade.addCandidature(candLog,
					positionSelect, cvPath, motivationLetter, candidatureDate,
					sourcesSelect);
			return cand;
		} catch (EJBException e) {
			String errorMsg = "You already have a candidature for this position: "
					+ e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					"msgNewCand",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, e
							.getMessage()));
			return null;
		}

	}

	public CandidateEntity addCandidate(String firstName, String lastName,
			String email, String password, Date birthdate, String address,
			String city, Long phone, Long mobilePhone, String country,
			String course, String school, String cvPath, String coverLetter) {

		try {
			CandidateEntity c = candidateFacade.addCandidate(firstName,
					lastName, email, password, birthdate, address, city, phone,
					mobilePhone, country, course, school, cvPath, coverLetter);
			return c;
		} catch (EJBException e) {
			String errorMsg = "Error adding candidate: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					"msgNewCand",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, e
							.getMessage()));
			return null;
		}

	}

	public UserEntity findUserByEmail(String email) {
		System.out.println("find user by email");
		try {
			return userFacade.findUserByEmail(email);
		} catch (EJBException e) {
			String errorMsg = "Error finding user by email: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					"messagesLoginEmp",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
		return null;
	}

	public CandidateEntity findCandidateByEmail(String email) {
		System.out.println("find candidate by email");
		try {
			return candidateFacade.findCandidateByEmail(email);
		} catch (EJBException e) {
			String errorMsg = "Error finding user by email: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					"messagesLoginEmp",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
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
