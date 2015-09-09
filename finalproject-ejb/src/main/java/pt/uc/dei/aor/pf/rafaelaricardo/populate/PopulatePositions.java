package pt.uc.dei.aor.pf.rafaelaricardo.populate;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class PopulatePositions implements Serializable {

	private static final long serialVersionUID = 3350198034827229932L;
	@PersistenceContext(name = "FinalProject")
	private EntityManager em;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

}
