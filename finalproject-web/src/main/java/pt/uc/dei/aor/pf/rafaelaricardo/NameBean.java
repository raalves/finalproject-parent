package pt.uc.dei.aor.pf.rafaelaricardo;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NameBean {

	private String areaName = "first";
	private String userName = "rafa";

	public void setName(String name) {
		setAreaName(name);
		System.out.println(name + "  " + areaName);
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
