package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ManagerVacanciesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Integer> vacanciesManager;

	// private Integer interviewSelect;

	public ManagerVacanciesBean() {
		vacanciesManager = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			vacanciesManager.add(i);
		}
	}

	public List<Integer> getVacanciesManager() {
		return vacanciesManager;
	}

	public void setVacanciesManager(List<Integer> vacanciesManager) {
		this.vacanciesManager = vacanciesManager;
	}

}
