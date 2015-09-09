package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.GuideDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Stateless
public class GuideFacadeImp implements GuideFacade {

	private static final Logger log = LoggerFactory
			.getLogger(GuideFacadeImp.class);
	@EJB
	private GuideDAO guideDAO;

	@Override
	public List<GuideEntity> findAllByOrder() {
		log.info("Creating query for all guides (order by id)");
		return guideDAO.findAllByOrder();
	}

	@Override
	public GuideEntity findGuideById(Long id) {
		log.info("Finding guide starting by id: " + id);
		return guideDAO.findGuideById(id);
	}

	@Override
	public List<GuideEntity> findGuideByAuthor(UserEntity author) {
		log.info("Finding guide starting by author: " + author);
		return guideDAO.findGuideByAuthor(author);
	}

	@Override
	public List<GuideEntity> findGuideByTitle(String title) {
		log.info("Finding guide starting by title: " + title);
		return guideDAO.findGuideByTitle(title);
	}

	@Override
	public List<GuideEntity> findGuideByDate(Date guideDate) {
		log.info("Finding guide starting by guideDate: " + guideDate);
		return guideDAO.findGuideByDate(guideDate);
	}

}