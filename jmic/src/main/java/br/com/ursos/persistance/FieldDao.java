package br.com.ursos.persistance;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import br.com.ursos.config.ExportConfig;
import br.com.ursos.export.Field;

@Component
public class FieldDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public FieldDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long createRow(String tableName) throws SQLException {
		String sql = "insert into " + tableName + "(ID) values (null)";
		final PreparedStatementCreator statement = new PreparedStatementCreator(sql);
		final KeyHolder holder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(statement, holder);
		} catch (Exception e) {
			throw new SQLException(e);
		}

		return holder.getKey().longValue();
	}

	public void updateRow(long rowId, Field field, ExportConfig exportConfig) throws SQLException {
		final String sql = "update " + exportConfig.tableName + " set " + exportConfig.columnName + " = ? where id = " + rowId;

		try {
			jdbcTemplate.update(sql, field.value);
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

}