package br.com.ursos.config;


import static br.com.ursos.config.MailConfigurationEnum.HOST;
import static br.com.ursos.config.MailConfigurationEnum.getConfigValue;
import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

public class MailConfigurationEnumTest {

    private Properties props;

    @Test
    public void testGetConfigValue() {
        props = new Properties();
        props.setProperty(HOST.configName, "waynecorp.com.br");
        assertEquals("waynecorp.com.br", getConfigValue(props, HOST));
    }

    @Test
    public void testReturnEmptyStringForEmptyProperty() {
        props = new Properties();
        assertEquals("", getConfigValue(props, HOST));
    }

}
