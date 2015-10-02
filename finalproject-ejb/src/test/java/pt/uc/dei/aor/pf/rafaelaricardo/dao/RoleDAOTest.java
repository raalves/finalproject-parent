package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleDAOTest {

	@Mock
	EntityManager em;

	@Mock
	RoleEntity role;

	@Mock
	Query q;

	@InjectMocks
	RoleDAO dao;

	Role name;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		name = Role.ADMIN;

		role = new RoleEntity();

		role.setRole(name);
		em.persist(role);
	}

	@Test
	public void testFindAllByOrder() {
		String NAMEDQUERY = "RoleEntity.findAllByIdOrder";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<RoleEntity> results = dao.findAllByOrder();
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindRoleById() {

		Long id = 1L;
		String NAMEDQUERY = "RoleEntity.findRoleById";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		RoleEntity result = dao.findRoleById(id);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("id", id);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindRoleByName() {

		String NAMEDQUERY = "RoleEntity.findRoleByName";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		RoleEntity result = dao.findRoleByName(name);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("role", name);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

}
