package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
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
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;

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
	@Inject
	private ApplicationMB applicationMB;
	@EJB
	private CandidatureFacade candidatureFacade;
	private UserEntity userLog;
	private CandidateEntity candLog;
	private String firstName;
	private String lastName;
	private String email;
	// private String candidatureStatus;
	private Date birthdate;
	private String address;
	private String city;
	private String country;
	private String phone;
	private String mobilePhone;
	private String course;
	private String school;
	private String cvPath;
	private String motivationLetter;
	private Date candidatureDate;
	private PositionEntity positionSelect;
	private Source sourcesSelect;

	// private ArrayList<Source>

	public String submitCandidature(UploadFile uploadFile) {
		this.candidatureDate = getCurrentTimeStamp();
		// this.candidatureStatus=;

		if (uploadFile != null) {
			cvPath = uploadFile.generatePath(email);
			System.out.println("cvpath" + cvPath);
			if (motivationLetter != null) {
				if (applicationMB.addCandidature(candLog, positionSelect,
						cvPath, motivationLetter, candidatureDate,
						sourcesSelect) == null) {
					String errorMsg = "Erro during submition of candidature";
					log.error(errorMsg);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									errorMsg, null));

					return "/pages/candidate/NewCandidature";
				} else {

					uploadFile.upload(email);

					String infoMsg = "Successfully created candidature";
					log.info(infoMsg);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									infoMsg, null));
					// return "history.go(-1)";
					return "/pages/candidate/myCandidatures";
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

		// return null;
	}

	// public void findSourceSelect() {
	// ArrayList<Source> allSources=candidatureFacade.fin
	// }

	public String applyToPosition() {
		System.out.println("applytoposition");
		System.out.println(positionSelect.getTitle());
		// System.out.println(positionMB.getPositionSelect());
		// this.positionSelect=positionMB.getPositionSelect();

		if (actUserMB.getCurrentUser() != null) {

			this.userLog = actUserMB.getCurrentUser();
			String errorMsg = "Please do login in or create a new  candidate profile!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
			// System.out.println(actUserMB.getCurrentUser().getEmail());
			return "/LoginCandidates";
		} else if (actUserMB.getCurrentCandidate() != null) {
			this.candLog = actUserMB.getCurrentCandidate();
			// System.out.println(actUserMB.getCurrentCandidate().getEmail());

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

	public static Date getCurrentTimeStamp() {
		// SimpleDateFormat sdfDate = new
		// SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date now = new Date();
		// String strDate = sdfDate.format(now);
		// return strDate;
		return now;
	}

	// Getters and Setters
	public Source getSources() {
		return positionSelect.getSource();
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

	public Source getSourcesSelect() {
		return sourcesSelect;
	}

	public void setSelectSources(Source sourcesSelect) {
		this.sourcesSelect = sourcesSelect;
	}

}
