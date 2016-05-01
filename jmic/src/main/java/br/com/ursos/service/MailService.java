package br.com.ursos.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ursos.export.FieldList;
import br.com.ursos.mail.MailParser;
import br.com.ursos.mail.MailReader;

@Service
public class MailService {

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
			fields.addAll(getMessageFields(message));
		}

		return fields;
	}

	public void persistFields() throws MessagingException, IOException, SQLException {
		Message[] emails = mailReader.getEmails();
		for (Message message : emails) {
			persistService.persistFields(getMessageFields(message));
		}
	}

	private FieldList getMessageFields(Message msg) throws SQLException, IOException, MessagingException {
			return mailParser.getMessageFields(msg, configService.getParserConfigs());
	}

}
