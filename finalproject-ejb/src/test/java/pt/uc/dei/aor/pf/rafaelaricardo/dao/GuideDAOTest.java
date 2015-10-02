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
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GuideDAOTest {

	@Mock
	EntityManager em;

	@Mock
	GuideEntity g;

	@Mock
	UserEntity u;

	@Mock
	Query q;

	@InjectMocks
	GuideDAO dao;

	Date date;
	String title;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		date = new Date();
		title = "A";

		g = new GuideEntity();

		u = new UserEntity();

		g.setGuideTitle(title);
		g.setAuthor(u);
		em.persist(g);
	}

	@Test
	public void testFindAllByOrder() {
		String NAMEDQUERY = "GuideEntity.findAllByIdOrder";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<GuideEntity> results = dao.findAllByOrder();
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindGuideByAuthor() {

		String NAMEDQUERY = "GuideEntity.findGuideByAuthor";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<GuideEntity> results = dao.findGuideByAuthor(u);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("author", u);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindGuideByDate() {

		String NAMEDQUERY = "GuideEntity.findGuideByDate";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<GuideEntity> results = dao.findGuideByDate(date);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("guideDate", date);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindGuideById() {

		Long id = 1L;
		String NAMEDQUERY = "GuideEntity.findGuideById";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		GuideEntity result = dao.findGuideById(id);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("id", id);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindGuideByTitle() {

		String NAMEDQUERY = "GuideEntity.findGuideByTitle";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<GuideEntity> results = dao.findGuideByTitle(title);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("guideTitle", title);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

}
