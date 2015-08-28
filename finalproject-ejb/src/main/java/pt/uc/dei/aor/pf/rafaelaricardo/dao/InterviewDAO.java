package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.InterviewStatus;

@Stateless
public class InterviewDAO extends GenericDAO<InterviewEntity> {

	public InterviewDAO() {
		super(InterviewEntity.class);
	}

	public void delete(InterviewEntity interview) {
		super.delete(interview.getId(), InterviewEntity.class);
	}

	public void save(InterviewEntity interview) {
		super.save(interview);
	}

	public List<InterviewEntity> findAllByOrder() {
		return super.findAllByOrder("InterviewEntity.findAllByIdOrder");
	}

	public InterviewEntity findInterviewById(Long id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		List<InterviewEntity> list = super.findSomeResults(
				"InterviewEntity.findInterviewById", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public List<InterviewEntity> findInterviewByCandidature(CandidatureEntity candidature) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("candidature", candidature);
		return super.findSomeResults("InterviewEntity.findInterviewByCandidature",
				parameters);
	}
	
	public List<InterviewEntity> findInterviewByDate(Date interviewDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("interviewDate", interviewDate);
		return super.findSomeResults("InterviewEntity.findInterviewByDate",
				parameters);
	}
	
	public List<InterviewEntity> findInterviewByStatus(InterviewStatus interviewStatus) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("interviewStatus", interviewStatus);
		return super.findSomeResults("InterviewEntity.findInterviewByStatus",
				parameters);
	}

}