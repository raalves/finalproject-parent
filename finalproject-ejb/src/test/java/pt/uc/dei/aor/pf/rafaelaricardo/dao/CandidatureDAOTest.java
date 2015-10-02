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
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.CandidatureStatus;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CandidatureDAOTest {

	@Mock
	EntityManager em;

	@Mock
	CandidatureEntity c;

	@Mock
	CandidateEntity cand;

	@Mock
	PositionEntity p;

	@Mock
	Query q;

	@InjectMocks
	CandidatureDAO dao;

	Date date;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		date = new Date();

		c = new CandidatureEntity("cvPath", "motivationLetter", date,
				CandidatureStatus.SUBMITTED);

		cand = new CandidateEntity();
		p = new PositionEntity();

		c.setCandidate(cand);
		c.setPosition(p);
		em.persist(c);
	}

	@Test
	public void testFindAllByOrder() {
		String NAMEDQUERY = "CandidatureEntity.findAllByIdOrder";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidatureEntity> results = dao.findAllByOrder();
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidatureByCandidate() {

		String NAMEDQUERY = "CandidatureEntity.findCandidatureByCandidate";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidatureEntity> results = dao.findCandidatureByCandidate(cand);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("candidate", cand);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindCandidatureByDate() {

		String NAMEDQUERY = "CandidatureEntity.findCandidatureByDate";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidatureEntity> results = dao.findCandidatureByDate(date);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("candidatureDate", date);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}
	
	@Test
	public void testFindCandidatureById() {
		Long id = 1L;
		String NAMEDQUERY = "CandidatureEntity.findCandidatureById";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		CandidatureEntity result = dao.findCandidatureById(id);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("id", id);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}
	
	@Test
	public void testFindCandidatureByPosition() {

		String NAMEDQUERY = "CandidatureEntity.findCandidatureByPosition";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidatureEntity> results = dao.findCandidatureByPosition(p);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("position", p);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}
	
	@Test
	public void testFindCandidatureByStatus() {

		String NAMEDQUERY = "CandidatureEntity.findCandidatureByStatus";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<CandidatureEntity> results = dao.findCandidatureByStatus(CandidatureStatus.SUBMITTED);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("candidatureStatus", CandidatureStatus.SUBMITTED);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}
}
