package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class CandToVacBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer selectVac;
	private List<Integer> candidatesList;

	public Integer getSelectVac() {
		return selectVac;
	}

	public void setSelectVac(Integer selectVac) {
		this.selectVac = selectVac;
	}

	public List<Integer> getCandidatesList() {
		return candidatesList;
	}

	public void setCandidatesList(List<Integer> candidatesList) {
		this.candidatesList = candidatesList;
	}

	public CandToVacBean() {

		candidatesList = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			candidatesList.add(i);

		}
	}

}
