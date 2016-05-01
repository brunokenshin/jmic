package br.com.ursos.persistance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ursos.JmicApplication;
import br.com.ursos.config.ExportConfig;
import br.com.ursos.export.Field;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JmicApplication.class)
public class FieldDaoTest {

	@Autowired private FieldDao dao;
	@Autowired private JdbcTemplate jdbc;
	private final String tableName = "CLIENTE";

	@Before
	public void setup() {
		jdbc.execute("DELETE FROM " + tableName);
	}
	
	@Test
	public void testCreateRow() {
		dao.createRow("CLIENTE");
		Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM " + tableName, Integer.class);

		assertEquals(new Integer(1), count);
	}

	@Test
	public void testUpdateRow() {
		String columnName = "NOME";
		jdbc.execute("INSERT INTO " + tableName + "(" + columnName + ") VALUES('ALFRED PENNYWORTH')");

		Field field = new Field("PARSER_NAME", "BRUCE WAYNE");
		ExportConfig exportConfig = new ExportConfig("PARSER_NAME", tableName, columnName);

		dao.updateRow(1, field, exportConfig);

		String sql = "SELECT " + columnName + " FROM " + tableName + " WHERE ID=1";
		assertEquals("BRUCE WAYNE", jdbc.queryForObject(sql, String.class));

	}

}
