package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;

public interface CandidateFacade {

	public abstract List<CandidateEntity> findAllByOrder();

	public abstract CandidateEntity findCandidateById(Long id);

	public abstract CandidateEntity findCandidateByEmail(String email);

	public abstract CandidateEntity addCandidate(String firstName,
			String lastName, String email, String password, Date birthdate,
			String address, String city, Long mobilePhone, String country,
			String course, String school, String cvPath, String coverLetter);

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

	public abstract List<CandidateEntity> findCandidateByCountry(String country);

	public abstract List<CandidateEntity> findCandidateByCourse(String course);

	public abstract List<CandidateEntity> findCandidateBySchool(String school);

	public abstract void addPhone(CandidateEntity candidate, Long phone);

	public abstract boolean updateCandidatePass(CandidateEntity c,
			String oldPass, String newPass);

}
