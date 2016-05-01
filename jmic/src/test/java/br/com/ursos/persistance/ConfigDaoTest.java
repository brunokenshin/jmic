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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.ursos.config.ExportConfig;
import br.com.ursos.config.ExportConfigRowMapper;
import br.com.ursos.config.MailConfig;
import br.com.ursos.config.MailConfigRowMapper;
import br.com.ursos.config.ParserFieldConfig;
import br.com.ursos.config.ParserFieldConfigRowMapper;

public class ConfigDaoTest {

	private ConfigDao dao;
	private JdbcTemplate jdbc;

	@Before
	public void setup() {
		jdbc = mock(JdbcTemplate.class);
		dao = new ConfigDao(jdbc);
	}

	@Test
	public void testGetMailConfigs() throws SQLException {
		ArrayList<MailConfig> expectedConfigs = getExpectedMailConfigs();
		when(jdbc.query(anyString(), any(MailConfigRowMapper.class))).thenReturn(expectedConfigs);

		List<MailConfig> configs = dao.getMailConfigs();
		assertTrue(EqualsBuilder.reflectionEquals(expectedConfigs, configs));
	}

	@Test(expected = SQLException.class)
	public void testErrorWhileGettingMailConfigs() throws SQLException {
		when(jdbc.query(anyString(), any(MailConfigRowMapper.class))).thenThrow(new RuntimeException());
		dao.getMailConfigs();
	}

	@Test
	public void testGetParserConfigs() throws SQLException {
		List<ParserFieldConfig> expectedConfigs = getExpectedParserConfigs();
		when(jdbc.query(anyString(), any(ParserFieldConfigRowMapper.class))).thenReturn(expectedConfigs);

		List<ParserFieldConfig> configs = dao.getParserConfigs();
		assertTrue(EqualsBuilder.reflectionEquals(expectedConfigs, configs));
	}

	@Test(expected = SQLException.class)
	public void testErrorWhileGettingParserConfigs() throws SQLException {
		when(jdbc.query(anyString(), any(MailConfigRowMapper.class))).thenThrow(new RuntimeException());
		dao.getParserConfigs();
	}

	@Test
	public void testGetExportConfigs() throws SQLException {
		ArrayList<ExportConfig> expectedConfigs = getExpectedExportConfigs();
		when(jdbc.query(anyString(), any(ExportConfigRowMapper.class))).thenReturn(expectedConfigs);

		List<ExportConfig> configs = dao.getExportConfigs();
		assertTrue(EqualsBuilder.reflectionEquals(expectedConfigs, configs));
	}

	@Test(expected = SQLException.class)
	public void testErrorWhileGettingExportConfigs() throws SQLException {
		when(jdbc.query(anyString(), any(MailConfigRowMapper.class))).thenThrow(new RuntimeException());
		dao.getExportConfigs();
	}

	private ArrayList<MailConfig> getExpectedMailConfigs() {
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
		return expectedConfigs;
	}

	private List<ParserFieldConfig> getExpectedParserConfigs() {
		List<ParserFieldConfig> expectedConfigs = new ArrayList<ParserFieldConfig>();
		expectedConfigs.add(new ParserFieldConfig("NAME", "Nome: ", "\r"));
		expectedConfigs.add(new ParserFieldConfig("PHONE", "Telefone: ", "\r"));
		expectedConfigs.add(new ParserFieldConfig("EMAIL", "Email: ", "\r"));
		expectedConfigs.add(new ParserFieldConfig("LOCATION", "Ebdereço: ", "\r"));
		return expectedConfigs;
	}

	private ArrayList<ExportConfig> getExpectedExportConfigs() {
		ArrayList<ExportConfig> expectedConfigs = new ArrayList<ExportConfig>();
		expectedConfigs.add(new ExportConfig("NAME", "CLIENTE", "NOME"));
		expectedConfigs.add(new ExportConfig("PHONE", "CLIENTE", "TELEFONE"));
		expectedConfigs.add(new ExportConfig("EMAIL", "CLIENTE", "EMAIL"));
		expectedConfigs.add(new ExportConfig("LOCATION", "CLIENTE", "ENDERECO"));
		return expectedConfigs;
	}

}
