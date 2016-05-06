package br.com.ursos.export;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FieldListTest {

    @Test
    public void testToString() {
        FieldList fieldList = new FieldList();
        Field field1 = new Field("field1", "value1");
        Field field2 = new Field("field2", "value2");
        
        fieldList.add(field1);
        fieldList.add(field2);
        
        assertEquals(field1.toString() + field2.toString(), fieldList.toString());
    }

}
