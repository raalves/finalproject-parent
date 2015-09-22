package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.CandidateDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Named
@SessionScoped
public class ActiveUserMB implements Serializable {

	private static final long serialVersionUID = -2924065797543265014L;
	private static final Logger log = LoggerFactory
			.getLogger(ActiveUserMB.class);

	private String email;
	private String fullName;
	private String firstName;
	private String lastName;
	private UserEntity currentUser;
	private CandidateEntity currentCandidate;
	private List<RoleEntity> userRoles;
	private boolean newUser;
	private boolean extraAreas;
	private boolean adminTab;
	private boolean managerTab;
	private boolean interviewerTab;
	private boolean candidateTab;
	@EJB
	private CandidateFacade candidateFacade;

	public ActiveUserMB() {
		email = null;
		newUser = false;
		extraAreas = false;
		adminTab = false;
		managerTab = false;
		interviewerTab = false;
		candidateTab = false;
	}

	public void showCandTabs() {
		newUser = true;
		candidateTab = true;
	}

	public void showTabs() {
		newUser = true;
		if (userRoles.size() <= 1) {
			extraAreas = false;
		} else {
			extraAreas = true;
			for (RoleEntity re : userRoles) {
				System.out.println(re);
				if (re.getRole().toString() == ("ADMIN")) {
					adminTab = true;
				} else if (re.getRole().toString() == ("MANAGER")) {
					managerTab = true;
				} else if (re.getRole().toString() == ("INTERVIEWER")) {
					interviewerTab = true;
				} else if (re.getRole().toString() == ("CANDIDATE")) {
					candidateTab = true;
				}
			}
		}
	}

	public void hideTabs() {
		System.out.println("hide tabs");
		email = null;
		newUser = false;
		extraAreas = false;
		adminTab = false;
		managerTab = false;
		interviewerTab = false;
		candidateTab = false;
	}

	public void changeToLogin() {
		this.newUser = false;
	}

	public void changeToNewUser() {
		this.newUser = true;
	}

	public List<RoleEntity> searchUserRoles() {
		setUserRoles(currentUser.getRoles());
		return userRoles;
	}

	public void submitChanges(UploadFile uploadFile) {
		System.out.println(uploadFile);
		if (uploadFile == null) {
			currentCandidate.setCvPath(currentCandidate.getCvPath());
		} else {
			currentCandidate.setCvPath(uploadFile.generatePath(currentCandidate
					.getEmail()));
		}
		if (candidateFacade.updateCandidateProfile(currentCandidate)) {
			String infomsg = "Profile successfully changed";
			if (uploadFile != null)
				uploadFile.upload(currentCandidate.getEmail());
			log.info(infomsg);
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									infomsg, null));

		} else {
			String errormsg = "Error on changing Profile";
			log.error(errormsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errormsg,
							null));
		}
	}

	// public void areaNameAdmin() {
	// System.out.println("areaadmin");
	// this.areaName = "Administrador";
	// // setAreaName("Administrador");
	// }
	//
	// public void areaNameManager() {
	// System.out.println("areaman");
	// this.areaName = "Manager";
	// // setAreaName("Manager");
	// }
	//
	// public void areaNameInterviewer() {
	// System.out.println("areaInterv");
	// this.areaName = "Interviewer";
	// // setAreaName("Interviewer");
	// }
	//
	// public void areaNameCandidate() {
	// System.out.println("areacand");
	// this.areaName = "Candidate";
	// // setAreaName("Candidate");
	// }

	/********* Getters e Setters ************/
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String firstName, String lastName) {
		this.fullName = this.firstName + " " + this.lastName;
	}

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

	public UserEntity getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserEntity currentUser) {
		this.currentUser = currentUser;
	}

	public CandidateEntity getCurrentCandidate() {
		return currentCandidate;
	}

	public void setCurrentCandidate(CandidateEntity currentCandidate) {
		this.currentCandidate = currentCandidate;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isNewUser() {
		return newUser;
	}

	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}

	public boolean isAdminTab() {
		return adminTab;
	}

	public void setAdminTab(boolean adminTab) {
		this.adminTab = adminTab;
	}

	public boolean isManagerTab() {
		return managerTab;
	}

	public void setManagerTab(boolean managerTab) {
		this.managerTab = managerTab;
	}

	public boolean isInterviewerTab() {
		return interviewerTab;
	}

	public void setInterviewerTab(boolean interviewerTab) {
		this.interviewerTab = interviewerTab;
	}

	public boolean isCandidateTab() {
		// setAreaName("Candidate");
		return candidateTab;
	}

	public void setCandidateTab(boolean candidateTab) {
		this.candidateTab = candidateTab;
	}

	public List<RoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<RoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public boolean isExtraAreas() {
		return extraAreas;
	}

	public void setExtraAreas(boolean extraAreas) {
		this.extraAreas = extraAreas;
	}

}
