package br.com.gerentedocumento.util;

import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testEmail{
	
	private static final String SMTP_SERVER_HOST = "smtp.gmail.com";
    private static final String SMTP_SERVER_PORT = "587";
    private static final String SUBJECT = "Sending mail with Gmail SMTP and Java Mail";
    private static final String BODY = "Hi,<br><br>This is a programmatic email.";
    
    void sendMail(String smtpServerHost, String smtpServerPort,  String smtpUserName, String smtpUserAccessToken, String fromUserEmail, String fromUserFullName, String toEmail, String subject, String body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", smtpServerPort);
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromUserEmail, fromUserFullName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            @SuppressWarnings("resource")
			SMTPTransport transport = new SMTPTransport(session, null);
            transport.connect(smtpServerHost, smtpUserName, null);
            transport.issueCommand("AUTH XOAUTH2 " + new String(BASE64EncoderStream.encode(String.format("user=%s\1auth=Bearer %s\1\1", smtpUserName, smtpUserAccessToken).getBytes())), 235);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    public static void main(String[] args){
    	 final String FROM_USER_EMAIL = "controle.interno.java";
         final String FROM_USER_FULLNAME = "controle.interno.java@gmail.com";
         final String FROM_USER_ACCESSTOKEN = "ya29.a0AfH6SMA4F6skPuPktGOD1stUbxqPpMIEyWSlYGAU5XNMsyH-DMeqjb3pp5B7mXs8CftPfi1hZJlO6joV0DY2aRDOf0r6mafVC9QqBYZmvSO23iAGKGES55yeyUtDCZ8y3mZPa5xvd_DzF1hQtOsAzSaVeJP6P3kGA0s";
         final String TO_USER_EMAIL = "gustavodias1988@hotmail.com";

         new testEmail().sendMail(SMTP_SERVER_HOST, SMTP_SERVER_PORT, FROM_USER_EMAIL, FROM_USER_ACCESSTOKEN, FROM_USER_EMAIL, FROM_USER_FULLNAME, TO_USER_EMAIL, SUBJECT, BODY);
    }
}
