package br.com.ursos.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MailConfigTest {

    @Test
    public void testMailConfig() {
        MailConfig config = new MailConfig("test.config.name", "test.config.value");
        assertEquals("test.config.name", config.name);
        assertEquals("test.config.value", config.value);
    }

}
