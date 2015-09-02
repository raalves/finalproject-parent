package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CandidateCandidaturesBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> candidaturesCandidate;
	private Integer candidatureSelect;
	private Integer numb;

	public Integer getNumb() {
		return numb;
	}

	public Integer getCandidatureSelect() {
		return candidatureSelect;
	}

	public void setCandidatureSelect(Integer candidatureSelect) {
		this.candidatureSelect = candidatureSelect;
	}

	public CandidateCandidaturesBean() {

		candidaturesCandidate = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			candidaturesCandidate.add(i);

		}
	}

	public List<Integer> getCandidaturesCandidate() {
		return candidaturesCandidate;
	}

}
