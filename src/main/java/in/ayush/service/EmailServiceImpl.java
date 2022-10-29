package in.ayush.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public boolean email(byte[] coPdf, String mailTo, String body, String subject) {
		String smtpHost = "gmail.com"; // replace this with a valid host
		int smtpPort = 587; // replace this with a valid port

		String sender = "aysh3256@gmail.com"; // replace this with a valid sender email address

		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		Session session = Session.getInstance(properties);

		try {
			// construct the text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(body);

			// now write the PDF content to the output stream
			/*
			 * outputStream = new ByteArrayOutputStream(); writePdf(outputStream); byte[]
			 * bytes = outputStream.toByteArray();
			 */

			// construct the pdf body part
			DataSource dataSource = new ByteArrayDataSource(coPdf, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("statusreport.pdf");

			// construct the mime multi part
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			// create the sender/recipient addresses
			InternetAddress iaSender = new InternetAddress(sender);
			InternetAddress iaRecipient = new InternetAddress(mailTo);

			// construct the mime message
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setSender(iaSender);
			mimeMessage.setSubject(subject);
			mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
			mimeMessage.setContent(mimeMultipart);

			// send off the email
			Transport.send(mimeMessage);

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

}
