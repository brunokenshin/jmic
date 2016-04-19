package br.com.ursos.mail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.Flags;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import br.com.ursos.config.MailFilterConfigs;

public class SearchFilterFactory {

	private final MailFilterConfigs filterConfigs;

	public SearchFilterFactory(MailFilterConfigs filterConfigs) {
		this.filterConfigs = filterConfigs;
	}

	public SearchTerm getMessageFilters() throws AddressException {
		List<SearchTerm> list = new ArrayList<SearchTerm>();

		if (!filterConfigs.subject.isEmpty()) {
			list.add(new SubjectTerm(filterConfigs.subject));
		}

		if (!filterConfigs.sender.isEmpty()) {
			list.add(new FromTerm(new InternetAddress(filterConfigs.sender)));
		}

		if (!filterConfigs.unread.isEmpty()) {
			Boolean unread = new Boolean(filterConfigs.unread);
			list.add(new FlagTerm(new Flags(Flags.Flag.SEEN), !unread));
		}

		if (!filterConfigs.daysAgo.isEmpty()) {
			list.add(getDateFilter(Integer.valueOf(filterConfigs.daysAgo)));
		}

		return new AndTerm(list.toArray(new SearchTerm[list.size()]));
	}

	private AndTerm getDateFilter(Integer daysAgo) {
		final Calendar calendar = Calendar.getInstance();

		SearchTerm olderThanToday = new ReceivedDateTerm(ComparisonTerm.LE, calendar.getTime());
		calendar.add(Calendar.DATE, -daysAgo);
		SearchTerm newerThanToday = new ReceivedDateTerm(ComparisonTerm.GT, calendar.getTime());

		return new AndTerm(olderThanToday, newerThanToday);
	}

}
