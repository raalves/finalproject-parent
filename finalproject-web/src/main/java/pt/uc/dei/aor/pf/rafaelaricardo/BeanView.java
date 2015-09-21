package pt.uc.dei.aor.pf.rafaelaricardo;

import javax.annotation.ManagedBean;
import javax.inject.Named;

@ManagedBean
@Named
public class BeanView {

	private String areaName = "Public";

	public void setName(String name) {
		setAreaName(name);
		System.out.println(name + "  " + areaName);
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName1) {
		this.areaName = areaName1;
		System.out.println(areaName + "  " + areaName1);
	}

	// private static final long serialVersionUID = 1L;
	// private boolean showName = false;
	// private String userName;
	//
	// private boolean showTabPublic = true;
	// private boolean showTabAdmin = false;
	// private boolean showTabMan = false;
	// private boolean showTabInterv = false;
	// private boolean showTabCand = false;
	//
	// public void showTabPublicMethod() {
	// setShowTabPublic(true);
	// setShowTabAdmin(false);
	// setShowTabMan(false);
	// setShowTabInterv(false);
	// setShowTabCand(false);
	// }
	//
	// public void showTabAdminMethod() {
	// setShowTabPublic(false);
	// setShowTabAdmin(true);
	// setShowTabMan(false);
	// setShowTabInterv(false);
	// setShowTabCand(false);
	// }
	//
	// public void showTabManMethod() {
	// setShowTabPublic(false);
	// setShowTabAdmin(false);
	// setShowTabMan(true);
	// setShowTabInterv(false);
	// setShowTabCand(false);
	// }
	//
	// public void showTabIntervMethod() {
	// setShowTabPublic(false);
	// setShowTabAdmin(false);
	// setShowTabMan(false);
	// setShowTabInterv(true);
	// setShowTabCand(false);
	// }
	//
	// public void showTabCandMethod() {
	// setShowTabPublic(false);
	// setShowTabAdmin(false);
	// setShowTabMan(false);
	// setShowTabInterv(false);
	// setShowTabCand(true);
	// }
	//
	// public boolean isShowTabAdmin() {
	// return showTabAdmin;
	// }
	//
	// public void setShowTabAdmin(boolean showTabAdmin) {
	// this.showTabAdmin = showTabAdmin;
	// }
	//
	// public boolean isShowName() {
	// return showName;
	// }
	//
	// public void setShowName(boolean showName) {
	// this.showName = showName;
	// }
	//
	// public String getUserName() {
	// return userName;
	// }
	//
	// public void setUserName(String userName) {
	// this.userName = "Rafa";
	// }
	//
	// public boolean isShowTabPublic() {
	// return showTabPublic;
	// }
	//
	// public void setShowTabPublic(boolean showTabPublic) {
	// this.showTabPublic = showTabPublic;
	// }
	//
	// public boolean isShowTabMan() {
	// return showTabMan;
	// }
	//
	// public void setShowTabMan(boolean showTabMan) {
	// this.showTabMan = showTabMan;
	// }
	//
	// public boolean isShowTabInterv() {
	// return showTabInterv;
	// }
	//
	// public void setShowTabInterv(boolean showTabInterv) {
	// this.showTabInterv = showTabInterv;
	// }
	//
	// public boolean isShowTabCand() {
	// return showTabCand;
	// }
	//
	// public void setShowTabCand(boolean showTabCand) {
	// this.showTabCand = showTabCand;
	// }

}
