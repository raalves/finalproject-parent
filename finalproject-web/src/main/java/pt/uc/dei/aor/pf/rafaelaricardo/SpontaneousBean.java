package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class SpontaneousBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String[] selectCities;
	private List<String> cities;
	private String[] selectAreaTech;
	private List<String> areasTech;

	@PostConstruct
	public void init() {
		cities = new ArrayList<String>();
		cities.add("Coimbra");
		cities.add("Lisboa");
		cities.add("OPorto");

		areasTech = new ArrayList<String>();
		areasTech.add(".Net Development");
		areasTech.add("Java Developmment");
		areasTech.add("Safety Critical");
		areasTech.add("Project Management");
		areasTech.add("Integration");
		areasTech.add("SSPA - Software and Security Product Assurance");

	}

	public String[] getSelectAreaTech() {
		return selectAreaTech;
	}

	public void setSelectAreaTech(String[] selectAreaTech) {
		this.selectAreaTech = selectAreaTech;
	}

	public List<String> getAreasTech() {
		return areasTech;
	}

	public void setAreasTech(List<String> areasTech) {
		this.areasTech = areasTech;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public String[] getSelectCities() {
		return selectCities;
	}

	public void setSelectCities(String[] selectCities) {
		this.selectCities = selectCities;
	}

	public List<String> getCities() {
		return cities;
	}

}
