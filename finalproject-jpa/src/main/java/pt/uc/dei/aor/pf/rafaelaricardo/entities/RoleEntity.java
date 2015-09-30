package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

@Entity
@Table(name = "role")
@NamedQueries({
	@NamedQuery(name = "RoleEntity.findRoleById", query = "SELECT r FROM RoleEntity r WHERE r.id = :id"),
	@NamedQuery(name = "RoleEntity.findRoleByName", query = "SELECT r FROM RoleEntity r WHERE r.role = :role"),
	@NamedQuery(name = "RoleEntity.findAllByIdOrder", query = "SELECT r FROM RoleEntity r ORDER BY r.id") })
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<UserEntity> users = new ArrayList<>();

	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private List<CandidateEntity> candidates = new ArrayList<>();

	// ************************ CONSTRUCTORS *************************

	public RoleEntity() {
		super();
	}

	public RoleEntity(Role role) {
		super();
		this.role = role;
	}

	// *************************** METHODS ***************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<CandidateEntity> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidateEntity> candidates) {
		this.candidates = candidates;
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
		RoleEntity other = (RoleEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", role=" + role + "]";
	}

}
