package in.ayush.service;

public interface EmailService {

	// public boolean sendMail(String toEmail, String subject, String message);

	public boolean email(byte[] coPdf, String mailTo, String body, String subject);
}
