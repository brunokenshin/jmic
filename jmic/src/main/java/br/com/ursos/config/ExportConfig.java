package br.com.ursos.config;

public class ExportConfig {

	public final String parserName;
	public final String tableName;
	public final String columnName;

	public ExportConfig(String parserName, String tableName, String columnName) {
		this.parserName = parserName;
		this.tableName = tableName;
		this.columnName = columnName;
	}
}
