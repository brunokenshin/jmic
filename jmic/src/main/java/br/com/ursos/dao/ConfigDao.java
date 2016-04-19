package br.com.ursos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.ursos.config.MailConfig;
import br.com.ursos.config.MailConfigRowMapper;
import br.com.ursos.config.ParserFieldConfig;
import br.com.ursos.config.ParserFieldConfigRowMapper;

@Component
public class ConfigDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ConfigDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<MailConfig> getMailConfigs() {
		String sql = "SELECT * FROM MAIL_CONNECTION_CONFIG";
		return jdbcTemplate.query(sql, new MailConfigRowMapper());
	}

	public List<ParserFieldConfig> getParserConfigs() {
		String sql = "select * from PARSER_CONFIG";
		return jdbcTemplate.query(sql, new ParserFieldConfigRowMapper());
	}

}
