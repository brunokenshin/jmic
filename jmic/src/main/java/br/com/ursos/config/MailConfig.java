package br.com.ursos.config;

public class MailConfig {

	public final String name;
	public final String value;

	public MailConfig(String configName, String configValue) {
		this.name = configName;
		this.value = configValue;
	}

	@Override
	public String toString() {
		return String.format("[configName=%s, configValue=%s]", name, value);
	}
}
