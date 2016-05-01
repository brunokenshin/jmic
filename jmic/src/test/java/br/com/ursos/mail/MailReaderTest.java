package br.com.ursos.mail;

import static br.com.ursos.utils.TestObjects.createPropertiesForHost;
import static br.com.ursos.utils.TestObjects.createPropertiesForProtocol;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import org.junit.Before;
import org.junit.Test;

import br.com.ursos.service.ConfigurationService;

public class MailReaderTest {

	private MailReader mailReader;
	private ConfigurationService configService;

	@Before
	public void setup() {
		configService = mock(ConfigurationService.class);
	}

	@Test(expected = NoSuchProviderException.class)
	public void test_invalid_protocol() throws MessagingException, SQLException {
		Properties props = createPropertiesForProtocol("invalid+protocol");

		when(configService.getMailConnectionProperties()).thenReturn(props);

		mailReader = new MailReader(configService);
		mailReader.getEmails();

		verify(configService);
	}

	@Test(expected = MessagingException.class)
	public void test_invalid_host() throws Exception {
		Properties props = createPropertiesForHost("invalid_host");

		when(configService.getMailConnectionProperties()).thenReturn(props);

		mailReader = new MailReader(configService);
		mailReader.getEmails();

		verify(configService);
	}

}
