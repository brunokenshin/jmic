package br.com.ursos.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ursos.export.FieldList;
import br.com.ursos.service.MailService;

@RestController
public class MailController {

	private final MailService mailService;

	@Autowired
	public MailController(MailService mailService) {
		this.mailService = mailService;
	}

	@ResponseBody
	@RequestMapping("/report")
	public FieldList reportFields() throws MessagingException, IOException, SQLException {
		return mailService.reportFields();
	}

	@ResponseBody
	@RequestMapping("/import")
	public String importFields() throws MessagingException, IOException, SQLException {
		mailService.importFields();
		return "Done!";
	}

}
