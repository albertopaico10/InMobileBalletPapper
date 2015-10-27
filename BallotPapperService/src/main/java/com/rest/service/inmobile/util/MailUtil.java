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
		props.setProperty("mail.transport.protocol" , beanEmail.getTransportProtocol()); 
        props.setProperty("mail.smtp.host", beanEmail.getEmailSmtp()); 
        props.setProperty("mail.smtp.port", beanEmail.getEmailPort()); 
        props.setProperty("mail.smtp.auth", beanEmail.getEmailTrue()); 
        
        Session session = Session.getInstance(props,   new javax.mail.Authenticator() {  
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(beanEmail.getEmailFrom(),beanEmail.getPasswordFrom());  
            }  
        }); 
        
        MimeMessage  mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(beanEmail.getEmailFrom()));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(beanEmail.getToEmail()));
        //mimeMessage.addRecipients(Message.RecipientType.BCC, new InternetAddress[] { new InternetAddress("albertopaico10@gmail.com") });
        mimeMessage.setSubject(beanEmail.getSubjectEmail());
        
        BodyPart texto = new MimeBodyPart();
        texto.setContent(beanEmail.getBodyEmail(),"text/html; charset=utf-8");
        MimeMultipart multiParte = new MimeMultipart("related");
        multiParte.addBodyPart(texto);
        
        mimeMessage.setContent(multiParte);
        
        
//        mimeMessage.setText(beanEmail.getBodyEmail());
        System.out.println("enviando.............");
        // Lo enviamos.
        Transport t = session.getTransport(beanEmail.getTransportProtocol());
        t.connect(beanEmail.getEmailFrom(),beanEmail.getPasswordFrom());
        t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        // Cierre.
        t.close();
		System.out.println("Done");

	}
	
	public static void sendEmail2(final EmailBean beanEmail)
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
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(beanEmail.getToEmail()));
		message.setSubject(beanEmail.getSubjectEmail());

		// Create the message body part
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(beanEmail.getBodyEmail(), "text/html; charset=utf-8");
		// Create a multipart message for attachment
		Multipart multipart = new MimeMultipart();
		// Set text message part
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		System.out.println("Before Send....!!!");
		Transport.send(message);

		System.out.println("Done");

	}
}
