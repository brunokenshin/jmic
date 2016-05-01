package br.com.ursos.utils;

import static br.com.ursos.config.MailConfigurationEnum.HOST;
import static br.com.ursos.config.MailConfigurationEnum.INBOX_FOLDER;
import static br.com.ursos.config.MailConfigurationEnum.PASSWORD;
import static br.com.ursos.config.MailConfigurationEnum.PROTOCOL;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_CLASS;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_FALLBACK;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_PORT;
import static br.com.ursos.config.MailConfigurationEnum.SSL_ENABLE;
import static br.com.ursos.config.MailConfigurationEnum.USERNAME;

import java.util.Properties;

public class TestObjects {

	public static Properties createProperties() {
		Properties properties = new Properties();
		properties.setProperty(HOST.name(), "imap.wayne.com");
		properties.setProperty(INBOX_FOLDER.name(), "BATBOX");
		properties.setProperty(USERNAME.name(), "bruce.wayne");
		properties.setProperty(PASSWORD.name(), "waynecorp");
		properties.setProperty(PROTOCOL.name(), "imaps");
		properties.setProperty(SSL_ENABLE.name(), "true");
		properties.setProperty(SOCKET_FACTORY_CLASS.name(), "BatClass");
		properties.setProperty(SOCKET_FACTORY_FALLBACK.name(), "false");
		properties.setProperty(SOCKET_FACTORY_PORT.name(), "666");
		return properties;
	}

	public static Properties createPropertiesForHost(String host) {
		Properties props = createProperties();
		props.setProperty(HOST.name(), host);
		return props;
	}

	public static Properties createPropertiesForProtocol(String protocol) {
		Properties props = createProperties();
		props.setProperty(PROTOCOL.name(), protocol);
		return props;
	}

}
