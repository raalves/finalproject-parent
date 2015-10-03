package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;

@ManagedBean
@ViewScoped
public class ManagerMB implements Serializable {

	private static final long serialVersionUID = 8666955892442985554L;
	private static final Logger log = LoggerFactory.getLogger(ManagerMB.class);

	@EJB
	private InterviewFacade interviewFacade;
	@EJB
	private CandidatureFacade candidatureFacade;
	@EJB
	private PositionFacade positionFacade;
	@Inject
	private ActiveUserMB activeUserMB;

	private List<InterviewEntity> allInterviewsList;
	private List<PositionEntity> managerPositionsList;

	@PostConstruct
	public void listAllPositions() {
		log.info("Search for all Positions of Manager: "
				+ activeUserMB.getCurrentUser().getEmail());
		try {
			setManagerPositionsList(positionFacade
					.findPositionByManager(activeUserMB.getCurrentUser()));

		} catch (EJBException e) {
			String errorMsg = "Error gettin Positions of Manager: "
					+ activeUserMB.getCurrentUser().getEmail() + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	public List<InterviewEntity> getAllInterviewsList() {
		return allInterviewsList;
	}

	public void setAllInterviewsList(List<InterviewEntity> allInterviewsList) {
		this.allInterviewsList = allInterviewsList;
	}

	public List<PositionEntity> getManagerPositionsList() {
		return managerPositionsList;
	}

	public void setManagerPositionsList(
			List<PositionEntity> managerPositionsList) {
		this.managerPositionsList = managerPositionsList;
	}

	public void updateInterviewsList(PositionEntity position) {
		List<CandidatureEntity> candidatures = candidatureFacade
				.findCandidatureByPosition(position);

		List<InterviewEntity> interviews = new ArrayList<InterviewEntity>();

		for (CandidatureEntity c : candidatures) {
			List<InterviewEntity> list1 = interviewFacade
					.findInterviewByCandidature(c);
			for (InterviewEntity i : list1) {
				interviews.add(i);
			}
		}
		allInterviewsList = interviews;
	}
}
