package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.Serializable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
public class EnvioMail implements Serializable {
	// capfrlra@gmail.com
	// projfinal123

	private static final long serialVersionUID = 3963092205902032139L;
	private static final Logger log = LoggerFactory.getLogger(EnvioMail.class);

	// public static void main(String[] args) {
	public void sendMail(String emailTo, String subject, String mail,
			String cvpath, String guidepath) {

		emailTo = "lourencorafaela@gmail.com";
		log.info("Sending email to:" + emailTo + " - Subject: " + subject);
		// Recipient's email ID needs to be mentioned.

		// String to = "lourencorafaela@gmail.com";// change accordingly

		// Sender's email ID needs to be mentioned
		String from = "capfrlra@gmail.com";// change accordingly
		// String from = "Application Candidature Managemen";
		final String username = "capfrlra@gmail.com";// change accordingly
		final String password = "projfinal123";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailTo));

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			// messageBodyPart.setText(mail);
			messageBodyPart.setContent(mail, "text/html");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			if (cvpath != null) {
				// Part two is attachment CV
				messageBodyPart = new MimeBodyPart();
				String filename = cvpath;
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
				multipart.addBodyPart(messageBodyPart);

				// Part two is attachment guide
				if (guidepath != null) {
					messageBodyPart = new MimeBodyPart();
					String filename2 = guidepath;
					DataSource source2 = new FileDataSource(filename2);
					messageBodyPart.setDataHandler(new DataHandler(source2));
					messageBodyPart.setFileName(filename2);
					multipart.addBodyPart(messageBodyPart);
				}
			}
			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");
			log.info("Email Sent");
		} catch (MessagingException e) {
			log.error("Error sending email: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
