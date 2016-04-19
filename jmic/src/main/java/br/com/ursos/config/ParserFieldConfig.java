package br.com.ursos.config;

public class ParserFieldConfig {

	public final String fieldName;
	public final String fieldStartPattern;
	public final String fieldEndPattern;

	public ParserFieldConfig(String fieldName, String fieldStartPattern, String fieldEndPattern) {
		this.fieldName = fieldName;
		this.fieldStartPattern = fieldStartPattern;
		this.fieldEndPattern = fieldEndPattern;
	}

	@Override
	public String toString() {
		return String.format("[fieldName=%s, startPattern=%s, endPattern=%s]", fieldName, fieldStartPattern,
				fieldEndPattern);
	}

}
