package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@Named
@SessionScoped
public class ApplyMB implements Serializable {

	private static final long serialVersionUID = -3455974751455752818L;
	private static final Logger log = LoggerFactory
			.getLogger(ApplicationMB.class);

	@Inject
	private ActiveUserMB actUserMB;

	@Inject
	private ApplicationMB applicationMB;
	@EJB
	private CandidatureFacade candidatureFacade;
	@Inject
	private WriteEmails writeEmails;
	@Inject
	private EnvioMail envioMail;

	private String cvPath;
	private String motivationLetter;
	private Date candidatureDate;
	private CandidatureEntity candidature;

	private PositionEntity positionSelect;

	private List<String> sourcesSelectWEB = new ArrayList<String>();
	private Set<Source> sourcesSelect = new HashSet<Source>();

	private List<Location> selectCities = new ArrayList<Location>();
	private List<TechnicalArea> selectTechAreas = new ArrayList<TechnicalArea>();

	public void transformStringInSources() {
		Source[] allSources = Source.values();
		for (Source s : allSources) {
			for (String sweb : sourcesSelectWEB) {
				if (s.name().equals(sweb)) {
					sourcesSelect.add(s);
				}
			}
		}
	}

	public String submitSpontCandidature(UploadFile uploadFile) {
		log.info("Creating a spontaneous candidature");
		this.positionSelect = null;
		this.sourcesSelectWEB.clear();
		return submitCandidature(uploadFile);
	}

	public String submitCandidature(UploadFile uploadFile) {
		this.candidatureDate = new Date();
		String fileType = "CV";
		String userType = "candidate";
		if (sourcesSelectWEB != null) {
			transformStringInSources();
		}

		if (uploadFile != null) {
			cvPath = uploadFile.generatePath(actUserMB.getCurrentCandidate()
					.getEmail(), fileType);
			if (motivationLetter != null) {

				candidature = applicationMB.addCandidature(
						actUserMB.getCurrentCandidate(), positionSelect,
						cvPath, motivationLetter, candidatureDate,
						sourcesSelect);
				if (candidature == null) {

					String errorMsg = "Error during submition of candidature/Allready have a candidature for this position";
					log.error(errorMsg);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									errorMsg, null));
					return "/pages/candidate/NewCandidature";
				} else {
					uploadFile.upload(actUserMB.getCurrentCandidate()
							.getEmail(), fileType, userType);
					cleanFields();
					String infoMsg = "Successfully created candidature";
					log.info(infoMsg);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									infoMsg, null));
					sendNotifications();
					return "/pages/candidate/CandidatePage.xhtml";

				}

			} else {
				String errorMsg = "Please write your motivation letter!";
				log.error(errorMsg);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
								null));
				return "/pages/public/NewCandidateRegister";
			}
		} else {
			String errorMsg = "Please choose a cv for upload!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
			return "/pages/public/NewCandidateRegister";
		}

	}

	public String applyToPosition() {
		if (actUserMB.getCurrentUser() != null) {
			String errorMsg = "Please do login in or create a new  candidate profile!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
			return "/LoginCandidates";
		} else if (actUserMB.getCurrentCandidate() != null) {
			return "/pages/candidate/NewCandidature.xhtml?faces-redirect=true";
		} else {
			String errorMsg = "Please do login in or create a new  candidate profile!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					"msgNewCandRegister",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
			return "/LoginCandidates";
		}
	}

	public void sendNotifications() {
		try {
			// send notification email to manager of position
			String mail = writeEmails
					.notificationNewCandidatureCandidate(candidature
							.getPosition().getTitle());

			if (mail != null) {
				envioMail.sendMail(candidature.getPosition().getManager()
						.getEmail(), "New Candidature by Candidate", mail,
						candidature.getResumePath(), null);
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

	public void cleanFields() {
		motivationLetter = null;
		selectCities.clear();
		selectTechAreas.clear();
		cvPath = null;
	}

	// Getters and Setters
	public Set<Source> getSources() {
		return positionSelect.getSource();
	}

	public Location[] getCities() {
		return Location.values();
	}

	public TechnicalArea[] getTecAreas() {
		return TechnicalArea.values();
	}

	public ActiveUserMB getActUserMB() {
		return actUserMB;
	}

	public void setActUserMB(ActiveUserMB actUserMB) {
		this.actUserMB = actUserMB;
	}

	public String getCvPath() {
		return cvPath;
	}

	public void setCvPath(String cvPath) {
		this.cvPath = cvPath;
	}

	public String getMotivationLetter() {
		return motivationLetter;
	}

	public void setMotivationLetter(String motivationLetter) {
		this.motivationLetter = motivationLetter;
	}

	public PositionEntity getPositionSelect() {
		return positionSelect;
	}

	public void setPositionSelect(PositionEntity positionSelect) {
		this.positionSelect = positionSelect;
	}

	public Date getCandidatureDate() {
		return candidatureDate;
	}

	public void setCandidatureDate(Date candidatureDate) {
		this.candidatureDate = candidatureDate;
	}

	public Set<Source> getSourcesSelect() {
		return sourcesSelect;
	}

	public void setSourcesSelect(Set<Source> sourcesSelect) {
		this.sourcesSelect = sourcesSelect;
	}

	public List<String> getSourcesSelectWEB() {
		return sourcesSelectWEB;
	}

	public void setSourcesSelectWEB(List<String> sourcesSelectWEB) {
		this.sourcesSelectWEB = sourcesSelectWEB;
	}

	public List<Location> getSelectCities() {
		return selectCities;
	}

	public void setSelectCities(List<Location> selectCities) {
		this.selectCities = selectCities;
	}

	public List<TechnicalArea> getSelectTechAreas() {
		return selectTechAreas;
	}

	public void setSelectTechAreas(List<TechnicalArea> selectTechAreas) {
		this.selectTechAreas = selectTechAreas;
	}

}
