package org.freecode.demo;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailTester {
    
    public static void main(String[] args) {
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "<SMTP Server>");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable", "false"); // for TLS

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("<User ID>", "<Password>");
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("<Sender Email>"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("<Recipient Email>"));
            message.setSubject("Test Email from App internally again");
            message.setText("This is the body of the email.");

            Transport.send(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
