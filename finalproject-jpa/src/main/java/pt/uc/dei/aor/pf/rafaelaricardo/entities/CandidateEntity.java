package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "candidate")
public class CandidateEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	private RoleEntity role;
	
	@NotNull @NotBlank
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotNull @NotBlank
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotNull @NotBlank @Email
	@Column(nullable = false, unique = true, length = 255)
	private String email;
	
	@NotNull @NotBlank
	@Column(nullable = false, length = 50)
	private String password;
	
	@Temporal(TemporalType.DATE) @Past
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	
	private String address;
	private String city;
	private Long phone;
	private Long mobilePhone;
	private String country;
	private String course;
	private String school;
	private String cvPath;
	private List<CandidatureEntity> candidatures = new ArrayList<>();
	
	// *************************** METHODS ***************************
}
