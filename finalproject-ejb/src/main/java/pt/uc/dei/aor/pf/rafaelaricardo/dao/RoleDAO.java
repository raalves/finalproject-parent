package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

@Stateless
public class RoleDAO extends GenericDAO<RoleEntity> {

	public RoleDAO() {
		super(RoleEntity.class);
	}

	public void delete(RoleEntity role) {
		super.delete(role.getId(), RoleEntity.class);
	}

	public void save(RoleEntity role) {
		super.save(role);
	}

	public RoleEntity findRoleById(Long id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		List<RoleEntity> list = super.findSomeResults(
				"RoleEntity.findRoleById", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public RoleEntity findRoleByName(Role role) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("role", role);
		List<RoleEntity> list = super.findSomeResults(
				"RoleEntity.findRoleByName", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

}