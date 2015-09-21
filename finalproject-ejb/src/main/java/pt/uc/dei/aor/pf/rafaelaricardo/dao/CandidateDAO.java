package pt.uc.dei.aor.pf.rafaelaricardo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;

@Stateless
public class CandidateDAO extends GenericDAO<CandidateEntity> {

	public CandidateDAO() {
		super(CandidateEntity.class);
	}

	public void delete(CandidateEntity candidate) {
		super.delete(candidate.getId(), CandidateEntity.class);
	}

	@Override
	public void save(CandidateEntity candidate) {
		super.save(candidate);
	}

	public List<CandidateEntity> findAllByOrder() {
		return super.findAllByOrder("CandidateEntity.findAllByIdOrder");
	}

	public CandidateEntity findCandidateById(Long id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		List<CandidateEntity> list = super.findSomeResults(
				"CandidateEntity.findCandidateById", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public CandidateEntity findCandidateByEmail(String email) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		List<CandidateEntity> list = super.findSomeResults(
				"CandidateEntity.findCandidateByEmail", parameters);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public List<CandidateEntity> findCandidateByRole(RoleEntity role) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("role", role);
		return super.findSomeResults("CandidateEntity.findCandidateByRole",
				parameters);
	}

	public List<CandidateEntity> findCandidateByFirstName(String firstName) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstName", firstName);
		return super.findSomeResults(
				"CandidateEntity.findCandidateByFirstName", parameters);
	}

	public List<CandidateEntity> findCandidateByLastName(String lastName) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("lastName", lastName);
		return super.findSomeResults("CandidateEntity.findCandidateByLastName",
				parameters);
	}

	public List<CandidateEntity> findCandidateByBirthDate(Date birthDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDate", birthDate);
		return super.findSomeResults(
				"CandidateEntity.findCandidateByBirthDate", parameters);
	}

	public List<CandidateEntity> findCandidateByAddress(String address) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("address", address);
		return super.findSomeResults("CandidateEntity.findCandidateByAddress",
				parameters);
	}

	public List<CandidateEntity> findCandidateByCity(String city) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("city", city);
		return super.findSomeResults("CandidateEntity.findCandidateByCity",
				parameters);
	}

	public List<CandidateEntity> findCandidateByMobilePhone(Long mobilePhone) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("mobilePhone", mobilePhone);
		return super.findSomeResults(
				"CandidateEntity.findCandidateByMobilePhone", parameters);
	}

	public List<CandidateEntity> findCandidateByCountry(String country) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("country", country);
		return super.findSomeResults("CandidateEntity.findCandidateByCountry",
				parameters);
	}

	public List<CandidateEntity> findCandidateByCourse(String course) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("course", course);
		return super.findSomeResults("CandidateEntity.findCandidateByCourse",
				parameters);
	}

	public List<CandidateEntity> findCandidateBySchool(String school) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("school", school);
		return super.findSomeResults("CandidateEntity.findCandidateBySchool",
				parameters);
	}

}
