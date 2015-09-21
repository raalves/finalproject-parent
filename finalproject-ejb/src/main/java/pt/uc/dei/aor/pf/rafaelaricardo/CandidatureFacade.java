package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.CandidatureStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;

public interface CandidatureFacade {

	public abstract List<CandidatureEntity> findAllByOrder();

	public abstract CandidatureEntity findUserById(Long id);

	public abstract List<CandidatureEntity> findCandidatureByPosition(
			PositionEntity position);

	public abstract List<CandidatureEntity> findCandidatureByCandidate(
			CandidateEntity candidate);

	public abstract List<CandidatureEntity> findCandidatureBySource(
			Source publicSource);

	public abstract List<CandidatureEntity> findCandidatureByDate(
			Date candidatureDate);

	public abstract List<CandidatureEntity> findCandidatureByStatus(
			CandidatureStatus candidatureStatus);

}
