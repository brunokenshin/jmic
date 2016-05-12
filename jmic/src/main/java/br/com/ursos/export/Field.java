package br.com.ursos.export;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Field {

	@JsonProperty("fieldName")
	public final String name;

	@JsonProperty("fieldValue")
	public final String value;

	public Field(String fieldName, String fieldValue) {
		this.name = fieldName;
		this.value = fieldValue;
	}

	@Override
	public String toString() {
		return String.format("[fieldName=%s, fieldValue=%s]", name, value);
	}

	@JsonIgnore
	public boolean isValid() {
		if (StringUtils.isBlank(name) || value.equals(null)) {
			return false;
		}
		return true;
	}

}
