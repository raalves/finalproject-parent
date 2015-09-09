package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

public interface UserFacade {

	public abstract UserEntity findUserById(Long id);

	public abstract UserEntity findUserByEmail(String email);

	public abstract List<UserEntity> findUserByCreator(UserEntity creator);

	public abstract List<UserEntity> findUserByFirstName(String firstName);

	public abstract List<UserEntity> findUserByLastName(String lastName);

	public abstract List<UserEntity> findUserStartingBy(String firstName);

	public abstract List<UserEntity> findAllByOrder();

}
