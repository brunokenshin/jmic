package br.com.ursos.config;

import static br.com.ursos.config.ConfigurationEnum.FILTER_DAYS_AGO;
import static br.com.ursos.config.ConfigurationEnum.FILTER_SENDER;
import static br.com.ursos.config.ConfigurationEnum.FILTER_SUBJECT;
import static br.com.ursos.config.ConfigurationEnum.FILTER_UNREAD;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ursos.dao.ConfigDao;

@Component
public class ConfigurationService {

	private ConfigDao configDao;

	@Autowired
	public ConfigurationService(ConfigDao configDao) {
		this.configDao = configDao;
	}

	public Properties getMailConnectionConfigs() {
		List<MailConfig> mailConfigs = configDao.getMailConfigs();
		Properties props = new Properties();

		mailConfigs.stream().forEach(config -> props.setProperty(config.name, config.value));

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