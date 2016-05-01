package br.com.ursos.config;

import java.util.Properties;

public enum MailConfigurationEnum {

    // MAIL CONFIGS
    HOST("host"),
    INBOX_FOLDER("inboxfolder"),
    USERNAME("username"),
    PASSWORD("password"),
    PROTOCOL("mail.store.protocol"),
    SSL_ENABLE("mail.imap.ssl.enable"),
    SOCKET_FACTORY_CLASS("mail.imap.socketFactory.class"),
    SOCKET_FACTORY_FALLBACK("mail.imap.socketFactory.fallback"),
    SOCKET_FACTORY_PORT("mail.imap.socketFactory.port"),
    FILTER_DAYS_AGO("filter.daysago"),
    FILTER_SENDER("filter.sender"),
    FILTER_SUBJECT("filter.subject"),
    FILTER_UNREAD("filter.unread");

    public final String configName;

    private MailConfigurationEnum(String configName) {
        this.configName = configName;
    }

	public static String getConfigValue(Properties props, MailConfigurationEnum config) {
		try {
			return props.getProperty(config.configName);
		} catch (Exception e) {
			throw new RuntimeException("Config not Found: " + config.configName);
		}
	}
	
}
