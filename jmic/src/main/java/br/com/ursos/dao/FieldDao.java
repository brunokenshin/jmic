package br.com.ursos.dao;

import java.util.List;

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

	public long createRow(List<ExportConfig> exportConfigs) {
		String sql = "insert into " + exportConfigs.get(0).tableName + "(ID) values (null)";
		final PreparedStatementCreator statement = new PreparedStatementCreator(sql);
		final KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(statement, holder);
		return holder.getKey().longValue();
	}

	public void updateRow(long rowId, Field field, ExportConfig exportConfig) {
		final String sql = "update " + exportConfig.tableName + " set " + exportConfig.columnName + " = ? where id = " + rowId;
		jdbcTemplate.update(sql, field.value);
	}

}