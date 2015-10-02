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

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.InterviewStatus;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InterviewDAOTest {

	@Mock
	EntityManager em;

	@Mock
	InterviewEntity i;

	@Mock
	CandidatureEntity cand;

	@Mock
	UserEntity u;

	@Mock
	Query q;

	@InjectMocks
	InterviewDAO dao;

	Date date;
	InterviewStatus interviewStatus;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		date = new Date();
		interviewStatus = InterviewStatus.SCHEDULED;

		i = new InterviewEntity();

		u = new UserEntity();
		cand = new CandidatureEntity();

		i.setCandidature(cand);
		i.setInterviewStatus(interviewStatus);

		em.persist(i);
	}

	@Test
	public void testFindAllByOrder() {
		String NAMEDQUERY = "InterviewEntity.findAllByIdOrder";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<InterviewEntity> results = dao.findAllByOrder();
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindInterviewByCandidature() {

		String NAMEDQUERY = "InterviewEntity.findInterviewByCandidature";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<InterviewEntity> results = dao.findInterviewByCandidature(cand);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("candidature", cand);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindInterviewByDate() {

		String NAMEDQUERY = "InterviewEntity.findInterviewByDate";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<InterviewEntity> results = dao.findInterviewByDate(date);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("interviewDate", date);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindInterviewById() {

		Long id = 1L;
		String NAMEDQUERY = "InterviewEntity.findInterviewById";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		InterviewEntity result = dao.findInterviewById(id);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("id", id);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindInterviewByStatus() {

		String NAMEDQUERY = "InterviewEntity.findInterviewByStatus";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<InterviewEntity> results = dao
				.findInterviewByStatus(interviewStatus);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("interviewStatus", interviewStatus);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

}
