package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class MyVacanciesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String vac;
	private List<String> statusVac;
	private String statusSelect;

	@PostConstruct
	public void init() {
		statusVac = new ArrayList<String>();
		statusVac.add("open");
		statusVac.add("On hold");
		statusVac.add("Closed");
	}

	public List<String> getStatusVac() {
		return statusVac;
	}

	public void setStatusVac(List<String> statusVac) {
		this.statusVac = statusVac;
	}

	public String getStatusSelect() {
		return statusSelect;
	}

	public void setStatusSelect(String statusSelect) {
		this.statusSelect = statusSelect;
	}

	public String getVac() {
		return vac;
	}

	public void setVac(String vac) {
		this.vac = vac;
	}

}
