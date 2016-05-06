package br.com.ursos.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MailFilterConfigsTest {

    @Test
    public void testMailFilterConfigs() {
        MailFilterConfigs configs = new MailFilterConfigs("The Joker", "bruce.wayne@waynecorp.com", "true", "10");
        assertEquals("The Joker", configs.subject);
        assertEquals("bruce.wayne@waynecorp.com", configs.sender);
        assertEquals("true", configs.unread);
        assertEquals("10", configs.daysAgo);
    }

}
