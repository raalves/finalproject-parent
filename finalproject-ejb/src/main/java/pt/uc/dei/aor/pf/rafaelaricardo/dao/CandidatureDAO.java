package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.CandidatureStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;

@Stateless
public class CandidatureDAO extends GenericDAO<CandidatureEntity> {

	public CandidatureDAO() {
		super(CandidatureEntity.class);
	}

	public void delete(CandidatureEntity candidature) {
		super.delete(candidature.getId(), CandidatureEntity.class);
	}

<<<<<<< HEAD
	public void save(CandidatureEntity guide) {
		super.save(guide);
=======
	@Override
	public void save(CandidatureEntity candidature) {
		super.save(candidature);
>>>>>>> origin/master
	}

	public List<CandidatureEntity> findAllByOrder() {
		return super.findAllByOrder("CandidatureEntity.findAllByIdOrder");
	}

<<<<<<< HEAD
	public CandidatureEntity findCandidatureById(Long id) {
=======
	public CandidatureEntity findUserById(Long id) {
>>>>>>> origin/master
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		List<CandidatureEntity> list = super.findSomeResults(
				"CandidatureEntity.findCandidatureById", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

<<<<<<< HEAD
	public List<CandidatureEntity> findCandidatureByPosition(PositionEntity position) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("position", position);
		return super.findSomeResults("CandidatureEntity.findCandidatureByPosition",
				parameters);
	}
	
	public List<CandidatureEntity> findCandidatureByCandidate(CandidateEntity candidate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("candidate", candidate);
		return super.findSomeResults("CandidatureEntity.findCandidatureByCandidate",
				parameters);
	}
	
	public List<CandidatureEntity> findCandidatureBySource(Source publicSource) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("publicSource", publicSource);
		return super.findSomeResults("CandidatureEntity.findCandidatureBySource",
				parameters);
	}
	
=======
	public List<CandidatureEntity> findCandidatureByPosition(
			PositionEntity position) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("position", position);
		return super.findSomeResults(
				"CandidatureEntity.findCandidatureByPosition", parameters);
	}

	public List<CandidatureEntity> findCandidatureByCandidate(
			CandidateEntity candidate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("candidate", candidate);
		return super.findSomeResults(
				"CandidatureEntity.findCandidatureByCandidate", parameters);
	}

	public List<CandidatureEntity> findCandidatureBySource(Source publicSource) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("publicSource", publicSource);
		return super.findSomeResults(
				"CandidatureEntity.findCandidatureBySource", parameters);
	}

>>>>>>> origin/master
	public List<CandidatureEntity> findCandidatureByDate(Date candidatureDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("candidatureDate", candidatureDate);
		return super.findSomeResults("CandidatureEntity.findCandidatureByDate",
				parameters);
	}
<<<<<<< HEAD
	
	public List<CandidatureEntity> findCandidatureByStatus(CandidatureStatus candidatureStatus) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("candidatureStatus", candidatureStatus);
		return super.findSomeResults("CandidatureEntity.findCandidatureByStatus",
				parameters);
	}

	

}
=======

	public List<CandidatureEntity> findCandidatureByStatus(
			CandidatureStatus candidatureStatus) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("candidatureStatus", candidatureStatus);
		return super.findSomeResults(
				"CandidatureEntity.findCandidatureByStatus", parameters);
	}
}
>>>>>>> origin/master
