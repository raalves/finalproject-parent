package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Named
@SessionScoped
public class GuidesMB implements Serializable {

	private static final long serialVersionUID = 1823470922454027047L;
	private static final Logger log = LoggerFactory.getLogger(GuidesMB.class);

	@Inject
	private ActiveUserMB actUserMB;
	@Inject
	private GuideFacade guideFacade;

	private String guideName;
	private Date guideDate;
	private UserEntity guideAuthor;
	private String guidePath;
	private List<GuideEntity> resultList;
	private GuideEntity selectGuide;

	@PostConstruct
	public void listAllGuides() {
		log.info("Search for all guides");
		try {
			resultList = guideFacade.findAllByOrder();
		} catch (EJBException e) {
			String errorMsg = "Error searching guides: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public void addGuide(UploadFile file) {
		String fileType = "Guide";
		String userType = "admin";
		this.guideDate = new Date();
		this.guideAuthor = actUserMB.getCurrentUser();
		log.info("Add new Guide: " + guideName);
		if (file != null) {
			guidePath = file.generatePath(guideName, fileType);

			if (guideFacade.addGuide(guideAuthor, guideName, guideDate,
					guidePath) != null) {
				file.upload(guideName, fileType, userType);
				String infoMsg = "Successfully created guide";
				log.info(infoMsg);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, infoMsg,
								null));
				cleanFields();
				listAllGuides();
			} else {
				String errorMsg = "Error adding Guide";
				log.error(errorMsg);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
								null));
			}
		} else {
			String errorMsg = "Please choose a guide for upload!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public void uploadNewFileGuide(UploadFile file) {
		log.info("Upload new file for guide: " + selectGuide.getGuideTitle());
		this.guideDate = new Date();
		this.guideAuthor = actUserMB.getCurrentUser();
		String fileType = "Guide";
		String userType = "admin";
		if (file != null) {
			String guidePath = file.generatePath(selectGuide.getGuideTitle(),
					fileType);
			if (guideFacade.updateFileGuide(selectGuide, guidePath,
					guideAuthor, guideDate)) {
				file.upload(selectGuide.getGuideTitle(), fileType, userType);
				String msg = "File guide update";
				log.info(msg);
				FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										msg, null));

			} else {
				String errorMsg = "An error ocurred when updating file guide.";
				log.error(errorMsg);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
								null));
			}
		} else {
			String errorMsg = "Error uploading the new guide file!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public void cleanFields() {
		guideAuthor = null;
		guideDate = null;
		guideName = null;
		guidePath = null;
	}

	// Getters and Setters
	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public Date getGuideDate() {
		return guideDate;
	}

	public void setGuideDate(Date guideDate) {
		this.guideDate = guideDate;
	}

	public UserEntity getGuideAuthor() {
		return guideAuthor;
	}

	public void setGuideAuthor(UserEntity guideAuthor) {
		this.guideAuthor = guideAuthor;
	}

	public String getGuidePath() {
		return guidePath;
	}

	public void setGuidePath(String guidePath) {
		this.guidePath = guidePath;
	}

	public List<GuideEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<GuideEntity> resultList) {
		this.resultList = resultList;
	}

	public GuideEntity getSelectGuide() {
		return selectGuide;
	}

	public void setSelectGuide(GuideEntity selectGuide) {
		this.selectGuide = selectGuide;
	}

}
