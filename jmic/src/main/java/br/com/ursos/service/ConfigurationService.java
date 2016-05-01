package br.com.ursos.service;

import static br.com.ursos.config.ConfigurationEnum.FILTER_DAYS_AGO;
import static br.com.ursos.config.ConfigurationEnum.FILTER_SENDER;
import static br.com.ursos.config.ConfigurationEnum.FILTER_SUBJECT;
import static br.com.ursos.config.ConfigurationEnum.FILTER_UNREAD;

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
			props.setProperty(mailConfig.name, mailConfig.value);
		}

		return props;
	}

	public List<ParserFieldConfig> getParserConfigs() {
		return configDao.getParserConfigs();
	}

	public MailFilterConfigs getFilterConfigs() {
		List<MailConfig> configs = configDao.getMailConfigs();

		String sender = null;
		String subject = null;
		String daysAgo = null;
		String unread = null;

		for (MailConfig config : configs) {
			if (FILTER_SENDER.configName.equals(config.name)) {
				sender = config.value;

			} else if (FILTER_SUBJECT.configName.equals(config.name)) {
				subject = config.value;

			} else if (FILTER_DAYS_AGO.configName.equals(config.name)) {
				daysAgo = config.value;

			} else if (FILTER_UNREAD.configName.equals(config.name)) {
				unread = config.value;
			}
		}

		return new MailFilterConfigs(subject, sender, unread, daysAgo);
	}

	public List<ExportConfig> getExportConfigs() {
		return configDao.getExportConfigs();
	}

}