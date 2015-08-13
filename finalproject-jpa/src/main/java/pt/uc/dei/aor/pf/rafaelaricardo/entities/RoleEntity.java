package pt.uc.dei.aor.pf.rafaelaricardo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

@Entity
@Table(name = "role")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;;

	@Id
	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private Role role;
	private List<UserEntity> users = new ArrayList<>();
	private List<CandidateEntity> candidates = new ArrayList<>();

	// *************************** METHODS ***************************
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
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}
