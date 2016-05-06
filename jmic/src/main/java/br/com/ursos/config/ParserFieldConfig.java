package br.com.ursos.config;

import static br.com.ursos.config.SpecialParsePatternsEnum.LINEBREAK;

import org.apache.commons.lang3.StringUtils;


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
        if (StringUtils.containsIgnoreCase(pattern, LINEBREAK.name())) {
            return pattern.replace(LINEBREAK.name(), System.lineSeparator());
        }
        return pattern;
	}

	@Override
	public String toString() {
		return String.format("[fieldName=%s, startPattern=%s, endPattern=%s]", fieldName, fieldStartPattern,
				fieldEndPattern);
	}

}
