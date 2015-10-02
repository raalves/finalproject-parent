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

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CandidateDAOTest {

	@Mock
	EntityManager em;

	@Mock
	CandidateEntity c;

	@Mock
	Query q;

	@InjectMocks
	CandidateDAO dao;

	Date birthDate;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		birthDate = new Date();

		c = new CandidateEntity("Ricardo", "Alves", "ricardo@mail.com", "123",
				birthDate, "Rua Principal", "Sertã", 274274274L, 966396887L,
				"Portugal", "Bioquímico", "FCUL", "cvPath");

		em.persist(c);
	}

	@Test
	public void testFindAllByOrder() {
		String NAMEDQUERY = "CandidateEntity.findAllByIdOrder";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findAllByOrder();
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByAddress() {
		String address = "Rua Principal";
		String NAMEDQUERY = "CandidateEntity.findCandidateByAddress";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findCandidateByAddress(address);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("address", address);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByBirthDate() {
		String NAMEDQUERY = "CandidateEntity.findCandidateByBirthDate";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findCandidateByBirthDate(birthDate);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("birthDate", birthDate);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByCity() {
		String city = "Sertã";
		String NAMEDQUERY = "CandidateEntity.findCandidateByCity";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findCandidateByCity(city);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("city", city);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByCountry() {
		String country = "Portugal";
		String NAMEDQUERY = "CandidateEntity.findCandidateByCountry";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findCandidateByCountry(country);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("country", country);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByCourse() {
		String course = "Bioquímico";
		String NAMEDQUERY = "CandidateEntity.findCandidateByCourse";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findCandidateByCourse(course);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("course", course);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByEmail() {
		String email = "ricardo@mail.com";
		String NAMEDQUERY = "CandidateEntity.findCandidateByEmail";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		CandidateEntity result = dao.findCandidateByEmail(email);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("email", email);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByFirstName() {
		String firstName = "Ricardo";
		String NAMEDQUERY = "CandidateEntity.findCandidateByFirstName";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findCandidateByFirstName(firstName);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("firstName", firstName);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateById() {
		Long id = 1L;
		String NAMEDQUERY = "CandidateEntity.findCandidateById";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		CandidateEntity result = dao.findCandidateById(id);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("id", id);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByLastName() {
		String lastName = "Alves";
		String NAMEDQUERY = "CandidateEntity.findCandidateByLastName";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findCandidateByLastName(lastName);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("lastName", lastName);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateByMobilePhone() {
		Long mobilePhone = 966396887L;
		String NAMEDQUERY = "CandidateEntity.findCandidateByMobilePhone";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao
				.findCandidateByMobilePhone(mobilePhone);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("mobilePhone", mobilePhone);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidateBySchool() {
		String school = "FCUL";
		String NAMEDQUERY = "CandidateEntity.findCandidateBySchool";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidateEntity> results = dao.findCandidateBySchool(school);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("school", school);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

}
