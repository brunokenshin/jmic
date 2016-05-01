package br.com.ursos.mail;

import static br.com.ursos.config.MailConfigurationEnum.HOST;
import static br.com.ursos.config.MailConfigurationEnum.INBOX_FOLDER;
import static br.com.ursos.config.MailConfigurationEnum.PASSWORD;
import static br.com.ursos.config.MailConfigurationEnum.PROTOCOL;
import static br.com.ursos.config.MailConfigurationEnum.USERNAME;
import static br.com.ursos.config.MailConfigurationEnum.getConfigValue;
import static javax.mail.Folder.READ_ONLY;

import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ursos.service.ConfigurationService;

@Component
public class MailReader {

	private final ConfigurationService configService;

	@Autowired
	public MailReader(ConfigurationService configService) {
		this.configService = configService;
	}

	public Message[] getEmails() throws MessagingException, SQLException {
		Properties props = configService.getMailConnectionProperties();

		final String host = getConfigValue(props, HOST);
		final String username = getConfigValue(props, USERNAME);
		final String password = getConfigValue(props, PASSWORD);

		Session session = Session.getInstance(props);

		Store store = session.getStore(getConfigValue(props, PROTOCOL));
		store.connect(host, username, password);

		Folder inbox = store.getFolder(getConfigValue(props, INBOX_FOLDER));
		inbox.open(READ_ONLY);

		SearchFilterFactory filters = new SearchFilterFactory(configService.getFilterConfigs());

		return inbox.search(filters.getMessageFilters());
	}

}
