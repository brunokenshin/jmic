package br.com.ursos.export;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FieldTest {

    @Test
    public void testField() {
        Field field = new Field("fieldName", "fieldValue");
        assertEquals("fieldName", field.name);
        assertEquals("fieldValue", field.value);
    }

}
