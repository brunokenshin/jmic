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
	private Folder inbox;

	@Autowired
	public MailReader(ConfigurationService configService) {
		this.configService = configService;
	}

	public Message[] getEmails() throws MessagingException, SQLException {
		inbox = getInbox();
		SearchFilterFactory filters = new SearchFilterFactory(configService.getFilterConfigs());
		return inbox.search(filters.getMessageFilters());
	}

	private Folder getInbox() throws SQLException, MessagingException {
		closeConnection();

		Properties props = configService.getMailConnectionProperties();
		String host = getConfigValue(props, HOST);
		String username = getConfigValue(props, USERNAME);
		String password = getConfigValue(props, PASSWORD);

		Session session = Session.getInstance(props);
		Store store = session.getStore(getConfigValue(props, PROTOCOL));
		store.connect(host, username, password);

		Folder inbox = store.getFolder(getConfigValue(props, INBOX_FOLDER));
		inbox.open(READ_ONLY);
		return inbox;
	}

	public void closeConnection() throws MessagingException {
		if (inbox != null && inbox.isOpen()) {
			inbox.close(false);
		}
		if (inbox != null && inbox.getStore().isConnected()) {
			inbox.getStore().close();
		}
	}

}
