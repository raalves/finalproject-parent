package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Stateless
public class UserDAO extends GenericDAO<UserEntity> {

	public UserDAO() {
		super(UserEntity.class);
	}

	public void delete(UserEntity user) {
		super.delete(user.getId(), UserEntity.class);
	}

	public void save(UserEntity user) {
		super.save(user);
	}

	public List<UserEntity> findAllByOrder() {
		return super.findAllByOrder("UserEntity.findAllByIdOrder");
	}

	public UserEntity findUserById(Long id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		List<UserEntity> list = super.findSomeResults("UserEntity.findUserById",
				parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public UserEntity findUserByEmail(String email) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		List<UserEntity> list = super.findSomeResults("UserEntity.findUserByEmail",
				parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public List<UserEntity> findUserStartingBy(String firstName) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstName", firstName);
		return super.findSomeResults("UserEntity.findUserStartingBy", parameters);
	}
	
	public List<UserEntity> findUserByCreator(UserEntity creator) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("creator", creator);
		return super.findSomeResults("UserEntity.findUserByCreator", parameters);
	}
	
	public List<UserEntity> findUserByFirstName(String firstName) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstName", firstName);
		return super.findSomeResults("UserEntity.findUserByFirstName", parameters);
	}
	
	public List<UserEntity> findUserByLastName(String lastName) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("lastName", lastName);
		return super.findSomeResults("UserEntity.findUserByLastName", parameters);
	}

}