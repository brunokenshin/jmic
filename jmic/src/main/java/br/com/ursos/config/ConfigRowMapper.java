package br.com.ursos.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ConfigRowMapper implements RowMapper<Config> {

	@Override
	public Config mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Config(rs.getString("CONFIG_NAME"), rs.getString("CONFIG_VALUE"));
	}

}
