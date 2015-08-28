package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Stateless
public class PositionDAO extends GenericDAO<PositionEntity> {

	public PositionDAO() {
		super(PositionEntity.class);
	}

	public void delete(PositionEntity user) {
		super.delete(user.getId(), PositionEntity.class);
	}

	public void save(PositionEntity user) {
		super.save(user);
	}

	public List<PositionEntity> findAllByOrder() {
		return super.findAllByOrder("PositionEntity.findAllByIdOrder");
	}

	public PositionEntity findPositionById(Long id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		List<PositionEntity> list = super.findSomeResults(
				"PositionEntity.findPositionById", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public List<PositionEntity> findPositionByTitle(String title) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("title", title);
		return super.findSomeResults("PositionEntity.findPositionByTitle",
				parameters);
	}

	public List<PositionEntity> findPositionByAdminCreator(UserEntity adminCreator) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("adminCreator", adminCreator);
		return super.findSomeResults("PositionEntity.findPositionByAdminCreator",
				parameters);
	}
	

}