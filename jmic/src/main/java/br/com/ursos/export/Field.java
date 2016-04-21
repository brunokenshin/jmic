package br.com.ursos.export;

public class Field {
	private final String name;
	private final String value;

	public Field(String fieldName, String fieldValue) {
		this.name = fieldName;
		this.value = fieldValue;
	}

	@Override
	public String toString() {
		return String.format("[fieldName=%s, fieldValue=%s]", name, value);
	}

}
