package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.RoleDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.dao.UserDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

@Stateless
public class UserFacadeImp implements UserFacade {

	private static final Logger log = LoggerFactory
			.getLogger(UserFacadeImp.class);
	@EJB
	private UserDAO userDAO;
	@EJB
	private EncryptPass encryptPass;
	@EJB
	private RoleDAO roleDAO;

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

	@Override
	public UserEntity addUser(UserEntity creator, String firstName,
			String lastName, String email, String password,
			ArrayList<RoleEntity> roles) {
		log.info("Saving user in DB");

		if (userDAO.findUserByEmail(email) == null) {
			UserEntity u = new UserEntity(firstName, lastName, email,
					encryptPass.encrypt(password), roles);
			u.setCreator(creator);
			userDAO.save(u);
			return u;
		}
		return null;
	}

	@Override
	public void addInterviewToUser(List<UserEntity> interviewers,
			InterviewEntity interview) {
		log.info("Add Interviewers to Interviwer");
		for (UserEntity u : interviewers) {
			List<InterviewEntity> interv = u.getInterviews();
			interv.add(interview);
			u.setInterviews(interv);
			userDAO.update(u);
		}
	}
}
