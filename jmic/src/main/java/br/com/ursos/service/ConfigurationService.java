package br.com.ursos.service;

import static br.com.ursos.config.MailConfigurationEnum.FILTER_DAYS_AGO;
import static br.com.ursos.config.MailConfigurationEnum.FILTER_SENDER;
import static br.com.ursos.config.MailConfigurationEnum.FILTER_SUBJECT;
import static br.com.ursos.config.MailConfigurationEnum.FILTER_UNREAD;
import static br.com.ursos.config.MailConfigurationEnum.HOST;
import static br.com.ursos.config.MailConfigurationEnum.INBOX_FOLDER;
import static br.com.ursos.config.MailConfigurationEnum.PASSWORD;
import static br.com.ursos.config.MailConfigurationEnum.PROTOCOL;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_CLASS;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_FALLBACK;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_PORT;
import static br.com.ursos.config.MailConfigurationEnum.SSL_ENABLE;
import static br.com.ursos.config.MailConfigurationEnum.USERNAME;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ursos.config.ExportConfig;
import br.com.ursos.config.MailConfig;
import br.com.ursos.config.MailFilterConfigs;
import br.com.ursos.config.ParserFieldConfig;
import br.com.ursos.persistance.ConfigDao;

@Component
public class ConfigurationService {

	private ConfigDao configDao;

	@Autowired
	public ConfigurationService(ConfigDao configDao) {
		this.configDao = configDao;
	}

	public Properties getMailConnectionProperties() {
		List<MailConfig> mailConfigs = configDao.getMailConfigs();
		Properties props = new Properties();

		for (MailConfig mailConfig : mailConfigs) {
			if (HOST.configName.equalsIgnoreCase(mailConfig.name) ||
				INBOX_FOLDER.configName.equalsIgnoreCase(mailConfig.name) ||
				USERNAME.configName.equalsIgnoreCase(mailConfig.name) ||
				PASSWORD.configName.equalsIgnoreCase(mailConfig.name) ||
				PROTOCOL.configName.equalsIgnoreCase(mailConfig.name) ||
				SSL_ENABLE.configName.equalsIgnoreCase(mailConfig.name) ||
				SOCKET_FACTORY_CLASS.configName.equalsIgnoreCase(mailConfig.name) ||
				SOCKET_FACTORY_FALLBACK.configName.equalsIgnoreCase(mailConfig.name) ||
				SOCKET_FACTORY_PORT.configName.equalsIgnoreCase(mailConfig.name)) {
				
				props.setProperty(mailConfig.name, mailConfig.value);
			}
		}
		return props;
	}

	public MailFilterConfigs getFilterConfigs() {
		List<MailConfig> configs = configDao.getMailConfigs();

		String sender = null;
		String subject = null;
		String daysAgo = null;
		String unread = null;

		for (MailConfig config : configs) {
			if (FILTER_SENDER.configName.equalsIgnoreCase(config.name)) {
				sender = config.value;

			} else if (FILTER_SUBJECT.configName.equalsIgnoreCase(config.name)) {
				subject = config.value;

			} else if (FILTER_DAYS_AGO.configName.equalsIgnoreCase(config.name)) {
				daysAgo = config.value;

			} else if (FILTER_UNREAD.configName.equalsIgnoreCase(config.name)) {
				unread = config.value;
			}
		}

		return new MailFilterConfigs(subject, sender, unread, daysAgo);
	}

	public List<ParserFieldConfig> getParserConfigs() {
		return configDao.getParserConfigs();
	}
	
	public List<ExportConfig> getExportConfigs() {
		return configDao.getExportConfigs();
	}

}