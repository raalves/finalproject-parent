package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.InterviewDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.InterviewStatus;

@Stateless
public class InterviewFacadeImp implements InterviewFacade {

	private static final Logger log = LoggerFactory
			.getLogger(InterviewFacadeImp.class);
	@EJB
	private InterviewDAO interviewDAO;

	@Override
	public List<InterviewEntity> findAllByOrder() {
		log.info("Creating query for all interviews (order by id)");
		return interviewDAO.findAllByOrder();
	}

	@Override
	public InterviewEntity findInterviewById(Long id) {
		log.info("Finding interview by id: " + id);
		return interviewDAO.findInterviewById(id);
	}

	@Override
	public List<InterviewEntity> findInterviewByCandidature(
			CandidatureEntity candidature) {
		log.info("Finding interview by candidature: " + candidature);
		return interviewDAO.findInterviewByCandidature(candidature);
	}

	@Override
	public List<InterviewEntity> findInterviewByDate(Date interviewDate) {
		log.info("Finding interview by interviewDate: " + interviewDate);
		return interviewDAO.findInterviewByDate(interviewDate);
	}

	@Override
	public List<InterviewEntity> findInterviewByStatus(
			InterviewStatus interviewStatus) {
		log.info("Finding interview by interviewStatus: " + interviewStatus);
		return interviewDAO.findInterviewByStatus(interviewStatus);
	}

}