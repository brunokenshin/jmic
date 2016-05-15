package br.com.ursos.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ursos.export.FieldList;
import br.com.ursos.mail.MailParser;
import br.com.ursos.mail.MailReader;

@Service
public class MailService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final ConfigurationService configService;
	private final PersistService persistService;
	private final MailReader mailReader;
	private final MailParser mailParser;

	@Autowired
	public MailService(ConfigurationService configService,
						PersistService persistService, 
						MailReader mailReader, 
						MailParser mailParser) {
		
		this.configService = configService;
		this.persistService = persistService;
		this.mailReader = mailReader;
		this.mailParser = mailParser;
	}

	public FieldList reportFields() throws MessagingException, IOException, SQLException {
		Message[] emails = mailReader.getEmails();
		FieldList fields = new FieldList();
		for (Message message : emails) {
			report(fields, message);
		}
		mailReader.closeConnection();
		return fields;
	}

	private void report(FieldList fields, Message message) throws IOException, MessagingException, SQLException {
		try {
			fields.addAll(getMessageFields(message));
		} catch (Exception e) {
			logger.error(String.format(
					"Error during fields reporting for message. "
					+ "Fields for this message will be skipped [msgSubject=%s, msgSender=%s, msgDate=%s]",
					message.getSubject(), message.getFrom(), message.getReceivedDate()), e);
		}
	}

	public void persistFields() throws MessagingException, IOException, SQLException {
		Message[] emails = mailReader.getEmails();
		for (Message message : emails) {
			persist(message);
		}
		mailReader.closeConnection();
	}

	private void persist(Message message) throws MessagingException {
		try {
			persistService.persistFields(getMessageFields(message));
		} catch (Exception e) {
			logger.error(String.format(
					"Error during fields persistance for message. "
					+ "Fields for this message will be skipped [msgSubject=%s, msgSender=%, msgDate=%s]",
					message.getSubject(), message.getFrom(), message.getReceivedDate()), e);
		}
	}

	private FieldList getMessageFields(Message msg) throws Exception {
		return mailParser.getMessageFields(msg, configService.getParserConfigs());
	}

}
