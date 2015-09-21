package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@Stateless
public class PositionDAO extends GenericDAO<PositionEntity> {

	public PositionDAO() {
		super(PositionEntity.class);
	}

	public void delete(PositionEntity user) {
		super.delete(user.getId(), PositionEntity.class);
	}

	@Override
	public void save(PositionEntity user) {
		super.save(user);
	}

	public List<PositionEntity> findAllByOrder() {
		return super.findAllByOrder("PositionEntity.findAllByIdOrder");
	}

	public PositionEntity findPositionById(Long id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		List<PositionEntity> list = super.findSomeResults(
				"PositionEntity.findPositionById", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public List<PositionEntity> findPositionByTitle(String title) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("title", title);
		return super.findSomeResults("PositionEntity.findPositionByTitle",
				parameters);
	}

	public List<PositionEntity> findPositionByAdminCreator(
			UserEntity adminCreator) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("adminCreator", adminCreator);
		return super.findSomeResults(
				"PositionEntity.findPositionByAdminCreator", parameters);
	}

	public List<PositionEntity> findPositionByManager(UserEntity manager) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("manager", manager);
		return super.findSomeResults(
				"PositionEntity.findPositionByAdminCreatoManager", parameters);
	}

	public List<PositionEntity> findPositionByGuide(GuideEntity guide) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("guide", guide);
		return super.findSomeResults("PositionEntity.findPositionByGuide",
				parameters);
	}

	public List<PositionEntity> findPositionByLocation(Location location) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("location", location);
		return super.findSomeResults("PositionEntity.findPositionByLocation",
				parameters);
	}

	public List<PositionEntity> findPositionByPositionStatus(
			PositionStatus positionStatus) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("positionStatus", positionStatus);
		return super.findSomeResults(
				"PositionEntity.findPositionByPositionStatus", parameters);
	}

	public List<PositionEntity> findPositionByQuantity(int quantity) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("quantity", quantity);
		return super.findSomeResults("PositionEntity.findPositionByQuantity",
				parameters);
	}

	public List<PositionEntity> findPositionByCompany(String company) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("company", company);
		return super.findSomeResults("PositionEntity.findPositionByCompany",
				parameters);
	}

	public List<PositionEntity> findPositionByTechnicalArea(
			TechnicalArea technicalArea) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("technicalArea", technicalArea);
		return super.findSomeResults(
				"PositionEntity.findPositionByTechnicalArea", parameters);
	}

	public List<PositionEntity> findPositionBySource(Source source) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("source", source);
		return super.findSomeResults("PositionEntity.findPositionBySource",
				parameters);
	}

	public List<PositionEntity> findPositionByOpenningDate(Date openningDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("openningDate", openningDate);
		return super.findSomeResults(
				"PositionEntity.findPositionByOpenningDate", parameters);
	}

	public List<PositionEntity> findPositionByClosingDate(Date closingDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("closingDate", closingDate);
		return super.findSomeResults(
				"PositionEntity.findPositionByClosingDate", parameters);
	}

	public List<PositionEntity> findPositionBySLA(int sla) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("sla", sla);
		return super.findSomeResults("PositionEntity.findPositionBySLA",
				parameters);
	}
<<<<<<< HEAD
	
	public List<PositionEntity> findPositionByManager(UserEntity manager) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("manager", manager);
		return super.findSomeResults("PositionEntity.findPositionByManager",
				parameters);
	}
	
	public List<PositionEntity> findPositionByGuide(GuideEntity guide) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("guide", guide);
		return super.findSomeResults("PositionEntity.findPositionByGuide",
				parameters);
	}
	
	public List<PositionEntity> findPositionByLocation(Location location) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("location", location);
		return super.findSomeResults("PositionEntity.findPositionByLocation",
				parameters);
	}
	
	public List<PositionEntity> findPositionByPositionStatus(PositionStatus positionStatus) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("positionStatus", positionStatus);
		return super.findSomeResults("PositionEntity.findPositionByPositionStatus",
				parameters);
	}
	
	public List<PositionEntity> findPositionByQuantity(int quantity) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("quantity", quantity);
		return super.findSomeResults("PositionEntity.findPositionByQuantity",
				parameters);
	}
	
	public List<PositionEntity> findPositionByCompany(String company) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("company", company);
		return super.findSomeResults("PositionEntity.findPositionByCompany",
				parameters);
	}
	
	public List<PositionEntity> findPositionByTechnicalArea(TechnicalArea technicalArea) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("technicalArea", technicalArea);
		return super.findSomeResults("PositionEntity.findPositionByTechnicalArea",
				parameters);
	}
	
	public List<PositionEntity> findPositionBySource(Source source) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("source", source);
		return super.findSomeResults("PositionEntity.findPositionBySource",
				parameters);
	}
	
	public List<PositionEntity> findPositionByOpenningDate(Date openningDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("openningDate", openningDate);
		return super.findSomeResults("PositionEntity.findPositionByOpenningDate",
				parameters);
	}
	
	public List<PositionEntity> findPositionByClosingDate(Date closingDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("closingDate", closingDate);
		return super.findSomeResults("PositionEntity.findPositionByClosingDate",
				parameters);
	}
	
	public List<PositionEntity> findPositionBySLA(int sla) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("sla", sla);
		return super.findSomeResults("PositionEntity.findPositionBySLA",
				parameters);
	}
	
=======
>>>>>>> origin/master

}