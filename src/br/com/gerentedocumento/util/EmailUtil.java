package br.com.gerentedocumento.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;

import br.com.gerentedocumento.domain.Email;

public class EmailUtil {

	private static final String SMTP_SERVER_HOST = "smtp.gmail.com";
	private static final String SMTP_SERVER_PORT = "587";

	private Email email;

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	/**
	 * Envia um email passando apenas o token de acesso
	 * @param smtpUserAccessToken
	 */
	@SuppressWarnings("resource")
	public void sendMail( String smtpUserAccessToken) {
		try {
			Properties props = System.getProperties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.port", SMTP_SERVER_PORT);
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(this.usuarioEmail(this.getEmail().getFormEmail()), this.getEmail().getFormEmail()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(this.getEmail().getToEmail()));
			msg.setSubject(this.getEmail().getSubject());
			msg.setContent(this.getEmail().getMessage(), "text/html");

			SMTPTransport transport = new SMTPTransport(session, null);
			transport.connect(SMTP_SERVER_HOST, this.usuarioEmail(this.getEmail().getFormEmail()), null);
			transport.issueCommand(
					"AUTH XOAUTH2 " + new String(BASE64EncoderStream.encode(String
							.format("user=%s\1auth=Bearer %s\1\1", this.usuarioEmail(this.getEmail().getFormEmail()), smtpUserAccessToken).getBytes())),
					235);
			transport.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	/**
	 * Possuindo estes parâmetros o método retornará o token de acesso com validade de 1 hora.
	 * @param refreshToken
	 * @param clientId
	 * @param clientSecret
	 * @return token de acesso com validade de 1 hora.
	 * @throws IOException
	 */
	public String getNewToken(String refreshToken, String clientId, String clientSecret) throws IOException {
		ArrayList<String> scopes = new ArrayList<>();

		scopes.add(CalendarScopes.CALENDAR);

		/*TokenResponse tokenResponse = new GoogleRefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(),
				refreshToken, clientId, clientSecret).setScopes(scopes).setGrantType("refresh_token").execute();*/
		TokenResponse tokenResponse = new GoogleRefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(),
				refreshToken, clientId, clientSecret).execute();

		return tokenResponse.getAccessToken();
	}

	private String usuarioEmail(String userFullName) {
		// capturando o usuario do email ao dividir o remetente
		String emailArray[] = userFullName.split(Pattern.quote("@"));
		String usuarioEmail = emailArray[0];
		return usuarioEmail;
	}
}
