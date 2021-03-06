package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

public interface RoleFacade {

	public abstract RoleEntity findRoleById(Long id);

	public abstract RoleEntity findRoleByName(Role role);

	public abstract List<RoleEntity> findAllByIdOrder();
}
