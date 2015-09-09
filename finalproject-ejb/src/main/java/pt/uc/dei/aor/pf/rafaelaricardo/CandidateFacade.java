package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;

public interface CandidateFacade {

	public abstract List<CandidateEntity> findAllByOrder();

	public abstract CandidateEntity findCandidateById(Long id);

	public abstract CandidateEntity findCandidateByEmail(String email);

	public abstract List<CandidateEntity> findCandidateByRole(RoleEntity role);

	public abstract List<CandidateEntity> findCandidateByFirstName(
			String firstName);

	public abstract List<CandidateEntity> findCandidateByLastName(
			String lastName);

	public abstract List<CandidateEntity> findCandidateByBirthDate(
			Date birthDate);

	public abstract List<CandidateEntity> findCandidateByAddress(String address);

	public abstract List<CandidateEntity> findCandidateByCity(String city);

	public abstract List<CandidateEntity> findCandidateByMobilePhone(
			Long mobilePhone);

	public abstract List<CandidateEntity> findCandidateByCountry(Long country);

	public abstract List<CandidateEntity> findCandidateByCourse(Long course);

	public abstract List<CandidateEntity> findCandidateBySchool(String school);

}
