package br.com.gerentedocumento.domain;

public class Email {

	private String formEmail;
	private String toEmail;
	private String subject;
	private String message;
	
	public String getFormEmail() {
		return formEmail;
	}
	public void setFormEmail(String formEmail) {
		this.formEmail = formEmail;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Email [formEmail=" + formEmail + ", toEmail=" + toEmail + ", subject=" + subject + ", message="
				+ message + "]";
	}
	
	
}
