package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "candidate")
@NamedQueries({
		@NamedQuery(name = "CandidateEntity.findCandidateById", query = "SELECT c FROM CandidateEntity c WHERE c.id = :id"),
		@NamedQuery(name = "CandidateEntity.findCandidateByRole", query = "SELECT c FROM CandidateEntity c WHERE c.role = :role"),
		@NamedQuery(name = "CandidateEntity.findCandidateByFirstName", query = "SELECT c FROM CandidateEntity c WHERE c.firstName = :firstName"),
		@NamedQuery(name = "CandidateEntity.findCandidateByLastName", query = "SELECT c FROM CandidateEntity c WHERE c.lastName = :lastName"),
		@NamedQuery(name = "CandidateEntity.findCandidateByEmail", query = "SELECT c FROM CandidateEntity c WHERE c.email = :email"),
		@NamedQuery(name = "CandidateEntity.findCandidateByBirthDate", query = "SELECT c FROM CandidateEntity c WHERE c.birthDate = :birthDate"),
		@NamedQuery(name = "CandidateEntity.findCandidateByAddress", query = "SELECT c FROM CandidateEntity c WHERE c.address = :address"),
		@NamedQuery(name = "CandidateEntity.findCandidateByCity", query = "SELECT c FROM CandidateEntity c WHERE c.city = :city"),
		@NamedQuery(name = "CandidateEntity.findCandidateByMobilePhone", query = "SELECT c FROM CandidateEntity c WHERE c.mobilePhone = :mobilePhone"),
		@NamedQuery(name = "CandidateEntity.findCandidateByCountry", query = "SELECT c FROM CandidateEntity c WHERE c.country = :country"),
		@NamedQuery(name = "CandidateEntity.findCandidateByCourse", query = "SELECT c FROM CandidateEntity c WHERE c.course = :course"),
		@NamedQuery(name = "CandidateEntity.findCandidateBySchool", query = "SELECT c FROM CandidateEntity c WHERE c.school = :school"),
		@NamedQuery(name = "CandidateEntity.findAllByIdOrder", query = "SELECT c FROM CandidateEntity c ORDER BY c.id") })
public class CandidateEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEntity role;

	@NotNull
	@NotBlank
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotNull
	@NotBlank
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotNull
	@NotBlank
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String password;

	@Temporal(TemporalType.DATE)
	@Past
	@Column(name = "birthdate", nullable = false)
	private Date birthDate;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String address;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String city;

	private Long phone;

	@Column(name = "mobile_phone", nullable = false)
	private Long mobilePhone;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String country;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String course;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String school;

	@NotNull
	@NotBlank
	@Column(name = "cv_path", nullable = false)
	private String cvPath;

	@Length(max = 5000)
	private String coverLetter;

	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
	private List<CandidatureEntity> candidatures = new ArrayList<>();

	// ************************ CONSTRUCTORS *************************

	public CandidateEntity() {
		super();
	}

	public CandidateEntity(String firstName, String lastName, String email,
			String password, Date birthDate, String address, String city,
			Long phone, Long mobilePhone, String country, String course,
			String school, String cvPath) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.mobilePhone = mobilePhone;
		this.country = country;
		this.course = course;
		this.school = school;
		this.cvPath = cvPath;
	}

	// *************************** METHODS ***************************
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(Long mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public List<CandidatureEntity> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<CandidatureEntity> candidatures) {
		this.candidatures = candidatures;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateEntity other = (CandidateEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CandidateEntity [id=" + id + ", role=" + role + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", birthDate=" + birthDate + ", address=" + address
				+ ", city=" + city + ", mobilePhone=" + mobilePhone
				+ ", country=" + country + ", course=" + course + ", school="
				+ school + "]";
	}

}
