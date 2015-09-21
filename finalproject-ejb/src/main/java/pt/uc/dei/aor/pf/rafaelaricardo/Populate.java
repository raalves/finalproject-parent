package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidateEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.CandidatureEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.DescriptionPosition;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.CandidatureStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@Startup
@Singleton
// @Stateless
// @LocalBean
public class Populate implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(Populate.class);

	@PersistenceContext(name = "FinalProject")
	private EntityManager em;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	@Inject
	private EncryptPass encPass;

	// public Populate() {
	// }

	@PostConstruct
	void populando() throws ParseException {

		log.info("Populating th DB");

		ArrayList<RoleEntity> rAdmin = new ArrayList<RoleEntity>();
		rAdmin.add(new RoleEntity(Role.ADMIN));
		ArrayList<RoleEntity> rInterv = new ArrayList<RoleEntity>();
		rInterv.add(new RoleEntity(Role.INTERVIEWER));

		ArrayList<RoleEntity> rManager = new ArrayList<RoleEntity>();
		rManager.add(new RoleEntity(Role.MANAGER));
		ArrayList<RoleEntity> rCandidate = new ArrayList<RoleEntity>();
		rCandidate.add(new RoleEntity(Role.CANDIDATE));
		ArrayList<RoleEntity> rAdminMan = new ArrayList<RoleEntity>();
		rAdminMan.add(new RoleEntity(Role.ADMIN));
		rAdminMan.add(new RoleEntity(Role.MANAGER));
		rAdminMan.add(new RoleEntity(Role.INTERVIEWER));

		RoleEntity rCAnd = new RoleEntity(Role.CANDIDATE);

		UserEntity[] users = {
				new UserEntity("admin", "das couves", "admin@gmail.com",
						encPass.encrypt("123"), rAdmin),
				new UserEntity("interviewer", "das couves", "interv@gmail.com",
						encPass.encrypt("123"), rInterv),
				new UserEntity("manager", "das couves", "manager@gmail.com",
						encPass.encrypt("123"), rManager),
				new UserEntity("candidate", "das couves",
						"candidate@gmail.com", encPass.encrypt("123"),
						rCandidate),
				new UserEntity("adminMan", "das couves", "teste2@gmail.com",
						encPass.encrypt("abc"), rAdminMan) };

		DescriptionPosition dp = new DescriptionPosition();
		dp.setDescription("Working for us is like nothing on earth. Every day, our teams across the globe challenge the limits of human achievement, engineering solutions for our planet and beyond.&lt;br/&gt; Our astronomically talented engineers build rock-solid software for leading industries’ most critical applications. Now, we’re looking for talented Technical Managers for Embedded Systems to join our Systems and Software Engineering team in breaching the frontiers of space, aerospace and defence."
				+ "Across international projects, you’ll be working on some of the most critical software applications developing today, as part of a global team with more than 15 years’ experience working with embedded software and systems. '&lt;br/&gt;'"
				+ "Sound like you? Then it’s time to challenge your limits. It’s time to give your career the rocket boost it deserves. It’s time you joined CRITICAL Software!"
				+ "");
		dp.setKeyResponsabilities("<p>Undertaking software development for embedded systems</p><p>Performing Verification & Validation testing of embedded systems</p><p>Leading a team through your technical expertise (task allocation, status reporting, and people management)keyResponsabilities</p>");
		dp.setDesiredQualifications("<p>Knowledge of embedded software development in microcontrollers</p><p>Knowledge of safety-critical standards (for example, ECSS, DO-178B/C, ISO 26262, IEC 61508)</p><p>Knowledge of system development using model-based development techniques and tools (for example, SCADE and MATLAB)</p>");
		dp.setRequiredQualifications("<p>More than 7 years’ experience working in software development and embedded systems engineering</p><p>Deep knowledge of different Operating Systems, particularly the concepts of real-time and embedded systems, computer architectures and programming languages (C/C++, Ada95)</p><p>Full-lifecycle software development experience, from initial requirements elicitation to design, coding, testing, documentation, implementation, integration and training</p><p>Teamwork and technical project management experience</p><p>Strong leadership, problem-solving and communication skills</p><p>Proficiency in English</p><p>Good organisational skills</p><p>Availability to travel, mainly Europe (United Kingdom, Germany, France)</p>");

		GuideEntity generalGuide = new GuideEntity();
		generalGuide.setGuideTitle("guide position1");
		generalGuide.setGuideDate(ft.parse("2015-05-01"));
		generalGuide.setFilePath("qqcoisa");
		generalGuide.setAuthor(users[0]);

		PositionEntity position1 = new PositionEntity();
		position1.setAdminCreator(users[0]);
		position1.setTitle("Technical manager for embedded systems");
		position1.setLocation(Location.COIMBRA);
		position1.setLocation(Location.OPORTO);
		position1.setPositionStatus(PositionStatus.OPEN);
		position1.setQuantity(2);
		position1.setCompany("Critical Software");
		position1.setTechnicalArea(TechnicalArea.INTEGRATION);
		position1.setDescriptionPosition(dp);
		position1.setSource(Source.CRITICAL_SOFTWARE_WEBSITE);
		position1.setSource(Source.FACEBOOK);
		position1.setOpenningDate(ft.parse("2015-08-01"));
		position1.setClosingDate(ft.parse("2015-10-31"));
		position1.setSla(8);
		position1.setGuide(generalGuide);
		position1.setManager(users[0]);

		CandidateEntity candidate1 = new CandidateEntity();
		candidate1.setFirstName("Rafaela	");
		candidate1.setLastName("Lourenço");
		candidate1.setBirthDate(ft.parse("1986-04-30"));
		candidate1.setEmail("lourencorafaela@gmail.com");
		candidate1.setPassword(encPass.encrypt("456"));
		candidate1.setMobilePhone((long) 912345678);
		candidate1.setPhone((long) 274111111);
		candidate1
				.setAddress("Travessa da Central Eléctrica nº2, 6100-654 Sertã");
		candidate1.setCity("Sertã");
		candidate1.setCountry("Portugal");
		candidate1.setCourse("Eng");
		candidate1.setSchool("FCUL");
		candidate1.setCvPath("qqcoisa");
		candidate1.setRole(rCAnd);
		// candidate1.setCandidatures(candidatures);

		ArrayList<CandidatureEntity> candidatures = new ArrayList<CandidatureEntity>();
		CandidatureEntity candidature1 = new CandidatureEntity("qqcoisaCV",
				"qqCoisaCoverLetter", ft.parse("2015-08-31"),
				CandidatureStatus.SUBMITTED);
		candidature1.setPosition(position1);
		candidature1.setPublicSource(Source.FACEBOOK);
		candidature1.setCandidate(candidate1);

		candidatures.add(candidature1);

		// for (RoleEntity rl : allRoles)
		// em.persist(rl);
		for (RoleEntity rl : rAdmin)
			em.persist(rl);
		for (RoleEntity rl : rCandidate)
			em.persist(rl);
		for (RoleEntity rl : rInterv)
			em.persist(rl);
		for (RoleEntity rl : rManager)
			em.persist(rl);

		for (RoleEntity rl : rAdminMan)
			em.persist(rl);
		for (UserEntity uE : users)
			em.persist(uE);
		em.persist(rCAnd);
		em.persist(generalGuide);
		em.persist(position1);
		em.persist(candidate1);
		for (CandidatureEntity c : candidatures)
			em.persist(c);

	}
}
