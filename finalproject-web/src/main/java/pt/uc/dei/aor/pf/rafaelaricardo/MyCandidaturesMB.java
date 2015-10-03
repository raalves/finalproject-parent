package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
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

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;

@ManagedBean
@ViewScoped
public class MyCandidaturesMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(MyCandidaturesMB.class);

	@Inject
	private ActiveUserMB actUserMB;
	@EJB
	private CandidatureFacade candidatureFacade;

	private CandidateEntity actCandidate;
	private List<CandidatureEntity> resultList;
	private CandidatureEntity candidatureSelect;

	@PostConstruct
	public void listMyCandidatures() {
		this.actCandidate = actUserMB.getCurrentCandidate();
		log.info("List all candidatures for candidate: " + actCandidate);

		try {
			resultList = candidatureFacade
					.findCandidatureByCandidate(actCandidate);
		} catch (EJBException e) {
			String errorMsg = "Error gettin candidatures: " + e.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
		}
	}

	// Getters and Setters
	public CandidateEntity getActCandidate() {
		return actCandidate;
	}

	public void setActCandidate(CandidateEntity actCandidate) {
		this.actCandidate = actCandidate;
	}

	public List<CandidatureEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<CandidatureEntity> resultList) {
		this.resultList = resultList;
	}

	public CandidatureEntity getCandidatureSelect() {
		return candidatureSelect;
	}

	public void setCandidatureSelect(CandidatureEntity candidatureSelect) {
		this.candidatureSelect = candidatureSelect;
	}
}
