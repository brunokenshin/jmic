package br.com.ursos.config;

public class MailFilterConfigs {

	public final String subject;
	public final String unread;
	public final String sender;
	public final String daysAgo;

	public MailFilterConfigs(String subject, String sender, String unread, String daysAgo) {
		this.subject = subject;
		this.sender = sender;
		this.unread = unread;
		this.daysAgo = daysAgo;
	}
}
