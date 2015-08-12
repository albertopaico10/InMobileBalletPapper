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

import com.rest.service.inmobile.bean.email.EmailBean;

public class MailUtil {

	public static void sendEmail(final EmailBean beanEmail)
			throws MessagingException {
		System.out.println("El correo va para : " + beanEmail.getToEmail() + "/ Asunto : "+beanEmail.getSubjectEmail());

		Properties props = new Properties();
		props.put("mail.smtp.auth", beanEmail.getEmailTrue());
		props.put("mail.smtp.starttls.enable", beanEmail.getEmailTrue());
		props.put("mail.smtp.host", beanEmail.getEmailSmtp());
		props.put("mail.smtp.port", beanEmail.getEmailPort());
		props.put("mail.smtp.ssl.trust", beanEmail.getEmailSmtp());

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								beanEmail.getEmailFrom(),
								beanEmail.getPasswordFrom());
					}
				});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(beanEmail.getEmailFrom()));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(beanEmail.getToEmail()));
		message.setSubject(beanEmail.getSubjectEmail());

		// Create the message body part
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(beanEmail.getBodyEmail(), "text/html");
		// Create a multipart message for attachment
		Multipart multipart = new MimeMultipart();
		// Set text message part
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);

		Transport.send(message);

		System.out.println("Done");

	}

}
