package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Named
@SessionScoped
public class ApplyMB implements Serializable {

	private static final long serialVersionUID = -3455974751455752818L;
	private static final Logger log = LoggerFactory
			.getLogger(ApplicationMB.class);

	@Inject
	private ActiveUserMB actUserMB;
	@Inject
	private PositionsMB positionMB;

	private UserEntity userLog;
	private CandidateEntity candLog;
	private String firstName;
	private String lastName;
	private String email;

	private Date birthdate;
	private String address;
	private String city;
	private String country;
	private String phone;
	private String mobilePhone;
	private String course;
	private String school;
	private String cvPath;
	private String coverLetterPath;

	private PositionEntity positionSelect;

	public String applyToPosition(PositionEntity positionS) {
		System.out.println("applytoposition");
		System.out.println(positionS + "position que vem da web");
		System.out.println(positionMB.getPositionSelect());
		// this.positionSelect=positionMB.getPositionSelect();

		if (actUserMB.getCurrentUser() != null) {

			this.userLog = actUserMB.getCurrentUser();
			String errorMsg = "Please create a new profile!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					"msgNewCandRegister",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
			// System.out.println(actUserMB.getCurrentUser().getEmail());
			return "/pages/public/NewCandidateRegister.xhtml?faces-redirect=true";
		} else if (actUserMB.getCurrentCandidate() != null) {
			this.candLog = actUserMB.getCurrentCandidate();
			// System.out.println(actUserMB.getCurrentCandidate().getEmail());

			return "/template/commonElements/NewCandidature.xhtml?faces-redirect=true";
		} else {
			String errorMsg = "Please create a new profile!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					"msgNewCandRegister",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
			//
			// FacesContext context = FacesContext.getCurrentInstance();
			// HttpServletRequest request = (HttpServletRequest) context
			// .getExternalContext().getRequest();
			// return request.getHeader("Referer");

			// return "window.history.go(-1)";
			return "/pages/public/NewCandidateRegister.xhtml?faces-redirect=true";

		}

	}

	public ActiveUserMB getActUserMB() {
		return actUserMB;
	}

	public void setActUserMB(ActiveUserMB actUserMB) {
		this.actUserMB = actUserMB;
	}

	public UserEntity getUserLog() {
		return userLog;
	}

	public void setUserLog(UserEntity userLog) {
		this.userLog = userLog;
	}

	public CandidateEntity getCandLog() {
		return candLog;
	}

	public void setCandLog(CandidateEntity candLog) {
		this.candLog = candLog;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCvPath() {
		return cvPath;
	}

	public void setCvPath(String cvPath) {
		this.cvPath = cvPath;
	}

	public String getCoverLetterPath() {
		return coverLetterPath;
	}

	public void setCoverLetterPath(String coverLetterPath) {
		this.coverLetterPath = coverLetterPath;
	}

	public PositionEntity getPositionSelect() {
		return positionSelect;
	}

	public void setPositionSelect(PositionEntity positionSelect) {
		this.positionSelect = positionSelect;
	}

}
