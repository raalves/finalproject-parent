package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "utilizador")
@NamedQueries({
	@NamedQuery(name = "UserEntity.findUserById", query = "SELECT u FROM UserEntity u WHERE u.id = :id"),
	@NamedQuery(name = "UserEntity.findUserByCreator", query = "SELECT u FROM UserEntity u WHERE u.creator = :creator"),
	@NamedQuery(name = "UserEntity.findUserByFirstName", query = "SELECT u FROM UserEntity u WHERE u.firstName = :firstName"),
	@NamedQuery(name = "UserEntity.findUserByLastName", query = "SELECT u FROM UserEntity u WHERE u.lastName = :lastName"),
	@NamedQuery(name = "UserEntity.findUserByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
	@NamedQuery(name = "UserEntity.findUserStartingBy", query = "SELECT u FROM UserEntity u WHERE u.firstName like :exp ORDER BY u.firstName"),
	@NamedQuery(name = "UserEntity.findAllByIdOrder", query = "SELECT u FROM UserEntity u ORDER BY u.id") })
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;

	private UserEntity creator;

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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_realizes_interview", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "interview_id"))
	private List<InterviewEntity> interviews = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "user_acts_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> roles = new ArrayList<>();

	@OneToMany(mappedBy = "adminCreator", cascade = CascadeType.ALL)
	private List<PositionEntity> positionsCreated = new ArrayList<>();

	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	private List<PositionEntity> positionsManaged = new ArrayList<>();

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<GuideEntity> guidesCreated = new ArrayList<>();

	// ************************ CONSTRUCTORS *************************

	public UserEntity() {
		super();
	}

	public UserEntity(String firstName, String lastName, String email,
			String password, ArrayList<RoleEntity> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	// *************************** METHODS ***************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(UserEntity creator) {
		this.creator = creator;
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

	public List<InterviewEntity> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<InterviewEntity> interviews) {
		this.interviews = interviews;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public List<PositionEntity> getPositionsCreated() {
		return positionsCreated;
	}

	public void setPositionsCreated(List<PositionEntity> positionsCreated) {
		this.positionsCreated = positionsCreated;
	}

	public List<PositionEntity> getPositionsManaged() {
		return positionsManaged;
	}

	public void setPositionsManaged(List<PositionEntity> positionsManaged) {
		this.positionsManaged = positionsManaged;
	}

	public List<GuideEntity> getGuidesCreated() {
		return guidesCreated;
	}

	public void setGuidesCreated(List<GuideEntity> guidesCreated) {
		this.guidesCreated = guidesCreated;
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
		UserEntity other = (UserEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return email;
	}

}
