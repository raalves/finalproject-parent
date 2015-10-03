package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;

@ManagedBean
@ViewScoped
public class CandidateMB implements Serializable {

	private static final long serialVersionUID = 8666955892442985554L;
	private static final Logger log = LoggerFactory
			.getLogger(CandidateMB.class);

	@EJB
	private InterviewFacade interviewFacade;

	@EJB
	private CandidatureFacade candidatureFacade;

	@Inject
	private ActiveUserMB activeUser;

	private List<InterviewEntity> candidateInterviewsList;

	@PostConstruct
	public void listAllInterviews() {
		log.info("Search for Candidate Interviews");

		List<CandidatureEntity> candidatures = candidatureFacade
				.findCandidatureByCandidate(activeUser.getCurrentCandidate());

		List<InterviewEntity> interviews = new ArrayList<InterviewEntity>();

		for (CandidatureEntity c : candidatures) {
			List<InterviewEntity> list1 = interviewFacade
					.findInterviewByCandidature(c);
			for (InterviewEntity i : list1) {
				interviews.add(i);
			}
		}
		candidateInterviewsList = interviews;
	}

	public List<InterviewEntity> getAllInterviewsList() {
		return candidateInterviewsList;
	}

	public void setAllInterviewsList(List<InterviewEntity> allInterviewsList) {
		this.candidateInterviewsList = allInterviewsList;
	}

	public ActiveUserMB getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(ActiveUserMB activeUser) {
		this.activeUser = activeUser;
	}
}
