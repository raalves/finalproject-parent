package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
public class WriteEmails implements Serializable {

	private static final long serialVersionUID = -2242189615390768212L;
	private static final Logger log = LoggerFactory
			.getLogger(WriteEmails.class);

	private String mail;

	public String notificationNewCandidatureAssociation(String positionTitle) {
		log.info("generating email for manager");
		mail = "<div><div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\">Hello</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\">A candidature was associated for position:\""
				+ positionTitle
				+ "\".</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\">Regards,</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\">The system management</span></font></div>"
				+ "<div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal;\"><br></div></div>"
				+ "<div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal;\"><br></div>";
		return mail;
	}

	public String notificationNewCandidatureCandidate(String positionTitle) {
		log.info("generating email for manager");
		mail = "<div><div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\">Hello</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\">A candidature was performed by candidate for position :\""
				+ positionTitle
				+ "\".</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\">Regards,</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\"><span style=\"font-size: 13.3333px;\">The system management</span></font></div>"
				+ "<div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal;\"><br></div></div>"
				+ "<div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal;\"><br></div>";
		return mail;
	}

	public String notificationInterv(String title, Date interviewDate) {
		log.info("generating email for interviewers");

		mail = "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">Hello</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">A new interview is scheduledfor the day: "
				+ interviewDate
				+ ".</span></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">The position is \""
				+ title
				+ "\"</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">In attachment follows the cv of the candidate and the guide for the interview.</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">Regards,</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">The management system</span></font></div>";

		return mail;
	}

	public String notificationCandidate(String title, String fistName,
			String lastName, Date interviewDate) {
		log.info("generating email for candidate");
		mail = "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">Hello "
				+ fistName
				+ " "
				+ lastName
				+ "</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">A interview for your application for the position \""
				+ title
				+ "\" is scheduled for the day: "
				+ interviewDate
				+ ".</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">Please contact us confirming of your disponibility  through our general email: geral@application.com.</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">Regards,</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">The management system</span></font></div>";
		return mail;
	}

	public String notificationManager(String positionTitle, Date openningDate, String admin) {
		log.info("generating email for interviewers");

		mail = "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">Hello</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">A new position was associated to you with the openning date: "
				+ openningDate
				+ ".</span></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">The position is: \""
				+ positionTitle
				+ "\"</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\"><br></span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">Regards,</span></font></div>"
				+ "<div><font face=\"Arial, Verdana\">"
				+ "<span style=\"font-size: 13.3333px;\">ADMIN - \""
				+ admin
				+ "\"</span></font></div>";

		return mail;
	}
}
