package br.com.ursos.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParserFieldConfigTest {

    @Test
    public void testParserFieldConfig() {
        ParserFieldConfig fieldConfig = new ParserFieldConfig("TEST_FIELD", "startPattern", "endPattern");
        assertEquals("startPattern", fieldConfig.fieldStartPattern);
        assertEquals("endPattern", fieldConfig.fieldEndPattern);
    }

    @Test
    public void testParserFieldConfigWithSimpleSpecialPattern() throws Exception {
        ParserFieldConfig fieldConfig = new ParserFieldConfig("TEST_FIELD", "startPattern", "LINEBREAK");
        assertEquals("startPattern", fieldConfig.fieldStartPattern);
        assertEquals(System.lineSeparator(), fieldConfig.fieldEndPattern);
    }

    @Test
    public void testParserFieldConfigWithComplexSpecialPattern() throws Exception {
        ParserFieldConfig fieldConfig = new ParserFieldConfig("TEST_FIELD", "startPattern", "complex end Pattern With LINEBREAK");
        assertEquals("startPattern", fieldConfig.fieldStartPattern);
        assertEquals("complex end Pattern With " + System.lineSeparator(), fieldConfig.fieldEndPattern);
    }

}