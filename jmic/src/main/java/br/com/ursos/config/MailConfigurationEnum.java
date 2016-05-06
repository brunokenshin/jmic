package br.com.ursos.config;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;


public enum MailConfigurationEnum {

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
        String propertyValue = props.getProperty(config.configName);
       
        if (StringUtils.isBlank(propertyValue)) {
            return "";
        }
        return propertyValue;
	}
	
}
