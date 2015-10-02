package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PositionDAOTest {

	@Mock
	EntityManager em;

	@Mock
	PositionEntity position;

	@Mock
	GuideEntity guide;

	@Mock
	UserEntity user;

	@Mock
	Query q;

	@InjectMocks
	PositionDAO dao;

	Date date;
	String string;
	Set<Location> locations = new HashSet<Location>();
	Set<Source> sources = new HashSet<Source>();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		date = new Date();
		string = "qqcoisa";
		locations.add(Location.COIMBRA);
		sources.add(Source.CRITICAL_SOFTWARE_WEBSITE);

		position = new PositionEntity();

		user = new UserEntity();
		guide = new GuideEntity();

		position.setAdminCreator(user);
		position.setClosingDate(date);
		position.setCompany(string);
		position.setGuide(guide);
		position.setLocation(locations);
		position.setManager(user);
		position.setOpenningDate(date);
		position.setPositionStatus(PositionStatus.OPEN);
		position.setQuantity(1);
		position.setSla(1);
		position.setSource(sources);
		position.setTechnicalArea(TechnicalArea.JAVA_DEVELOPMENT);
		position.setTitle(string);

		em.persist(position);
	}

	@Test
	public void testFindAllByOrder() {
		String NAMEDQUERY = "PositionEntity.findAllByIdOrder";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findAllByOrder();
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByAdminCreator() {

		String NAMEDQUERY = "PositionEntity.findPositionByAdminCreator";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionByAdminCreator(user);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("adminCreator", user);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByClosingDate() {

		String NAMEDQUERY = "PositionEntity.findPositionByClosingDate";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionByClosingDate(date);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("closingDate", date);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByCompany() {

		String NAMEDQUERY = "PositionEntity.findPositionByCompany";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionByCompany(string);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("company", string);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByGuide() {

		String NAMEDQUERY = "PositionEntity.findPositionByGuide";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionByGuide(guide);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("guide", guide);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionById() {

		String NAMEDQUERY = "PositionEntity.findPositionById";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		PositionEntity result = dao.findPositionById(1L);
		when(q.getSingleResult()).thenReturn(result);

		verify(q).getResultList();
		verify(q).setParameter("id", 1L);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByLocation() {

		String NAMEDQUERY = "PositionEntity.findPositionByLocation";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao
				.findPositionByLocation(Location.COIMBRA);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("location", Location.COIMBRA);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByManager() {

		String NAMEDQUERY = "PositionEntity.findPositionByManager";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionByManager(user);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("manager", user);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByOpenningDate() {

		String NAMEDQUERY = "PositionEntity.findPositionByOpenningDate";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionByOpenningDate(date);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("openningDate", date);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByPositionStatus() {

		String NAMEDQUERY = "PositionEntity.findPositionByPositionStatus";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao
				.findPositionByPositionStatus(PositionStatus.OPEN);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("positionStatus", PositionStatus.OPEN);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByQuantity() {

		String NAMEDQUERY = "PositionEntity.findPositionByQuantity";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionByQuantity(1);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("quantity", 1);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionBySla() {

		String NAMEDQUERY = "PositionEntity.findPositionBySLA";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionBySLA(1);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("sla", 1);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionBySource() {

		String NAMEDQUERY = "PositionEntity.findPositionBySource";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao
				.findPositionBySource(Source.CRITICAL_SOFTWARE_WEBSITE);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("source", Source.CRITICAL_SOFTWARE_WEBSITE);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByTechnicalArea() {

		String NAMEDQUERY = "PositionEntity.findPositionByTechnicalArea";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao
				.findPositionByTechnicalArea(TechnicalArea.JAVA_DEVELOPMENT);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("technicalArea", TechnicalArea.JAVA_DEVELOPMENT);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}

	@Test
	public void testFindPositionByTitle() {

		String NAMEDQUERY = "PositionEntity.findPositionByTitle";
		when(em.createNamedQuery(NAMEDQUERY)).thenReturn(q);

		List<PositionEntity> results = dao.findPositionByTitle(string);
		when(q.getResultList()).thenReturn(results);

		verify(q).getResultList();
		verify(q).setParameter("title", string);
		verify(em).createNamedQuery(NAMEDQUERY);

		System.out.println("Checked successfully query " + NAMEDQUERY + ".");
	}
}
