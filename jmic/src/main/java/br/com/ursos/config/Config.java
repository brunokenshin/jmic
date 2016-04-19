package br.com.ursos.config;

public class Config {

	public final String name;
	public final String value;

	public Config(String configName, String configValue) {
		this.name = configName;
		this.value = configValue;
	}

	@Override
	public String toString() {
		return String.format("[configName=%s, configValue=%s]", name, value);
	}
}
