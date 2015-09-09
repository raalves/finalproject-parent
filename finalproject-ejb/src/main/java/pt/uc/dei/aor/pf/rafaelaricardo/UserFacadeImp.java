package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.UserDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Stateless
public class UserFacadeImp implements UserFacade {

	private static final Logger log = LoggerFactory
			.getLogger(UserFacadeImp.class);
	@EJB
	private UserDAO userDAO;

	@Override
	public UserEntity findUserById(Long id) {
		log.info("Finding user by id: " + id);
		return userDAO.findUserById(id);
	}

	@Override
	public UserEntity findUserByEmail(String email) {
		log.info("Finding user by email: " + email);
		return userDAO.findUserByEmail(email);
	}

	@Override
	public List<UserEntity> findUserByCreator(UserEntity creator) {
		log.info("Finding user by creator: " + creator);
		return userDAO.findUserByCreator(creator);
	}

	@Override
	public List<UserEntity> findUserByFirstName(String firstName) {
		log.info("Finding user by first name: " + firstName);
		return userDAO.findUserByFirstName(firstName);
	}

	@Override
	public List<UserEntity> findUserByLastName(String lastName) {
		log.info("Finding user by last name: " + lastName);
		return userDAO.findUserByLastName(lastName);
	}

	@Override
	public List<UserEntity> findUserStartingBy(String firstName) {
		log.info("Finding user starting by first name: " + firstName);
		return null;
	}

	@Override
	public List<UserEntity> findAllByOrder() {
		log.info("Creating query for all users (order by id)");
		return userDAO.findAllByOrder();
	}

}