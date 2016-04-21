package br.com.ursos.controller;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ursos.config.ConfigurationService;
import br.com.ursos.export.FieldList;
import br.com.ursos.mail.MailParser;
import br.com.ursos.mail.MailReader;

@RestController
public class MailController {

	private final ConfigurationService configService;
	private final MailReader mailReader;
	private final MailParser mailParser;

	@Autowired
	public MailController(ConfigurationService configService, MailReader mailReader, MailParser mailParser) {
		this.configService = configService;
		this.mailReader = mailReader;
		this.mailParser = mailParser;
	}

	@ResponseBody
	@RequestMapping("/import")
	public String importMails() throws MessagingException, IOException {
		Message[] emails = mailReader.getEmails();
		FieldList fields = new FieldList();

		for (Message mail : emails) {
			fields.addAll(mailParser.getMessageFields(mail, configService.getParserConfigs()));
		}
		
		return fields.toString();
	}
}
