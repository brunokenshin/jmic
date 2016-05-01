package br.com.ursos.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.ursos.config.ParserFieldConfig;
import br.com.ursos.export.Field;
import br.com.ursos.export.FieldList;
import br.com.ursos.mail.MailParser;
import br.com.ursos.mail.MailReader;
import br.com.ursos.mock.MessageMock;

public class MailServiceTest {

	private MailService service;
	private ConfigurationService configService;
	private PersistService persistService;
	private MailReader mailReader;
	private MailParser mailParser;

	private Message[] msgs;
	private List<ParserFieldConfig> fieldsConfigs;
	private FieldList fieldList1;
	private FieldList fieldList2;

	@Before
	public void setup() throws Exception {
		configService = mock(ConfigurationService.class);
		persistService = mock(PersistService.class);
		mailReader = mock(MailReader.class);
		mailParser = mock(MailParser.class);
		service = new MailService(configService, persistService, mailReader, mailParser);

		msgs = new Message[] { new MessageMock(), new MessageMock() };

		fieldsConfigs = new ArrayList<>();
		fieldsConfigs.add(new ParserFieldConfig("NAME", "Name: ", "\r"));
		fieldsConfigs.add(new ParserFieldConfig("EMAIL", "Email: ", "\r"));

		fieldList1 = new FieldList();
		fieldList1.add(new Field("NAME", "Bruce Wayne"));
		fieldList1.add(new Field("EMAIL", "bruce.wayne@waynecorp.com"));

		fieldList2 = new FieldList();
		fieldList1.add(new Field("NAME", "Alfred Pennyworth"));
		fieldList1.add(new Field("EMAIL", "alfred.pennyworth@waynecorp.com"));

		when(mailReader.getEmails()).thenReturn(msgs);
		when(configService.getParserConfigs()).thenReturn(fieldsConfigs);
		when(mailParser.getMessageFields(msgs[0], fieldsConfigs)).thenReturn(fieldList1);
		when(mailParser.getMessageFields(msgs[1], fieldsConfigs)).thenReturn(fieldList2);
	}

	@Test
	public void testReportFields() throws Exception {
		fieldList1.addAll(fieldList2);
		assertTrue(EqualsBuilder.reflectionEquals(fieldList1, service.reportFields()));
	}

	@Test
	public void testIgnoreFieldsOnErrorWhileReporting() throws Exception {
		when(mailParser.getMessageFields(msgs[0], fieldsConfigs)).thenThrow(new RuntimeException());
		assertTrue(EqualsBuilder.reflectionEquals(fieldList2, service.reportFields()));

	}

	@Test
	public void testPersistFields() throws Exception {
		service.persistFields();
		verify(persistService).persistFields(fieldList1);
		verify(persistService).persistFields(fieldList2);
	}

	@Test
	@Ignore // TODO: Search how to test this case
	public void testIgnoreMessageFieldsOnErrorWhilePersisting() throws Exception {
		when(mailParser.getMessageFields(msgs[0], fieldsConfigs)).thenThrow(new RuntimeException());

		service.persistFields();
		verify(persistService).persistFields(fieldList1); //This method call must be done but ignored because of the exception
		verify(persistService).persistFields(fieldList2);
	}

}
