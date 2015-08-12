package com.rest.service.inmobile.bean.email;

public class EmailBean {
	private String emailFrom;
	private String passwordFrom;
	private String subjectEmail;
	private String emailTrue;
	private String emailSmtp;
	private String emailPort;
	private String bodyEmail;
	private String toEmail;
	
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getPasswordFrom() {
		return passwordFrom;
	}
	public void setPasswordFrom(String passwordFrom) {
		this.passwordFrom = passwordFrom;
	}
	public String getSubjectEmail() {
		return subjectEmail;
	}
	public void setSubjectEmail(String subjectEmail) {
		this.subjectEmail = subjectEmail;
	}
	public String getEmailTrue() {
		return emailTrue;
	}
	public void setEmailTrue(String emailTrue) {
		this.emailTrue = emailTrue;
	}
	public String getEmailSmtp() {
		return emailSmtp;
	}
	public void setEmailSmtp(String emailSmtp) {
		this.emailSmtp = emailSmtp;
	}
	public String getEmailPort() {
		return emailPort;
	}
	public void setEmailPort(String emailPort) {
		this.emailPort = emailPort;
	}
	public String getBodyEmail() {
		return bodyEmail;
	}
	public void setBodyEmail(String bodyEmail) {
		this.bodyEmail = bodyEmail;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
}
