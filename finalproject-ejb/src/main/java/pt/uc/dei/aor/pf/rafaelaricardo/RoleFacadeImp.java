package pt.uc.dei.aor.pf.rafaelaricardo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.RoleDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

@Stateless
public class RoleFacadeImp implements RoleFacade {

	private static final Logger log = LoggerFactory
			.getLogger(RoleFacadeImp.class);
	@EJB
	private RoleDAO roleDAO;

	@Override
	public RoleEntity findRoleById(Long id) {
		log.info("Finding role by id : " + id);
		return roleDAO.findRoleById(id);
	}

	@Override
	public RoleEntity findRoleByName(Role role) {
		log.info("Finding role by name : " + role);
		return roleDAO.findRoleByName(role);
	}

}