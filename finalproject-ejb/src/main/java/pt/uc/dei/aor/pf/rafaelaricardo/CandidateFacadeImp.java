package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.dao.CandidateDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.dao.RoleDAO;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;

@Stateless
public class CandidateFacadeImp implements CandidateFacade {

	private static final Logger log = LoggerFactory
			.getLogger(CandidateFacadeImp.class);
	@EJB
	private RoleDAO roleDao;
	@EJB
	private CandidateDAO candidateDao;
	@EJB
	private EncryptPass encryptPass;

	@Override
	public List<CandidateEntity> findAllByOrder() {
		log.info("Creating query for all candidates (order by id)");
		return candidateDao.findAllByOrder();
	}

	@Override
	public CandidateEntity findCandidateById(Long id) {
		log.info("Finding candidate by id: " + id);
		return candidateDao.findCandidateById(id);
	}

	@Override
	public CandidateEntity findCandidateByEmail(String email) {
		log.info("Finding candidate by email: " + email);
		return candidateDao.findCandidateByEmail(email);
	}

	@Override
	public List<CandidateEntity> findCandidateByRole(RoleEntity role) {
		log.info("Finding candidate by role: " + role);
		return candidateDao.findCandidateByRole(role);
	}

	@Override
	public List<CandidateEntity> findCandidateByFirstName(String firstName) {
		log.info("Finding candidate by firstName: " + firstName);
		return candidateDao.findCandidateByFirstName(firstName);
	}

	@Override
	public List<CandidateEntity> findCandidateByLastName(String lastName) {
		log.info("Finding candidate by lastName: " + lastName);
		return candidateDao.findCandidateByLastName(lastName);
	}

	@Override
	public List<CandidateEntity> findCandidateByBirthDate(Date birthDate) {
		log.info("Finding candidates by birthDate: " + birthDate);
		return candidateDao.findCandidateByBirthDate(birthDate);
	}

	@Override
	public List<CandidateEntity> findCandidateByAddress(String address) {
		log.info("Finding candidates by address: " + address);
		return candidateDao.findCandidateByAddress(address);
	}

	@Override
	public List<CandidateEntity> findCandidateByCity(String city) {
		log.info("Finding candidates by first city: " + city);
		return candidateDao.findCandidateByCity(city);
	}

	@Override
	public List<CandidateEntity> findCandidateByMobilePhone(Long mobilePhone) {
		log.info("Finding candidates by mobilePhone: " + mobilePhone);
		return candidateDao.findCandidateByMobilePhone(mobilePhone);
	}

	@Override
	public List<CandidateEntity> findCandidateByCountry(Long country) {
		log.info("Finding candidates by country: " + country);
		return candidateDao.findCandidateByCountry(country);
	}

	@Override
	public List<CandidateEntity> findCandidateByCourse(Long course) {
		log.info("Finding candidates by course: " + course);
		return candidateDao.findCandidateByCourse(course);
	}

	@Override
	public List<CandidateEntity> findCandidateBySchool(String school) {
		log.info("Finding candidates by school: " + school);
		return candidateDao.findCandidateBySchool(school);
	}

	@Override
	public CandidateEntity addCandidate(String firstName, String lastName,
			String email, String password, Date birthdate, String address,
			String city, Long mobilePhone, String country, String course,
			String school, String cvPath) {
		log.info("Saving candidate in DB");

		if (candidateDao.findCandidateByEmail(email) == null) {
			CandidateEntity c = new CandidateEntity(firstName, lastName, email,
					encryptPass.encrypt(password), birthdate, address, city,
					mobilePhone, country, course, school, cvPath);
			RoleEntity rc = new RoleEntity(Role.CANDIDATE);
			roleDao.save(rc);
			// roleDao.save(new RoleEntity(Role.CANDIDATE));
			c.setRole(rc);
			candidateDao.save(c);
			return c;
		}
		return null;

	}

	@Override
	public void addPhone(CandidateEntity candidate, Long phone) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateCandidatePass(CandidateEntity c, String oldPass,
			String newPass) {
		log.info("Update password from candidate: " + c.getEmail());
		if (c != null) {
			if (c.checkPassword(encryptPass.encrypt(oldPass))) {
				c.setPassword(encryptPass.encrypt(newPass));
				isCandidateWithAllData(c);
				candidateDao.update(c);
				return true;
			}
		}
		return false;
	}

	private void isCandidateWithAllData(CandidateEntity c) {
		boolean hasError = false;

		if (c == null) {
			hasError = true;
		}

		if ((c.getFirstName() == null) || "".equals(c.getFirstName().trim())) {
			hasError = true;
		}

		if (hasError) {
			throw new IllegalArgumentException(
					"The candidate is missing data. Check the name and password, they should have value.");
		}
	}

	// @EJB
	// private LyricDAO lyrDAO;
	//
	// @EJB
	// private UserDAO userDAO;
	//
	// @EJB
	// private SongDAO songDAO;
	//
	// // first time that someone click on Get Lyrics
	// public Lyric addLyric(String text, Song s) {
	//
	// // update song attibute hasLyrics
	// log.info("Updating song hasLyric to true");
	// s.setHasLyric(true);
	// songDAO.update(s);
	//
	// log.info("Adding lyric to DB (owner = ADMIN)");
	// User admin = userDAO.findUserByEmail("admin@admin.com");
	// Lyric l = new Lyric(text, admin, s);
	// lyrDAO.save(l);
	// return l;
	// }
	//
	// // if hasLyrics!!! (to pop-up)
	// public Lyric getLyricSongUser(User u, Song s) {
	// log.info("Getting lyric of DB (for a particular user)");
	// Lyric l = lyrDAO.findLyricByUserAndSong(u, s);
	// if (l == null) {
	// User admin = userDAO.findUserByEmail("admin@admin.com");
	// l = lyrDAO.findLyricByUserAndSong(admin, s);
	// }
	//
	// return l;
	// }
	//
	// // if hasLyrics and already in the pop-up (we know the Lyrics)
	// public Lyric editLyric(User u, Lyric lyric) {
	// log.info("Editing lyric of DB (for a particular user)");
	// User admin = userDAO.findUserByEmail("admin@admin.com");
	// if (lyric.getOwner().equals(admin)) { // create new lyric
	// Lyric l = new Lyric(lyric.getLyric(), u, lyric.getMusic());
	// lyrDAO.save(l);
	// return l;
	// } else {
	// lyrDAO.update(lyric);
	// return lyric;
	// }
	//
	// }
	//
	// public Lyric getLyricById(Long id) {
	// log.info("Getting lyric of DB by id");
	// Lyric l = lyrDAO.findLyricById(id);
	// return l;
	// }

}