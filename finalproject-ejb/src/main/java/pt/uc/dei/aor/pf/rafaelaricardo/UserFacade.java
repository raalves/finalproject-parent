package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.ArrayList;
import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

public interface UserFacade {

	public abstract UserEntity findUserById(Long id);

	public abstract UserEntity findUserByEmail(String email);

	public abstract UserEntity addUser(UserEntity creator, String firstName,
			String lastName, String email, String password,
			ArrayList<RoleEntity> roles);

	public abstract List<UserEntity> findUserByCreator(UserEntity creator);

	public abstract List<UserEntity> findUserByFirstName(String firstName);

	public abstract List<UserEntity> findUserByLastName(String lastName);

	public abstract List<UserEntity> findUserStartingBy(String firstName);

	public abstract List<UserEntity> findAllByOrder();

	public abstract void addInterviewToUser(List<UserEntity> interviewers,
			InterviewEntity interview);

}
