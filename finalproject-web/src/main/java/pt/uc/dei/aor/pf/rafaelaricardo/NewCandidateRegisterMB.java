package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@SessionScoped
public class NewCandidateRegisterMB implements Serializable {

	private static final long serialVersionUID = -1618159482190255439L;
	private static final Logger log = LoggerFactory
			.getLogger(NewCandidateRegisterMB.class);
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

	@Inject
	private ApplicationMB applicationMB;
	@Inject
	private ActiveUserMB actUserMB;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String repeatPassword;
	private Date birthdate;
	private String address;
	private String city;
	private String country;
	private String phone;
	private String mobilePhone;
	private String course;
	private String school;
	private String cvPath;
	private String coverLetter;

	public NewCandidateRegisterMB() {
	}

	public String newCandidate(UploadFile cvFile) {
		String fileType = "CV";
		String userType = "candidate";
		log.info("Registering new Candidate");
		System.out.println("regist candidate" + birthdate);
		if (email
				.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			if (password.equals(repeatPassword)) {
				if (cvFile != null) {
					cvPath = cvFile.generatePath(email, fileType);
					System.out.println("cvpath" + cvPath);

					if (applicationMB.addCandidate(firstName, lastName, email,
							password, birthdate, address, city,
							Long.parseLong(phone), Long.parseLong(mobilePhone),
							country, course, school, cvPath, coverLetter) == null) {
						String errorMsg = "This email already exists!";
						log.error(errorMsg);
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										errorMsg, null));

						return "/pages/public/NewCandidateRegister";
					} else {

						cvFile.upload(email, fileType, userType);

						String infoMsg = "Successfully created candidate";
						log.info(infoMsg);
						FacesContext.getCurrentInstance().addMessage(
								"messagesLoginCand",
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										infoMsg, null));

						cleanFields();
						return null;
					}

				} else {
					String errorMsg = "Please choose a cv for upload!";
					log.error(errorMsg);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									errorMsg, null));
					return "/pages/public/NewCandidateRegister";
				}

			} else {
				String errorMsg = "Passwords don't match";
				log.error(errorMsg);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
								null));
				return "/pages/public/NewCandidateRegister";
			}
		} else {
			String errorMsg = "This email is not valid!";
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
			return "/pages/public/NewCandidateRegister";
		}
	}

	public void deleteUploadedFile(String filePath) {
		try {

			System.out.println(filePath + "filePath");
			String path = System.getProperty("jboss.home.dir")
					+ "\\ProjFinalUploadedFiles\\" + filePath;
			File file = new File(path);
			file.delete();

			System.out.println(file.getName() + " is deleted!");
		} catch (Exception e) {
			System.out.println("Delete operation is failed.");

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanFields() {
		firstName = null;
		lastName = null;
		email = null;
		password = null;
		repeatPassword = null;
		birthdate = null;
		address = null;
		city = null;
		country = null;
		phone = null;
		mobilePhone = null;
		course = null;
		school = null;
		cvPath = null;
		coverLetter = null;
	}

	// Getters and Setters
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getCvPath() {
		return cvPath;
	}

	public void setCvPath(String cvPath) {
		this.cvPath = cvPath;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

}
