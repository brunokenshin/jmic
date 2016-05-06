package br.com.ursos.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExportConfigTest {

    @Test
    public void testExportConfig() {
        ExportConfig config = new ExportConfig("TEST_FIELD", "TEST_TABLE", "TEST_COLUMN");
        assertEquals("TEST_FIELD", config.parserName);
        assertEquals("TEST_TABLE", config.tableName);
        assertEquals("TEST_COLUMN", config.columnName);
    }

}
