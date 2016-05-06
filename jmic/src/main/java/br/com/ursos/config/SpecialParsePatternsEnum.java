package br.com.ursos.config;

public enum SpecialParsePatternsEnum {

	LINEBREAK(System.lineSeparator());

	public final String pattern;

	private SpecialParsePatternsEnum(String pattern) {
		this.pattern = pattern;
	}

}
