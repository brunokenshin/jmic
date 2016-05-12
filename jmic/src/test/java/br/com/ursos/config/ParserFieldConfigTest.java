package br.com.ursos.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParserFieldConfigTest {

    @Test
    public void testParserFieldConfig() {
        ParserFieldConfig fieldConfig = new ParserFieldConfig("TEST_FIELD", "startPattern", "endPattern");
        assertEquals("startPattern", fieldConfig.startPattern);
        assertEquals("endPattern", fieldConfig.endPattern);
    }

    @Test
    public void testParserFieldConfigWithSimpleSpecialPattern() throws Exception {
        ParserFieldConfig fieldConfig = new ParserFieldConfig("TEST_FIELD", "startPattern", "LINEBREAK");
        assertEquals("startPattern", fieldConfig.startPattern);
        assertEquals(System.lineSeparator(), fieldConfig.endPattern);
    }

    @Test
    public void testParserFieldConfigWithComplexSpecialPattern() throws Exception {
        ParserFieldConfig fieldConfig = new ParserFieldConfig("TEST_FIELD", "startPattern", "complex end Pattern With LINEBREAK");
        assertEquals("startPattern", fieldConfig.startPattern);
        assertEquals("complex end Pattern With " + System.lineSeparator(), fieldConfig.endPattern);
    }

}