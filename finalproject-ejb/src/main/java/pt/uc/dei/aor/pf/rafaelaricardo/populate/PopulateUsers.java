package pt.uc.dei.aor.pf.rafaelaricardo.populate;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pt.uc.dei.aor.pf.rafaelaricardo.EncryptPass;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

@Stateless
@LocalBean
public class PopulateUsers implements Serializable {

	private static final long serialVersionUID = 5869673025889342489L;
	private EncryptPass encPass;
	@PersistenceContext(name = "FinalProject")
	private EntityManager em;

	public void populateUsers() {
		ArrayList<RoleEntity> allRoles = new ArrayList<RoleEntity>();
		allRoles.add(new RoleEntity(Role.ADMIN));
		allRoles.add(new RoleEntity(Role.INTERVIEWER));
		allRoles.add(new RoleEntity(Role.MANAGER));
		allRoles.add(new RoleEntity(Role.CANDIDATE));

		ArrayList<RoleEntity> rAdmin = new ArrayList<RoleEntity>();
		rAdmin.add(new RoleEntity(Role.ADMIN));
		ArrayList<RoleEntity> rInterv = new ArrayList<RoleEntity>();
		rAdmin.add(new RoleEntity(Role.INTERVIEWER));

		ArrayList<RoleEntity> rManager = new ArrayList<RoleEntity>();
		rAdmin.add(new RoleEntity(Role.MANAGER));
		ArrayList<RoleEntity> rCandidate = new ArrayList<RoleEntity>();
		rAdmin.add(new RoleEntity(Role.CANDIDATE));

		UserEntity[] users = {
				new UserEntity("admin", "das couves", "admin@gmail.com",
						encPass.encrypt("123"), rAdmin),
				new UserEntity("interv", "das couves", "admin@gmail.com",
						encPass.encrypt("123"), rInterv),
				new UserEntity("manager", "das couves", "admin@gmail.com",
						encPass.encrypt("123"), rManager),
				new UserEntity("candidate", "das couves", "admin@gmail.com",
						encPass.encrypt("123"), rCandidate) };

		for (UserEntity uE : users)
			em.persist(uE);

		for (RoleEntity rl : allRoles)
			em.persist(rl);
	}
}