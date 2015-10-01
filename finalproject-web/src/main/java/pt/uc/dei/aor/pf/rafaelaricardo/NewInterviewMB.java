package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Named
@SessionScoped
public class NewInterviewMB implements Serializable {

	private static final long serialVersionUID = -8247524831573252249L;
	private static final Logger log = LoggerFactory
			.getLogger(NewInterviewMB.class);

	private PositionEntity selectPosition;
	private CandidatureEntity selectCandidature;
	private List<UserEntity> selectInterviewer;
	private Date interviewDate;
	private InterviewEntity interview;
	@EJB
	private InterviewFacade interviewFacade;
	@Inject
	private ApplicationMB applicationMB;
	@Inject
	private ActiveUserMB actUserMB;
	@Inject
	private EnvioMail envioMail;
	@Inject
	private WriteEmails writeEmails;

	public void createNewInterview() {
		interviewDate = new Date();
		log.info("Creating a new interview. Candidate: "
				+ selectCandidature.getCandidate().getFirstName() + " "
				+ selectCandidature.getCandidate().getLastName()
				+ " to position " + selectPosition.getTitle());

		interview = applicationMB.addInterview(selectCandidature,
				interviewDate, selectInterviewer);
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
			if (mailInterviewers != null && mailCandidate != null) {
				envioMail.sendMail(selectCandidature.getCandidate().getEmail(),
						"New Interview: information mail", mailCandidate, null,
						null);
				for (UserEntity u : selectInterviewer) {
					envioMail.sendMail(u.getEmail(),
							"New Interview: information mail",
							mailInterviewers, selectCandidature.getCandidate()
									.getCvPath(), selectCandidature
									.getPosition().getGuide().getFilePath());

				}
			}
		} catch (Exception e) {
			String errorMsg = "An error ocurred wwhile sendin notifications: "
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

	public List<UserEntity> getSelectInterviewer() {
		return selectInterviewer;
	}

	public void setSelectInterviewer(List<UserEntity> selectInterviewer) {
		this.selectInterviewer = selectInterviewer;
	}

	public Date getIntervewDate() {
		return interviewDate;
	}

	public void setIntervewDate(Date intervewDate) {
		this.interviewDate = intervewDate;
	}

}
