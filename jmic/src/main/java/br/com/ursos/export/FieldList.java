package br.com.ursos.export;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat
@SuppressWarnings("serial")
public class FieldList extends ArrayList<Field> {

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Field field : this) {
			sb.append(field.toString());
		}
		return sb.toString();
	}
}
