package br.com.ursos.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ursos.config.ExportConfig;

public class ExportConfigRowMapper implements RowMapper<ExportConfig> {

	@Override
	public ExportConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ExportConfig(rs.getString("PARSER_NAME"), 
								rs.getString("TABLE_NAME"), 
								rs.getString("COLUMN_NAME"));
	}


}
