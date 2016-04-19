package br.com.ursos.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ParserFieldConfigRowMapper implements RowMapper<ParserFieldConfig> {

	@Override
	public ParserFieldConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ParserFieldConfig(rs.getString("FIELD_NAME"), 
								   rs.getString("FIELD_START_PATTERN"), 
								   rs.getString("FIELD_END_PATTERN"));
	}

}
