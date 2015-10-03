package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Date;
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

import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
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

	@EJB
	private GuideFacade guideFacade;
	private DownloadFile dwnlFile;

	private UserEntity actUser;
	private List<InterviewEntity> resultList;
	private InterviewEntity interviewSelect;
	private String feedback;
	private InterviewStatus statusSelect;
	private String downloadName;
	private String path;
	private boolean render;

	@PostConstruct
	public void listMyInterviewers() {
		render = false;

		this.actUser = actUserMB.getCurrentUser();
		log.info("List all interviewes for interviewers" + actUser);

		try {
			resultList = interviewFacade.findInterviewByUser(actUser);
		} catch (EJBException e) {
			String errorMsg = "Error gettin interviews: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public void downloadGuide(InterviewEntity interviewSelect) {
		this.interviewSelect = interviewSelect;
		this.downloadName = "Guide Complete_"
				+ this.interviewSelect.getCandidature().getCandidate()
				.getFirstName()
				+ this.interviewSelect.getCandidature().getCandidate()
				.getLastName() + ".xlsx";
		this.path = interviewSelect.getCandidature().getPosition().getGuide()
				.getFilePath();
	}

	public void uploadGuideComplete(UploadFile file) {
		log.info("Uploading guide complete for interview of the candidate: "
				+ interviewSelect.getCandidature().getCandidate().getEmail());
		String fileType = "GuideComplete";
		String userType = "interviewer";
		String candidateName = interviewSelect.getCandidature().getCandidate()
				.getFirstName()
				+ interviewSelect.getCandidature().getCandidate().getLastName();
		if (file != null) {
			String guidePath = file.generatePath(candidateName, fileType);

			GuideEntity guideComplete = guideFacade.addGuide(
					actUserMB.getCurrentUser(), "Guide for interview: "
							+ interviewSelect.getId(), new Date(), guidePath);
			if (guideComplete != null) {

				if (interviewFacade.updateGuideCompletePath(interviewSelect,
						guideComplete)) {
					file.upload(candidateName, fileType, userType);

					String infoMsg = "Guide complete upload with sucess";
					log.info(infoMsg);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									infoMsg, null));
				}
			} else {
				String errorMsg = "Error uploading the guide complete!";
				log.error(errorMsg);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
								null));
			}
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

	public void renderButtonGuideComplet() {
		if (interviewSelect.getGuideComplete().getFilePath() != null) {
			render = true;
		} else {
			render = false;
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

	public UserEntity getActUser() {
		return actUser;
	}

	public void setActUser(UserEntity actUser) {
		this.actUser = actUser;
	}

	public DownloadFile getDwnlFile() {
		return dwnlFile;
	}

	public void setDwnlFile(DownloadFile dwnlFile) {
		this.dwnlFile = dwnlFile;
	}

	public String getDownloadName() {
		return downloadName;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

}
