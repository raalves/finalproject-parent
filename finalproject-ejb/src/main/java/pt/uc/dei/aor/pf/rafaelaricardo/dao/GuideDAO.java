package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Stateless
public class GuideDAO extends GenericDAO<GuideEntity> {

	public GuideDAO() {
		super(GuideEntity.class);
	}

	public void delete(GuideEntity guide) {
		super.delete(guide.getId(), GuideEntity.class);
	}

<<<<<<< HEAD
=======
	@Override
>>>>>>> origin/master
	public void save(GuideEntity guide) {
		super.save(guide);
	}

	public List<GuideEntity> findAllByOrder() {
		return super.findAllByOrder("GuideEntity.findAllByIdOrder");
	}

	public GuideEntity findGuideById(Long id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		List<GuideEntity> list = super.findSomeResults(
				"GuideEntity.findGuideById", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public List<GuideEntity> findGuideByAuthor(UserEntity author) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("author", author);
		return super.findSomeResults("GuideEntity.findGuideByAuthor",
				parameters);
	}

<<<<<<< HEAD
	public List<GuideEntity> findGuideByTitle(String guideTitle) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("guideTitle", guideTitle);
=======
	public List<GuideEntity> findGuideByTitle(String title) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("title", title);
>>>>>>> origin/master
		return super
				.findSomeResults("GuideEntity.findGuideByTitle", parameters);
	}

	public List<GuideEntity> findGuideByDate(Date guideDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("guideDate", guideDate);
		return super.findSomeResults("GuideEntity.findGuideByDate", parameters);
	}
<<<<<<< HEAD

}
=======
}
>>>>>>> origin/master
