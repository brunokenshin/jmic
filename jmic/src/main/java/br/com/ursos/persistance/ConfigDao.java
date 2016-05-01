package br.com.ursos.persistance;

import static br.com.ursos.persistance.ConfigTableNamesEnum.EXPORT_CONFIG;
import static br.com.ursos.persistance.ConfigTableNamesEnum.MAIL_CONNECTION_CONFIG;
import static br.com.ursos.persistance.ConfigTableNamesEnum.PARSER_CONFIG;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.ursos.config.ExportConfig;
import br.com.ursos.config.MailConfig;
import br.com.ursos.config.ParserFieldConfig;

@Component
public class ConfigDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ConfigDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<MailConfig> getMailConfigs() throws SQLException {
		String sql = "SELECT * FROM " + MAIL_CONNECTION_CONFIG;
		try {
			return jdbcTemplate.query(sql, new MailConfigRowMapper());
		} catch (Exception e) {
			throw new SQLException("Error while getting mail configs from database", e);
		}
	}

	public List<ParserFieldConfig> getParserConfigs() throws SQLException {
		String sql = "select * from " + PARSER_CONFIG;
		try {
			return jdbcTemplate.query(sql, new ParserFieldConfigRowMapper());
		} catch (Exception e) {
			throw new SQLException("Error while getting parser configs from database", e);
		}
	}

	public List<ExportConfig> getExportConfigs() throws SQLException {
		String sql = "select * from " + EXPORT_CONFIG;
		try {
			return jdbcTemplate.query(sql, new ExportConfigRowMapper());
		} catch (Exception e) {
			throw new SQLException("Error while getting export configs from database", e);
		}
	}
}
