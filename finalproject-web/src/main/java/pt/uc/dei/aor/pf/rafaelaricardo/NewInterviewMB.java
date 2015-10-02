package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

@ManagedBean
@ViewScoped
public class NewInterviewMB implements Serializable {

	private static final long serialVersionUID = -8247524831573252249L;
	private static final Logger log = LoggerFactory
			.getLogger(NewInterviewMB.class);

	private PositionEntity selectPosition;
	private CandidatureEntity selectCandidature;
	private List<UserEntity> selectInterviewers = new ArrayList<UserEntity>();
	private List<String> selectInterviewersWeb = new ArrayList<String>();

	private List<CandidatureEntity> listCandidatures = new ArrayList<CandidatureEntity>();
	private List<InterviewEntity> listInterviews = new ArrayList<InterviewEntity>();
	private List<UserEntity> allInterviewers = new ArrayList<UserEntity>();

	private Date interviewDate;
	private InterviewEntity interview;
	@EJB
	private InterviewFacade interviewFacade;
	@EJB
	private CandidatureFacade candidatureFacade;
	@EJB
	private RoleFacade roleFacade;
	@Inject
	private ApplicationMB applicationMB;
	@Inject
	private ActiveUserMB actUserMB;
	@Inject
	private EnvioMail envioMail;
	@Inject
	private WriteEmails writeEmails;

	public void listCandidaturesByPosition() {

		log.info("Listing candidatures for position: "
				+ selectPosition.getTitle());
		try {
			listCandidatures = candidatureFacade
					.findCandidatureByPosition(selectPosition);
		} catch (EJBException e) {
			String errorMessage = "Error getting candidatures" + e.getMessage();
			log.error(errorMessage);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
							null));
		}

	}

	public void listInterviewsByCandidature() {
		log.info("Listing interviews for candidatura: "
				+ selectPosition.getTitle());
		try {
			listInterviews = interviewFacade
					.findInterviewByCandidature(selectCandidature);
		} catch (EJBException e) {
			String errorMessage = "Error getting candidatures" + e.getMessage();
			log.error(errorMessage);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
							null));
		}
	}

	public void convertStringInToUser() {
		List<UserEntity> allUserInterv = roleFacade.findRoleByName(
				Role.INTERVIEWER).getUsers();
		for (UserEntity u : allUserInterv) {
			for (String s : selectInterviewersWeb) {
				System.out.println(u.getEmail() + " " + s);
				if (u.getEmail().equals(s)) {
					selectInterviewers.add(u);
				}
			}
		}
	}

	public String createNewInterview() {

		System.out.println(selectInterviewersWeb);
		convertStringInToUser();
		interviewDate = new Date();
		log.info("Creating a new interview. Candidate: "
				+ selectCandidature.getCandidate().getFirstName() + " "
				+ selectCandidature.getCandidate().getLastName()
				+ " to position " + selectPosition.getTitle());

		interview = applicationMB.addInterview(selectCandidature,
				interviewDate, selectInterviewers);
		if (interview == null) {
			String errorMsg = "Error creating interview: " + interview.getId();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));

		} else {
			String infoMsg = "Success on creating interview";
			log.info(infoMsg);
			FacesContext.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							infoMsg, null));
			sendNotifications();
		}
		return null;
	}

	public void sendNotifications() {

		// Send notification email
		try {
			String mailInterviewers = writeEmails.notificationInterv(
					selectCandidature.getPosition().getTitle(),
					interview.getInterviewDate());
			String mailCandidate = writeEmails.notificationCandidate(
					selectCandidature.getPosition().getTitle(),
					selectCandidature.getCandidate().getFirstName(),
					selectCandidature.getCandidate().getLastName(),
					interview.getInterviewDate());
			if (mailCandidate != null) {
				System.out.println("send email to candidate");
				envioMail.sendMail(selectCandidature.getCandidate().getEmail(),
						"New Interview: information mail", mailCandidate, null,
						null);
			}

			if (mailInterviewers != null) {
				System.out.println("send email for interviewers");
				for (UserEntity u : selectInterviewers) {
					System.out.println("send email for interviewers: "
							+ u.getEmail());

					envioMail.sendMail(u.getEmail(),
							"New Interview: information mail",
							mailInterviewers, selectCandidature.getCandidate()
							.getCvPath(), selectCandidature
							.getPosition().getGuide().getFilePath());
				}
			}
		} catch (Exception e) {
			String errorMsg = "An error ocurred while sending notifications: "
					+ e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	// Getters and Setters
	public PositionEntity getSelectPosition() {
		return selectPosition;
	}

	public void setSelectPosition(PositionEntity selectPosition) {
		this.selectPosition = selectPosition;
	}

	public CandidatureEntity getSelectCandidature() {
		return selectCandidature;
	}

	public void setSelectCandidature(CandidatureEntity selectCandidature) {
		this.selectCandidature = selectCandidature;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public InterviewEntity getInterview() {
		return interview;
	}

	public void setInterview(InterviewEntity interview) {
		this.interview = interview;
	}

	public List<CandidatureEntity> getListCandidatures() {
		return listCandidatures;
	}

	public void setListCandidatures(List<CandidatureEntity> listCandidatures) {
		this.listCandidatures = listCandidatures;
	}

	public List<UserEntity> getSelectInterviewers() {
		return selectInterviewers;
	}

	public void setSelectInterviewers(List<UserEntity> selectInterviewers) {
		this.selectInterviewers = selectInterviewers;
	}

	public List<InterviewEntity> getListInterviews() {
		return listInterviews;
	}

	public void setListInterviews(List<InterviewEntity> listInterviews) {
		this.listInterviews = listInterviews;
	}

	public List<UserEntity> getAllInterviewers() {
		return roleFacade.findRoleByName(Role.INTERVIEWER).getUsers();
	}

	public void setAllInterviewers(List<UserEntity> allInterviewers) {
		this.allInterviewers = allInterviewers;
	}

	public List<String> getSelectInterviewersWeb() {
		return selectInterviewersWeb;
	}

	public void setSelectInterviewersWeb(List<String> selectInterviewersWeb) {
		this.selectInterviewersWeb = selectInterviewersWeb;
	}

}
