package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.CandidatureDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.CandidatureStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;

@Stateless
public class CandidatureFacadeImp implements CandidatureFacade {

	private static final Logger log = LoggerFactory
			.getLogger(CandidatureFacadeImp.class);
	@EJB
	private CandidatureDAO candidatureDAO;

	@Override
	public List<CandidatureEntity> findAllByOrder() {
		log.info("Creating query for all candidatures (order by id)");
		return candidatureDAO.findAllByOrder();
	}

	@Override
	public CandidatureEntity findCandidatureById(Long id) {
		log.info("Finding candidature by last id: " + id);
		return candidatureDAO.findCandidatureById(id);
	}

	@Override
	public List<CandidatureEntity> findCandidatureByPosition(
			PositionEntity position) {
		log.info("Finding candidature by last position: " + position);
		return candidatureDAO.findCandidatureByPosition(position);
	}

	@Override
	public List<CandidatureEntity> findCandidatureByCandidate(
			CandidateEntity candidate) {
		log.info("Finding candidature by last candidate: " + candidate);
		return candidatureDAO.findCandidatureByCandidate(candidate);
	}

	@Override
	public List<CandidatureEntity> findCandidatureBySource(Source publicSource) {
		log.info("Finding candidature by last publicSource: " + publicSource);
		return candidatureDAO.findCandidatureBySource(publicSource);
	}

	@Override
	public List<CandidatureEntity> findCandidatureByDate(Date candidatureDate) {
		log.info("Finding candidature by last candidatureDate: "
				+ candidatureDate);
		return candidatureDAO.findCandidatureByDate(candidatureDate);
	}

	@Override
	public List<CandidatureEntity> findCandidatureByStatus(
			CandidatureStatus candidatureStatus) {
		log.info("Finding candidature by last candidatureStatus: "
				+ candidatureStatus);
		return candidatureDAO.findCandidatureByStatus(candidatureStatus);
	}

}