package br.com.ursos.config;

import static br.com.ursos.config.SpecialParsePatternsEnum.LINEBREAK;

import org.apache.commons.lang3.StringUtils;


public class ParserFieldConfig {

	public final String name;
	public final String startPattern;
	public final String endPattern;

	public ParserFieldConfig(String fieldName, String fieldStartPattern, String fieldEndPattern) {
		this.name = fieldName;
		this.startPattern = verifyPatterns(fieldStartPattern);
		this.endPattern = verifyPatterns(fieldEndPattern);
	}

	private String verifyPatterns(String pattern) {
		if (StringUtils.containsIgnoreCase(pattern, LINEBREAK.name())) {
			pattern = pattern.replace(LINEBREAK.name(), LINEBREAK.pattern);
		}

        return pattern;
	}

	@Override
	public String toString() {
		return String.format("[fieldName=%s, startPattern=%s, endPattern=%s]", name, startPattern, endPattern);
	}

}
