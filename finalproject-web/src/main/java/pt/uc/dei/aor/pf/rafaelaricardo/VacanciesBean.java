package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class VacanciesBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> vacancies;
	private String title = "title qq coisa";
	private String Location = "Lisboa";
	private Integer onVacSelect;
	private Integer numb = 10;

	public Integer getNumb() {
		return numb;
	}

	public void setNumb(Integer numb) {
		this.numb = numb;
	}

	public Integer getOnVacSelect() {
		return onVacSelect;
	}

	// public void onVacSelect(SelectEvent event) {
	// this.onVacSelect = (Integer) event.getObject();
	//
	// }

	public void setOnVacSelect(Integer onVacSelect) {
		this.onVacSelect = onVacSelect;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public VacanciesBean() {

		vacancies = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			vacancies.add(i);
		}
	}

	public List<Integer> getVacancies() {
		return vacancies;
	}

}
