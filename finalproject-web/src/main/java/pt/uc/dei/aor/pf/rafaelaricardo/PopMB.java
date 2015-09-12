package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named
public class PopMB implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PopMB.class);
	// <p:commandButton value="popul" action="#{popMB.popul()}"/>
	// <p:outputLabel value="#{popMB.popl}"/>
	// @Inject
	// private Populate pp;
	// private String popl = "nao popuplada";
	// @EJB
	// private PositionFacade positionFacade;
	//
	// public void popul() {
	// try {
	// log.info("Populando a DB");
	// pp.populando();
	// setPopl("populada");
	// } catch (ParseException e) {
	// log.error(e.getMessage());
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// public String getPopl() {
	// try {
	// if (positionFacade.findAllByOrder() == null) {
	// popul();
	// }
	// return popl;
	// } catch (EJBException e) {
	// log.error(e.getMessage());
	//
	// }
	// return null;
	// }
	//
	// public void setPopl(String popl) {
	// this.popl = popl;
	// }

}
