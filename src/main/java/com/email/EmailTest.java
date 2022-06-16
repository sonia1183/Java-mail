package com.email;

import java.io.IOException;
import java.util.Properties;

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

public class EmailTest {
	public static void main(String[] args) throws IOException {
		final String username="***";
		final String password="***";
		String fromEmail="***";
		String toEmail = "***";
		
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.user", username);
		properties.setProperty("mail.smtp.password", password);
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		
		//Properties props = mailProperties();
      Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(
                  username, password);// Specify the Username and the PassWord
          }
      });

		
		MimeMessage msg =new MimeMessage(session);
		
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("Subject Line");
			
			Multipart emailContent = new MimeMultipart();
			MimeBodyPart textBodypart = new MimeBodyPart();
			
			textBodypart.setText("text");
			
			MimeBodyPart htmlAttachment = new MimeBodyPart();
			htmlAttachment.attachFile("__path");
			
			emailContent.addBodyPart(textBodypart);
			emailContent.addBodyPart(htmlAttachment);
			
			msg.setContent(emailContent);
			
			Transport.send(msg);
			System.out.println("sent mail");
		} catch (MessagingException e){
			e.printStackTrace();
		}
	}
}