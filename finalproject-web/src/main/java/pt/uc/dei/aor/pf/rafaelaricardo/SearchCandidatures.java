package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;

@Named
@RequestScoped
public class SearchCandidatures implements Serializable {

	private static final long serialVersionUID = -6172703602355429115L;
	private static final Logger log = LoggerFactory
			.getLogger(SearchCandidatures.class);

	@EJB
	private CandidateFacade candidateFacade;

	private int id;
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

	private ArrayList<CandidatureEntity> resultList;

	// public SearchCandidatures() {
	// candidateFacade.findAllByOrder();
	// }

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ArrayList<CandidatureEntity> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<CandidatureEntity> resultList) {
		this.resultList = resultList;
	}

}
