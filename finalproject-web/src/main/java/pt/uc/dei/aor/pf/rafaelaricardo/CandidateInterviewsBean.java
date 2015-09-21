package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CandidateInterviewsBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> interviewsCandidate;
	private Integer interviewSelect;

	public Integer getInterviewSelect() {
		return interviewSelect;
	}

	public void setInterviewSelect(Integer interviewSelect) {
		this.interviewSelect = interviewSelect;
	}

	public CandidateInterviewsBean() {
		interviewsCandidate = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			interviewsCandidate.add(i);
		}

	}

	public List<Integer> getInterviewsCandidate() {
		return interviewsCandidate;
	}

}