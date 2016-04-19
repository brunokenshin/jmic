package br.com.ursos.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MailConfigRowMapper implements RowMapper<MailConfig> {

	@Override
	public MailConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new MailConfig(rs.getString("CONFIG_NAME"), rs.getString("CONFIG_VALUE"));
	}

}
