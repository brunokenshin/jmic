package br.com.ursos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.mail.Message;

import org.junit.Test;

import br.com.ursos.config.MailFilterConfigs;
import br.com.ursos.mail.SearchFilterFactory;
import br.com.ursos.mock.MessageMock;

public class SearchFilterFactoryTest {

	private SearchFilterFactory filters;
	private MailFilterConfigs mailFilterConfigs;

	@Test
	public void test_filters() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("belt", "bruce.wayne@waynecorp.com", "false", "10");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		assertTrue(filters.getMessageFilters().match(message));
	}

	@Test
	public void test_sender_filter() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("belt", "riddler@wtf.com", "false", "10");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		assertFalse(filters.getMessageFilters().match(message));
	}

	@Test
	public void test_empty_sender_filter_must_match_emails_from_anyone() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("belt", "", "false", "10");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		filters.getMessageFilters().match(message);
	}

	@Test
	public void test_subject_filter() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("subject not recognized", "bruce.wayne@waynecorp.com", "false", "10");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		assertFalse(filters.getMessageFilters().match(message));
	}

	@Test
	public void test_empty_subject_filter_must_match_always() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("", "bruce.wayne@waynecorp.com", "false", "10");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		assertTrue(filters.getMessageFilters().match(message));
	}

	@Test
	public void test_days_ago_filter() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("belt", "bruce.wayne@waynecorp.com", "false", "3");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		assertFalse(filters.getMessageFilters().match(message));
	}

	@Test
	public void test_empty_days_ago_filter_must_match_alltime() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("belt", "bruce.wayne@waynecorp.com", "false", "");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		assertTrue(filters.getMessageFilters().match(message));
	}

	@Test
	public void test_unread_filter() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("belt", "bruce.wayne@waynecorp.com", "true", "10");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		assertFalse(filters.getMessageFilters().match(message));
	}

	@Test
	public void test_empty_unread_filter_must_match_both() throws Exception {
		mailFilterConfigs = new MailFilterConfigs("belt", "bruce.wayne@waynecorp.com", "", "10");
		filters = new SearchFilterFactory(mailFilterConfigs);
		Message message = new MessageMock();
		assertTrue(filters.getMessageFilters().match(message));
	}

}