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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;

@ManagedBean
@ViewScoped
public class AdminMB implements Serializable {

	private static final long serialVersionUID = 8666955892442985554L;
	private static final Logger log = LoggerFactory
			.getLogger(SearchCandidaturesMB.class);
	
	
	@EJB
	private InterviewFacade interviewFacade;
	
	private List<InterviewEntity> allInterviewsList;
	
	
	
	@PostConstruct
	public void listAllCandidates() {
		log.info("Search for all Interviews");
		try {
			allInterviewsList = interviewFacade.findAllByOrder();

		} catch (EJBException e) {
			String errorMsg = "Error gettin Interviews: " + e.getMessage();
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
}
