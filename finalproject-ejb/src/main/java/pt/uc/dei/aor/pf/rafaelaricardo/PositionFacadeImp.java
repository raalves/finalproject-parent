package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.PositionDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.DescriptionPosition;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@Stateless
public class PositionFacadeImp implements PositionFacade {

	private static final Logger log = LoggerFactory
			.getLogger(PositionFacadeImp.class);

	@EJB
	private PositionDAO positionDAO;

	@Override
	public PositionEntity findPositionById(Long id) {
		log.info("Finding position by id: " + id);
		return positionDAO.findPositionById(id);
	}

	@Override
	public List<PositionEntity> findPositionByTitle(String title) {
		log.info("Finding position by title: " + title);
		return positionDAO.findPositionByTitle(title);
	}

	@Override
	public List<PositionEntity> findPositionByAdminCreator(
			UserEntity adminCreator) {
		log.info("Finding position by adminCreator: " + adminCreator);
		return positionDAO.findPositionByAdminCreator(adminCreator);
	}

	@Override
	public List<PositionEntity> findPositionByManager(UserEntity manager) {
		log.info("Finding position by manager: " + manager);
		return positionDAO.findPositionByManager(manager);
	}

	@Override
	public List<PositionEntity> findPositionByGuide(GuideEntity guide) {
		log.info("Finding position by guide: " + guide);
		return positionDAO.findPositionByGuide(guide);
	}

	@Override
	public List<PositionEntity> findPositionByLocation(Location location) {
		log.info("Finding position by location: " + location);
		return positionDAO.findPositionByLocation(location);
	}

	@Override
	public List<PositionEntity> findPositionByPositionStatus(
			PositionStatus positionStatus) {
		log.info("Finding position by positionStatus: " + positionStatus);
		return positionDAO.findPositionByPositionStatus(positionStatus);
	}

	@Override
	public List<PositionEntity> findPositionByQuantity(int quantity) {
		log.info("Finding position by quantity: " + quantity);
		return positionDAO.findPositionByQuantity(quantity);
	}

	@Override
	public List<PositionEntity> findPositionByCompany(String company) {
		log.info("Finding position by company: " + company);
		return positionDAO.findPositionByCompany(company);
	}

	@Override
	public List<PositionEntity> findPositionByTechnicalArea(
			TechnicalArea technicalArea) {
		log.info("Finding position by technicalArea: " + technicalArea);
		return positionDAO.findPositionByTechnicalArea(technicalArea);
	}

	@Override
	public List<PositionEntity> findPositionBySource(Source source) {
		log.info("Finding position by source: " + source);
		return positionDAO.findPositionBySource(source);
	}

	@Override
	public List<PositionEntity> findPositionByOpenningDate(Date openningDate) {
		log.info("Finding position by openningDate: " + openningDate);
		return positionDAO.findPositionByOpenningDate(openningDate);
	}

	@Override
	public List<PositionEntity> findPositionByClosingDate(Date closingDate) {
		log.info("Finding position by closingDate: " + closingDate);
		return positionDAO.findPositionByClosingDate(closingDate);
	}

	@Override
	public List<PositionEntity> findPositionBySLA(int sla) {
		log.info("Finding position by sla: " + sla);
		return positionDAO.findPositionBySLA(sla);
	}

	@Override
	public List<PositionEntity> findAllByOrder() {
		log.info("Creating query for all positions (order by id)");
		return positionDAO.findAllByOrder();
	}

	@Override
	public PositionEntity addPosition(UserEntity adminCreator,
			UserEntity manager, GuideEntity guide, String title,
			List<Location> location,
			int quantity, String company, TechnicalArea technicalArea,
			DescriptionPosition description, ArrayList<Source> source,
			Date openningDate, Date closingDate, int sla) {
		
		log.info("Saving position in DB");

		PositionEntity p = new PositionEntity(title, location, PositionStatus.OPEN,
				quantity, company, technicalArea, description, source,
				openningDate, closingDate, sla);
		p.setAdminCreator(adminCreator);
		p.setManager(manager);
		p.setGuide(guide);
		positionDAO.save(p);

		return p;
	}
}