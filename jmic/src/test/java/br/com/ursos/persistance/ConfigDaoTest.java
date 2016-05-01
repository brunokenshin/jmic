package br.com.ursos.persistance;

import static br.com.ursos.config.MailConfigurationEnum.FILTER_DAYS_AGO;
import static br.com.ursos.config.MailConfigurationEnum.FILTER_SENDER;
import static br.com.ursos.config.MailConfigurationEnum.FILTER_SUBJECT;
import static br.com.ursos.config.MailConfigurationEnum.FILTER_UNREAD;
import static br.com.ursos.config.MailConfigurationEnum.HOST;
import static br.com.ursos.config.MailConfigurationEnum.INBOX_FOLDER;
import static br.com.ursos.config.MailConfigurationEnum.PASSWORD;
import static br.com.ursos.config.MailConfigurationEnum.PROTOCOL;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_CLASS;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_FALLBACK;
import static br.com.ursos.config.MailConfigurationEnum.SOCKET_FACTORY_PORT;
import static br.com.ursos.config.MailConfigurationEnum.SSL_ENABLE;
import static br.com.ursos.config.MailConfigurationEnum.USERNAME;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ursos.JmicApplication;
import br.com.ursos.config.ExportConfig;
import br.com.ursos.config.MailConfig;
import br.com.ursos.config.ParserFieldConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JmicApplication.class)
public class ConfigDaoTest {

	@Autowired
	private ConfigDao dao;

	@Test
	public void testGetMailConfigs() {
		List<MailConfig> configs = dao.getMailConfigs();
		ArrayList<MailConfig> expectedConfigs = new ArrayList<MailConfig>();
		expectedConfigs.add(new MailConfig(HOST.configName, "imap.gmail.com"));
		expectedConfigs.add(new MailConfig(INBOX_FOLDER.configName, "INBOX"));
		expectedConfigs.add(new MailConfig(USERNAME.configName, "bruce.wayne@waynecorp.com"));
		expectedConfigs.add(new MailConfig(PASSWORD.configName, "alfred"));
		expectedConfigs.add(new MailConfig(PROTOCOL.configName, "imaps"));
		expectedConfigs.add(new MailConfig(SSL_ENABLE.configName, "true"));
		expectedConfigs.add(new MailConfig(SOCKET_FACTORY_CLASS.configName, "javax.net.ssl.SSLSocketFactory"));
		expectedConfigs.add(new MailConfig(SOCKET_FACTORY_FALLBACK.configName, "false"));
		expectedConfigs.add(new MailConfig(SOCKET_FACTORY_PORT.configName, "993"));
		expectedConfigs.add(new MailConfig(FILTER_DAYS_AGO.configName, "20"));
		expectedConfigs.add(new MailConfig(FILTER_SENDER.configName, "alfred@waynecorp.com"));
		expectedConfigs.add(new MailConfig(FILTER_SUBJECT.configName, "Joker"));
		expectedConfigs.add(new MailConfig(FILTER_UNREAD.configName, "false"));

		assertTrue(EqualsBuilder.reflectionEquals(expectedConfigs, configs));
	}

	@Test
	public void testGetParserConfigs() {
		List<ParserFieldConfig> configs = dao.getParserConfigs();

		List<ParserFieldConfig> expectedConfigs = new ArrayList<ParserFieldConfig>();
		expectedConfigs.add(new ParserFieldConfig("NAME", "Nome: ", "\r"));
		expectedConfigs.add(new ParserFieldConfig("PHONE", "Telefone: ", "\r"));
		expectedConfigs.add(new ParserFieldConfig("EMAIL", "Email: ", "\r"));
		expectedConfigs.add(new ParserFieldConfig("LOCATION", "Ebdereço: ", "\r"));

		assertTrue(EqualsBuilder.reflectionEquals(expectedConfigs, configs));
	}

	@Test
	public void testGetExportConfigs() {
		List<ExportConfig> configs = dao.getExportConfigs();
		ArrayList<ExportConfig> expectedConfigs = new ArrayList<ExportConfig>();
		expectedConfigs.add(new ExportConfig("NAME", "CLIENTE", "NOME"));
		expectedConfigs.add(new ExportConfig("PHONE", "CLIENTE", "TELEFONE"));
		expectedConfigs.add(new ExportConfig("EMAIL", "CLIENTE", "EMAIL"));
		expectedConfigs.add(new ExportConfig("LOCATION", "CLIENTE", "ENDERECO"));
		
		assertTrue(EqualsBuilder.reflectionEquals(expectedConfigs, configs));
	}

}
