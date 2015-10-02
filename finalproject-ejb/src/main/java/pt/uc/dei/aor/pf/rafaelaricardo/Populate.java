package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import pt.uc.dei.aor.pf.rafaelaricardo.entities.InterviewEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.RoleEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.CandidatureStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.InterviewStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Role;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

@Startup
@Singleton
public class Populate implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(Populate.class);

	@PersistenceContext(name = "FinalProject")
	private EntityManager em;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	@Inject
	private EncryptPass encPass;

	@PostConstruct
	void populando() throws ParseException {

		log.info("Populating the DB");

		// Creating Roles
		ArrayList<RoleEntity> rAdmin = new ArrayList<RoleEntity>();
		rAdmin.add(new RoleEntity(Role.ADMIN));
		ArrayList<RoleEntity> rInterv = new ArrayList<RoleEntity>();
		rInterv.add(new RoleEntity(Role.INTERVIEWER));
		ArrayList<RoleEntity> rManager = new ArrayList<RoleEntity>();
		rManager.add(new RoleEntity(Role.MANAGER));
		RoleEntity rCAnd = new RoleEntity(Role.CANDIDATE);

		// Creating Users
		UserEntity[] users = {
				new UserEntity("admin", "das couves", "admin@gmail.com",
						encPass.encrypt("123"), rAdmin),
						new UserEntity("manager", "das couves", "manager@gmail.com",
								encPass.encrypt("123"), rManager),
								new UserEntity("interviewer", "das couves", "interv@gmail.com",
										encPass.encrypt("123"), rInterv) };

		// Creating Positions
		// Position1
		DescriptionPosition dp = new DescriptionPosition();
		dp.setDescription("<p><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Working for us is like nothing on earth. Every day, our teams across the globe challenge the limits of human achievement, engineering solutions for our planet and beyond.<br>"
				+ "<br>Our astronomically talented engineers build rock-solid software for leading industries’ most critical applications. Now, we’re looking for talented <span style=\"color: rgb(178, 34, 34);\">Technical Managers for Embedded Systems</span> to join our Systems and Software Engineering team in breaching the frontiers of space, aerospace and defence.<br><br>Across international projects, you’ll be working on some of the most critical software applications developing today, as part of a global team with more than 15 years’ experience working with embedded software and systems.<br><br>Sound like you? Then it’s time to challenge your limits. It’s time to give your career the rocket boost it deserves. It’s time you joined CRITICAL Software!</span></p>");

		dp.setKeyResponsabilities("<ul>	<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Undertaking software development for embedded systems</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Performing Verification &amp; Validation testing of embedded systems</span></li>"
				+ "	<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Leading a team through your technical expertise (task allocation, status reporting, and people management)</span></li></ul>");

		dp.setRequiredQualifications("<ul><li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">More than 7 years’ experience working in software development and embedded systems engineering</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Deep knowledge of different Operating Systems, particularly the concepts of real-time and embedded systems, computer architectures and programming languages (C/C++, Ada95)</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Full-lifecycle software development experience, from initial requirements elicitation to design, coding, testing, documentation, implementation, integration and training</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Teamwork and technical project management experience</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Strong leadership, problem-solving and communication skills</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Proficiency in English</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Good organisational skills</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Availability to travel, mainly Europe (United Kingdom, Germany, France)</span></li></ul>");

		dp.setDesiredQualifications("<ul><li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Knowledge of embedded software development in microcontrollers</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Knowledge of safety-critical standards (for example, ECSS, DO-178B/C, ISO 26262, IEC 61508)</span></li>"
				+ "<li><span style=\"line-height: 1.6em; font-family: Arial, Helvetica, sans-serif; font-size: 14px\">Knowledge of system development using model-based development techniques and tools (for example, SCADE and MATLAB)</span></li></ul>");

		GuideEntity generalGuide = new GuideEntity();
		generalGuide.setGuideTitle("Guide position1");
		generalGuide.setGuideDate(ft.parse("2015-05-01"));
		generalGuide
		.setFilePath("C:\\Program Files\\wildfly-8.2.0.Final\\wildfly-8.2.0.Final\\ProjFinalUploadedFiles\\Guide\\Guide_guide position1_2015-09-30 15-47-43_guide.xlsx");
		generalGuide.setAuthor(users[0]);

		Set<Source> sourcesPos1 = new HashSet<Source>();
		sourcesPos1.add(Source.CRITICAL_SOFTWARE_WEBSITE);
		sourcesPos1.add(Source.FACEBOOK);
		sourcesPos1.add(Source.GLASSDOOR);
		sourcesPos1.add(Source.LINKEDIN);

		Set<Location> localPos1 = new HashSet<Location>();
		localPos1.add(Location.COIMBRA);
		localPos1.add(Location.OPORTO);

		PositionEntity position1 = new PositionEntity();
		position1.setAdminCreator(users[0]);
		position1.setTitle("Technical manager for embedded systems");
		position1.setLocation(localPos1);
		position1.setPositionStatus(PositionStatus.OPEN);
		position1.setQuantity(2);
		position1.setCompany("Critical Software");
		position1.setTechnicalArea(TechnicalArea.INTEGRATION);
		position1.setDescriptionPosition(dp);
		position1.setSource(sourcesPos1);
		position1.setOpenningDate(ft.parse("2015-08-01"));
		position1.setClosingDate(ft.parse("2015-10-31"));
		position1.setSla(8);
		position1.setGuide(generalGuide);
		position1.setManager(users[1]);

		// Position2
		DescriptionPosition dp2 = new DescriptionPosition();
		dp2.setDescription("<p style=\"margin-bottom: 6.75pt;line-height: normal;\">"
				+ "<span style=\"color: black;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Working for us is like nothing on earth! Every day, our teams challenge the limits of human achievement, engineering solutions for our planet and beyond. Our astronomically talented engineers build rock-solid software for leading industries’ most critical applications.<br><br>Now, we’re looking for a talented </span></span></span>"
				+ "<span style=\"color: rgb(192, 0, 0); font-family: arial, sans-serif; font-size: 14px; line-height: normal;\">.Net&nbsp;Software Engineer</span>"
				+ "<span style=\"color: black;\">"
				+ "<span style=\"font-family: arial , sans-serif;\"> "
				+ "<span style=\"font-size: 10.5pt;\"> to join our team.</span></span></span>"
				+ "<br><br><span style=\"color: black;\">"
				+ "<span style=\"font-family: tahoma , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">This is a great opportunity to join a high tech international company that genuinely cares about its employees and has recently become “Employer of the Year” in local business awards.&nbsp; The business has customers in the aerospace, energy and defence sectors that demand high integrity and complex systems to meet their challenging requirements.</span></span></span>"
				+ "<br><br><span style=\"color: black;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">It’s time to challenge your limits. It’s time to give your career the rocket boost it deserves. </span></span></span>"
				+ "<span style=\"color: black;\"> <span style=\"font-family: arial , sans-serif;\"> <span style=\"font-size: 10.5pt;\">It’s time you joined </span></span></span>"
				+ "<span style=\"color: rgb(178, 34, 34); font-family: arial, sans-serif; font-size: 14px; line-height: normal;\">CRITICAL Software</span>"
				+ "<span style=\"color: black;\"><span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">!</span></span></span></p>"
				+ "	<p style=\"margin-bottom: 6.75pt;line-height: normal;\">"
				+ "<strong style=\"color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 1.6em;\">"
				+ "<span style=\"color: rgb(178, 34, 34);\">WHAT WE OFFER:</span></strong></p>"
				+ "<ul><li><span style=\"color: black;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">A high-integrity systems and software engineering company that creates and deploys innovative software solutions</span></span></span></li><li><span style=\"color: black;\"><span style=\"font-family: arial , sans-serif;\"><span style=\"font-size: 10.5pt;\">Delivery of high-quality work across a diverse array of market sectors</span></span></span></li><li><span style=\"color: black;\"><span style=\"font-family: arial , sans-serif;\"><span style=\"font-size: 10.5pt;\">An in-depth knowledge and partnership approach, which together ensures excellent results in the development of solutions that simply must not fail </span></span></span></li><li><span style=\"color: black;\"><span style=\"font-family: tahoma , sans-serif;\"><span style=\"font-size: 10.5pt;\">The benefits package is extensive, starting with pension and life insurance and continuing with a menu that includes healthcare, dental care, payment of fees for membership of professional bodies, gym membership, travel insurance and mobile phone allowance</span></span></span></li><li><span style=\"color: black;\"><span style=\"font-family: helvetica , sans-serif;\"><span style=\"font-size: 10.5pt;\">Very flexible working scheme </span></span></span></li><li><span style=\"color: black;\"><span style=\"font-family: helvetica , sans-serif;\"><span style=\"font-size: 10.5pt;\">Staff training and social events throughout the year</span></span></span></li></ul>");
		dp2.setKeyResponsabilities("<p><span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">The successful candidate will be expected to undertake a variety of duties. These include, but are not limited to:</span></span></p>"
				+ "<ul><li style=\"margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;line-height: normal;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Performing software engineering tasks to a high standard, including software requirements, design, development and testing&nbsp; </span></span></li>"
				+ "<li style=\"margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;line-height: normal;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Configuration management</span></span></li>"
				+ "<li style=\"margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;line-height: normal;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Development and maintenance of data visualisation software in web technologies</span></span></li>"
				+ "<li style=\"margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;line-height: normal;\">"
				+ "<span style=\"font-family: arial , sans-serif;\"><span style=\"font-size: 10.5pt;\">Development and maintenance of data loading processes (ETL)</span></span></li>"
				+ "<li style=\"margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;line-height: normal;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Business Intelligence development from ETL through to report development&nbsp;&nbsp; </span></span></li>"
				+ "<li style=\"margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;line-height: normal;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Statistical and mathematical modelling of data </span></span></li></ul>"
				+ "<p style=\"text-align: justify;\"><br><span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">You will need to be very client focused and feel comfortable in understanding and acting on customer needs. You will also be expected to contribute to bid work.</span></span></p>");

		dp2.setDesiredQualifications("<ul><li>"
				+ "<span style=\"color: black;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Microsoft Certified Developer</span></span></span></li>"
				+ "<li style=\"margin-top: 0.0cm;margin-right: 0.0cm;margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Exposure to agile development</span></span></li>"
				+ "<li style=\"margin-top: 0.0cm;margin-right: 0.0cm;margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Experience of dealing with customers and staff</span></span></li>"
				+ "<li style=\"margin-top: 0.0cm;margin-right: 0.0cm;margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Web development skills (e.g. AngularJs, JQuery, HTML 5, CSS, JavaScript, LESS, SASS &amp; Knockout)</span></span></li>"
				+ "<li style=\"margin-top: 0.0cm;margin-right: 0.0cm;margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Experience with ORM (e.g. Entity Framework and NHibernate)</span></span></li><li style=\"margin-top: 0.0cm;margin-right: 0.0cm;margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;\">"
				+ "<span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Database development and SQL</span></span></li><li style=\"margin-top: 0.0cm;margin-right: 0.0cm;margin-bottom: 0.0cm;margin-bottom: 1.0E-4pt;\">"
				+ "<span style=\"font-family: arial , sans-serif;\"><span style=\"font-size: 10.5pt;\">Experience within the aerospace, defence, energy or transport sectors</span></span></li><li><span style=\"font-family: arial , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Other programming languages</span></span></li></ul>");
		dp2.setRequiredQualifications("<ul><li><span style=\"color: black;\"><span style=\"font-family: helvetica , sans-serif;\"><span style=\"font-size: 10.5pt;\">Educated to degree level in a computing or mathematically based subject achieving a 2:1 or higher </span></span></span></li>"
				+ "<li><span style=\"color: black;\"><span style=\"font-family: helvetica , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Experience and ability to demonstrate knowledge with developing web applications using the Microsoft Stack: Visual Studio 2012+, ASP.NET, C# and SQL Server 2008+</span></span></span></li><li><span style=\"color: black;\">"
				+ "<span style=\"font-family: helvetica , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Strong object-oriented development skills</span></span></span>"
				+ "</li><li><span style=\"color: black;\"><span style=\"font-family: helvetica , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Design experience using UML and design patterns</span></span></span></li><li><span style=\"color: black;\"><span style=\"font-family: helvetica , sans-serif;\">"
				+ "<span style=\"font-size: 10.5pt;\">Testing at module and system level (creating test scripts and procedures)</span></span></span></li>"
				+ "<li><span style=\"color: black;\"><span style=\"font-family: helvetica , sans-serif;\"><span style=\"font-size: 10.5pt;\">Configuration management</span></span></span></li></ul>");

		GuideEntity generalGuide2 = new GuideEntity();
		generalGuide2.setGuideTitle("Guide Position2");
		generalGuide2.setGuideDate(ft.parse("2015-06-01"));
		generalGuide2
		.setFilePath("C:\\Program Files\\wildfly-8.2.0.Final\\wildfly-8.2.0.Final\\ProjFinalUploadedFiles\\Guide\\Guide_guide position2_2015-09-30 21-28-46_guide.xlsx");
		generalGuide2.setAuthor(users[0]);

		Set<Source> sourcesPos2 = new HashSet<Source>();
		sourcesPos2.add(Source.CRITICAL_SOFTWARE_WEBSITE);
		sourcesPos2.add(Source.LINKEDIN);

		Set<Location> localPos2 = new HashSet<Location>();
		localPos2.add(Location.COIMBRA);
		localPos2.add(Location.CLIENT);
		localPos2.add(Location.OPORTO);

		PositionEntity position2 = new PositionEntity();
		position2.setAdminCreator(users[0]);
		position2.setTitle(".NET SOFTWARE ENGINEER");
		position2.setLocation(localPos2);
		position2.setPositionStatus(PositionStatus.OPEN);
		position2.setQuantity(1);
		position2.setCompany("Critical Software");
		position2.setTechnicalArea(TechnicalArea.NET_DEVELOPMENT);
		position2.setDescriptionPosition(dp2);
		position2.setSource(sourcesPos2);
		position2.setOpenningDate(ft.parse("2015-09-01"));
		position2.setClosingDate(ft.parse("2015-11-31"));
		position2.setSla(8);
		position2.setGuide(generalGuide2);
		position2.setManager(users[1]);

		// Create Candidate
		// Candidate1
		CandidateEntity candidate1 = new CandidateEntity();
		candidate1.setFirstName("Rafaela");
		candidate1.setLastName("Lourenço");
		candidate1.setBirthDate(ft.parse("1986-04-30"));
		candidate1.setEmail("rafaela@gmail.com");
		candidate1.setPassword(encPass.encrypt("456"));
		candidate1.setMobilePhone((long) 912345678);
		candidate1.setPhone((long) 274111111);
		candidate1
		.setAddress("Travessa da Central Eléctrica nº2, 6100-654 Sertã");
		candidate1.setCity("Sertã");
		candidate1.setCountry("Portugal");
		candidate1.setCourse("Eng");
		candidate1.setSchool("FCUL");
		candidate1
		.setCvPath("C:\\Program Files\\wildfly-8.2.0.Final\\wildfly-8.2.0.Final\\ProjFinalUploadedFiles\\CV\\CV_rafaela@gmail.com_2015-10-01 12-23-41_CV_Rafaela_Lourenco.pdf");
		candidate1.setRole(rCAnd);

		Set<Source> sourceCand1 = new HashSet<Source>();
		sourceCand1.add(Source.FACEBOOK);

		// Candidate2
		CandidateEntity candidate2 = new CandidateEntity();
		candidate2.setFirstName("Ricardo");
		candidate2.setLastName("Alves");
		candidate2.setBirthDate(ft.parse("1983-03-24"));
		candidate2.setEmail("ricardo@gmail.com");
		candidate2.setPassword(encPass.encrypt("456"));
		candidate2.setMobilePhone((long) 925874913);
		candidate2.setPhone((long) 274111111);
		candidate2.setAddress("algures na serta");
		candidate2.setCity("Sertã");
		candidate2.setCountry("Portugal");
		candidate2.setCourse("Eng Bioquimica");
		candidate2.setSchool("FCUL");
		candidate2
		.setCvPath("C:\\Program Files\\wildfly-8.2.0.Final\\wildfly-8.2.0.Final\\ProjFinalUploadedFiles\\CV\\CV_ricardo@gmail.com_2015-10-01 12-21-36_CV Ricardo Alves.pdf");
		candidate2.setRole(rCAnd);

		// Candidatures
		// candidatures candidate1
		ArrayList<CandidatureEntity> candidatures = new ArrayList<CandidatureEntity>();
		CandidatureEntity candidature1 = new CandidatureEntity("qqcoisaCV",
				"qqCoisaCoverLetter", ft.parse("2015-08-31"),
				CandidatureStatus.SUBMITTED);
		candidature1.setPosition(position1);
		candidature1.setPublicSource(sourceCand1);
		candidature1.setCandidate(candidate1);
		candidatures.add(candidature1);

		// spontaneous candidate2 (no position or source
		List<CandidatureEntity> candSP = new ArrayList<CandidatureEntity>();
		CandidatureEntity candidatureSP1 = new CandidatureEntity("spont",
				"spont", ft.parse("2015-08-31"), CandidatureStatus.SUBMITTED);
		candidatureSP1.setCandidate(candidate2);
		candSP.add(candidatureSP1);

		// creating interviews
		// List<UserEntity> interviewers = new ArrayList<UserEntity>();
		// interviewers.add(users[2]);
		List<InterviewEntity> interviews = new ArrayList<InterviewEntity>();
		InterviewEntity interv = new InterviewEntity(ft.parse("2015-12-31"),
				InterviewStatus.SCHEDULED);
		interv.setCandidature(candidature1);
		// interv.setInterviewers(interviewers);
		interviews.add(interv);
		users[2].setInterviews(interviews);

		// Persisting all creations
		// persisting roles
		for (RoleEntity rl : rAdmin)
			em.persist(rl);
		for (RoleEntity rl : rInterv)
			em.persist(rl);
		for (RoleEntity rl : rManager)
			em.persist(rl);
		em.persist(rCAnd);
		// persisting users
		for (UserEntity uE : users)
			em.persist(uE);

		// persisting candidates
		em.persist(candidate1);
		em.persist(candidate2);
		// persisting guides
		em.persist(generalGuide);
		em.persist(generalGuide2);
		// persisting positions
		em.persist(position1);
		em.persist(position2);
		// persisting candidatures
		for (CandidatureEntity c : candidatures)
			em.persist(c);
		for (CandidatureEntity c : candSP)
			em.persist(c);
		// persisting interviews
		em.persist(interv);

	}
}
