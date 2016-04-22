package br.com.ursos.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class PreparedStatementCreator implements org.springframework.jdbc.core.PreparedStatementCreator {

	private final String query;

	public PreparedStatementCreator(String sqlQuery) {
		this.query = sqlQuery;
	}

	@Override
	public PreparedStatement createPreparedStatement(final Connection conn) throws SQLException {
		return conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	}
	
}
