package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.InterviewStatus;

public interface InterviewFacade {

	public abstract List<InterviewEntity> findAllByOrder();

	public abstract InterviewEntity findInterviewById(Long id);

	public abstract List<InterviewEntity> findInterviewByCandidature(
			CandidatureEntity candidature);

	public abstract List<InterviewEntity> findInterviewByDate(Date interviewDate);

	public abstract List<InterviewEntity> findInterviewByStatus(
			InterviewStatus interviewStatus);

	public abstract List<InterviewEntity> findInterviewByUser(UserEntity user);

	public abstract boolean updateFeedbackStatus(InterviewEntity interview,
			String feedbakc, InterviewStatus status);

}
