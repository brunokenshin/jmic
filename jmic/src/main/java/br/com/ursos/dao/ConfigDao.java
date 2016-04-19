package br.com.ursos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.ursos.config.Config;
import br.com.ursos.config.ConfigRowMapper;

@Component
public class ConfigDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ConfigDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Config> getMailConfigs() {
		String sql = "SELECT * FROM MAIL_CONNECTION_CONFIG";
		return jdbcTemplate.query(sql, new ConfigRowMapper());
	}

}
