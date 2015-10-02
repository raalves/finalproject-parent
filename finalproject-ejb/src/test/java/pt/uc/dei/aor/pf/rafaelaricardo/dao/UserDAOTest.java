package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.Date;
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

import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

	@Mock
	EntityManager em;

	@Mock
	UserEntity user;

	@Mock
	UserEntity creator;

	@Mock
	Query q;

	@InjectMocks
	UserDAO dao;

	String string;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		string = "string";

		user = new UserEntity();
		creator = new UserEntity();

		user.setCreator(creator);
		user.setEmail(string);
		user.setFirstName(string);
		user.setLastName(string);

		em.persist(user);
	}

	@Test
	public void testFindAllByOrder() {
		String NAMEDQUERY = "UserEntity.findAllByIdOrder";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<UserEntity> results = dao.findAllByOrder();
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindUserByCreator() {

		String NAMEDQUERY = "UserEntity.findUserByCreator";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<UserEntity> results = dao.findUserByCreator(creator);
		when(q.getSingleResult()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("creator", creator);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindUserByEmail() {

		String NAMEDQUERY = "UserEntity.findUserByEmail";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		UserEntity result = dao.findUserByEmail(string);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("email", string);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindUserByFirstName() {

		String NAMEDQUERY = "UserEntity.findUserByFirstName";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<UserEntity> results = dao.findUserByFirstName(string);
		when(q.getSingleResult()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("firstName", string);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindUserById() {

		Long id = 1L;
		String NAMEDQUERY = "UserEntity.findUserById";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		UserEntity result = dao.findUserById(id);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("id", id);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindUserByLastName() {

		String NAMEDQUERY = "UserEntity.findUserByLastName";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<UserEntity> results = dao.findUserByLastName(string);
		when(q.getSingleResult()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("lastName", string);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindUserStartingBy() {

		String NAMEDQUERY = "UserEntity.findUserStartingBy";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<UserEntity> results = dao.findUserStartingBy("str");
		when(q.getSingleResult()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("firstName", "str");
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

}
