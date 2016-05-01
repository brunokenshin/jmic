package br.com.ursos.service;

import static br.com.ursos.config.ConfigurationEnum.FILTER_DAYS_AGO;
import static br.com.ursos.config.ConfigurationEnum.FILTER_SENDER;
import static br.com.ursos.config.ConfigurationEnum.FILTER_SUBJECT;
import static br.com.ursos.config.ConfigurationEnum.FILTER_UNREAD;
import static br.com.ursos.config.ConfigurationEnum.HOST;
import static br.com.ursos.config.ConfigurationEnum.INBOX_FOLDER;
import static br.com.ursos.config.ConfigurationEnum.PASSWORD;
import static br.com.ursos.config.ConfigurationEnum.PROTOCOL;
import static br.com.ursos.config.ConfigurationEnum.SOCKET_FACTORY_CLASS;
import static br.com.ursos.config.ConfigurationEnum.SOCKET_FACTORY_FALLBACK;
import static br.com.ursos.config.ConfigurationEnum.SOCKET_FACTORY_PORT;
import static br.com.ursos.config.ConfigurationEnum.SSL_ENABLE;
import static br.com.ursos.config.ConfigurationEnum.USERNAME;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import br.com.ursos.config.MailConfig;
import br.com.ursos.config.MailFilterConfigs;
import br.com.ursos.persistance.ConfigDao;

public class ConfigurationServiceTest {

	private ConfigurationService service;
	private ConfigDao dao;

	@Before
	public void setup() {
		dao = mock(ConfigDao.class);
		service = new ConfigurationService(dao);
	}

	@Test
	public void testGetMailConnectionConfigs() {
		when(dao.getMailConfigs()).thenReturn(populateMailConfigs());
		
		Properties configs = service.getMailConnectionProperties();
		assertEquals("alfred", configs.getProperty(PASSWORD.configName));
		assertEquals("imaps", configs.getProperty(PROTOCOL.configName));
		assertEquals("true", configs.getProperty(SSL_ENABLE.configName));
		assertEquals("javax.net.ssl.SSLSocketFactory", configs.getProperty(SOCKET_FACTORY_CLASS.configName));
		assertEquals("false", configs.getProperty(SOCKET_FACTORY_FALLBACK.configName));
		assertEquals("993", configs.getProperty(SOCKET_FACTORY_PORT.configName));
		assertEquals("20", configs.getProperty(FILTER_DAYS_AGO.configName));
		assertEquals("alfred@waynecorp.com", configs.getProperty(FILTER_SENDER.configName));
		assertEquals("Joker", configs.getProperty(FILTER_SUBJECT.configName));
		assertEquals("false", configs.getProperty(FILTER_UNREAD.configName));
	}

	@Test
	public void testGetFilterConfigs() {
		when(dao.getMailConfigs()).thenReturn(populateMailConfigs());
		
		MailFilterConfigs configs = service.getFilterConfigs();
		assertEquals("20", configs.daysAgo);
		assertEquals("alfred@waynecorp.com", configs.sender);
		assertEquals("Joker", configs.subject);
		assertEquals("false", configs.unread);
	}

	@Test
	public void testGetParserConfigs() {
		when(dao.getParserConfigs()).thenReturn(null);
		service.getParserConfigs();
		verify(dao, times(1)).getParserConfigs();
	}

	@Test
	public void testGetExportConfigs() {
		when(dao.getExportConfigs()).thenReturn(null);
		service.getExportConfigs();
		verify(dao, times(1)).getExportConfigs();
	}

	private List<MailConfig> populateMailConfigs() {
		List<MailConfig> mailConfigs = new ArrayList<>();
		mailConfigs.add(new MailConfig(HOST.configName, "imap.gmail.com"));
		mailConfigs.add(new MailConfig(INBOX_FOLDER.configName, "INBOX"));
		mailConfigs.add(new MailConfig(USERNAME.configName, "bruce.wayne@waynecorp.com"));
		mailConfigs.add(new MailConfig(PASSWORD.configName, "alfred"));
		mailConfigs.add(new MailConfig(PROTOCOL.configName, "imaps"));
		mailConfigs.add(new MailConfig(SSL_ENABLE.configName, "true"));
		mailConfigs.add(new MailConfig(SOCKET_FACTORY_CLASS.configName, "javax.net.ssl.SSLSocketFactory"));
		mailConfigs.add(new MailConfig(SOCKET_FACTORY_FALLBACK.configName, "false"));
		mailConfigs.add(new MailConfig(SOCKET_FACTORY_PORT.configName, "993"));
		mailConfigs.add(new MailConfig(FILTER_DAYS_AGO.configName, "20"));
		mailConfigs.add(new MailConfig(FILTER_SENDER.configName, "alfred@waynecorp.com"));
		mailConfigs.add(new MailConfig(FILTER_SUBJECT.configName, "Joker"));
		mailConfigs.add(new MailConfig(FILTER_UNREAD.configName, "false"));
		return mailConfigs;
	}

}
