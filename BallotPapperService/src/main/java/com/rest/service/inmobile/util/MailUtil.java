package com.rest.service.inmobile.util;

import java.util.Properties;

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

public class MailUtil {

	public static void sendEmail(String to, String subject, String body)
			throws MessagingException {
		System.out.println("El correo va para : " + to + "/ Asunto : " + subject);

		Properties props = new Properties();
		props.put("mail.smtp.auth", CommonConstants.Email.EMAIL_TRUE);
		props.put("mail.smtp.starttls.enable", CommonConstants.Email.EMAIL_TRUE);
		props.put("mail.smtp.host", CommonConstants.Email.EMAIL_SMTP_GMAIL);
		props.put("mail.smtp.port", CommonConstants.Email.EMAIL_PORT_GMAIL);
		props.put("mail.smtp.ssl.trust", CommonConstants.Email.EMAIL_SMTP_GMAIL);

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								CommonConstants.Email.EMAIL_FROM,
								CommonConstants.Email.PASSWORD_FROM);
					}
				});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(CommonConstants.Email.EMAIL_FROM));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
		message.setSubject(subject);

		// Create the message body part
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(body, "text/html");
		// Create a multipart message for attachment
		Multipart multipart = new MimeMultipart();
		// Set text message part
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);

		Transport.send(message);

		System.out.println("Done");

	}

}
