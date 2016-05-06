package br.com.ursos.config;

import static br.com.ursos.config.SpecialParsePatternsEnum.LINEBREAK;

public class ParserFieldConfig {

	public final String fieldName;
	public final String fieldStartPattern;
	public final String fieldEndPattern;

	public ParserFieldConfig(String fieldName, String fieldStartPattern, String fieldEndPattern) {
		this.fieldName = fieldName;
		this.fieldStartPattern = verifyPatterns(fieldStartPattern);
		this.fieldEndPattern = verifyPatterns(fieldEndPattern);
	}

	private String verifyPatterns(String pattern) {
		if (LINEBREAK.name().equalsIgnoreCase(pattern)) {
			return LINEBREAK.pattern;
		}
		return pattern;
	}

	@Override
	public String toString() {
		return String.format("[fieldName=%s, startPattern=%s, endPattern=%s]", fieldName, fieldStartPattern,
				fieldEndPattern);
	}

}
