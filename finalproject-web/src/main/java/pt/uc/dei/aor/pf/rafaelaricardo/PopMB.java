package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.text.ParseException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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

	@Inject
	private Populate pp;

	public void popul() {
		try {
			log.info("Populando a DB");
			pp.populando();
		} catch (ParseException e) {
			log.error(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
