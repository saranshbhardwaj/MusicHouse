/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sony
 */
public class Emailserver {
    
            public static void activateUser(String name, String email, String token,String link){
		final String username = "saranshbhardwaj@gmail.com";
		final String password = "fbd2525105";
		String[] to = { email };
		Properties prpty = new Properties();
		prpty.put("mail.smtp.auth", "true");
		prpty.put("mail.smtp.starttls.enable", "true");
		prpty.put("mail.smtp.host", "smtp.gmail.com");
		prpty.put("mail.smtp.port", "587");
		Session session = Session.getInstance(prpty, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress("saranshbhardwaj@gmail.com");
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("Activation email");
			message.setText("Hi "+ name  + ",\n" 
					+ "\nPlease click on the link below to activate your account "+".\n\n"
					+ link + "?action=activate&token="+token
                                        + "\n\n Thank You");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
            
            public static void resetPassword(String name, String email, String token,String link){
            
                final String username = "saranshbhardwaj@gmail.com";
                final String password = "fbd2525105";
                String[] email1 = { email };
                Properties prpt = new Properties();
                prpt.put("mail.smtp.auth", "true");
		prpt.put("mail.smtp.starttls.enable", "true");
		prpt.put("mail.smtp.host", "smtp.gmail.com");
		prpt.put("mail.smtp.port", "587");
                Session session = Session.getInstance(prpt, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
                
                try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress("saranshbhardwaj@gmail.com");
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < email1.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email1[i]));
			}
			message.setSubject("Reset Password");
			message.setText("Hi "+ name  + ",\n\n" 
					+ "\nPlease hit on the below mentioned link to reset your password .\n\n"
					+ link + "?action=reset&token="+token
					+ "\n\nThank and Regards");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
            }
    
}
