package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Named
@SessionScoped
public class ActiveUserMB implements Serializable {

	private static final long serialVersionUID = -2924065797543265014L;

	private String email;
	private String fullName;
	private String firstName;
	private String lastName;
	private UserEntity currentUser;
	private CandidateEntity currentCandidate;
	private boolean newUser;
	private boolean adminTab;
	private boolean managerTab;
	private boolean interviewerTab;
	private boolean candidateTab;

	public ActiveUserMB() {
		email = null;
		newUser = false;
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

	public void setFullName(String firstName, String astNames) {
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
		return candidateTab;
	}

	public void setCandidateTab(boolean candidateTab) {
		this.candidateTab = candidateTab;
	}

}
