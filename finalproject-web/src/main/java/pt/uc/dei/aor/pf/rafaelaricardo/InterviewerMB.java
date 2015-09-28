package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.InterviewStatus;

@ManagedBean
@ViewScoped
public class InterviewerMB implements Serializable {

	private static final long serialVersionUID = 3941279875640157898L;
	private static final Logger log = LoggerFactory
			.getLogger(InterviewerMB.class);

	@Inject
	private ActiveUserMB actUserMB;
	@EJB
	private InterviewFacade interviewFacade;
	@EJB
	private UserFacade userFacade;

	private UserEntity actUser;
	private List<InterviewEntity> resultList;
	private InterviewEntity interviewSelect;
	private String feedback;

	private InterviewStatus statusSelect;

	@PostConstruct
	public void listMyInterviewers() {

		this.actUser = actUserMB.getCurrentUser();
		log.info("List all interviewes for interviewers" + actUser);

		try {
			resultList = interviewFacade.findInterviewByUser(actUser);
			System.out.println(resultList);
		} catch (EJBException e) {
			String errorMsg = "Error gettin interviews: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public void submitFeedback() {
		log.info("Submit feedback for position: "
				+ interviewSelect.getCandidature().getPosition().getTitle()
				+ " from interviewer " + actUser.getEmail());
		if (interviewFacade.updateFeedbackStatus(interviewSelect, feedback,
				statusSelect)) {
			String msg = "Interviw feedback and status update";
			log.info(msg);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		} else {
			String errorMsg = "An error ocurred when updating feedback/status.";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	// Getters and Setters
	public InterviewStatus[] getStatusInterview() {
		return InterviewStatus.values();
	}

	public List<InterviewEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<InterviewEntity> resultList) {
		this.resultList = resultList;
	}

	public InterviewEntity getInterviewSelect() {
		return interviewSelect;
	}

	public void setInterviewSelect(InterviewEntity interviewSelect) {
		this.interviewSelect = interviewSelect;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public InterviewStatus getStatusSelect() {
		return statusSelect;
	}

	public void setStatusSelect(InterviewStatus statusSelect) {
		this.statusSelect = statusSelect;
	}

}
