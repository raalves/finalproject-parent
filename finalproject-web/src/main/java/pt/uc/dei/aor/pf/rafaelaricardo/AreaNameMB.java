package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AreaNameMB implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String areaName;

	public void areaNameAdmin() {
		System.out.println("areaadmin");
		this.areaName = "Administrador";
		// setAreaName("Administrador");
	}

	public void areaNameManager() {
		System.out.println("areaman");
		this.areaName = "Manager";
		// setAreaName("Manager");
	}

	public void areaNameInterviewer() {
		System.out.println("areaInterv");
		this.areaName = "Interviewer";
		// setAreaName("Interviewer");
	}

	public void areaNameCandidate() {
		System.out.println("areacand");
		this.areaName = "Candidate";
		// setAreaName("Candidate");
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
