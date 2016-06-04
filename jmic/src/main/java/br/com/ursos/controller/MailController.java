package br.com.ursos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

	private final String contentType = "application/json;charset=UTF-8";

	@Autowired
	public MailController(MailService mailService) {
		this.mailService = mailService;
	}

	@ResponseBody
	@RequestMapping(value = "/report", method = GET, produces = contentType)
	public FieldList reportFields() throws MessagingException, IOException, SQLException {
		return mailService.reportFields();
	}

	@ResponseBody
	@RequestMapping(value = "/import", method = GET, produces = contentType)
	public FieldList importFields() throws MessagingException, IOException, SQLException {
		return mailService.persistFields();
	}

}
