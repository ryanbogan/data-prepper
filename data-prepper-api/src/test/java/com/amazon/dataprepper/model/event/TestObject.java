package com.amazon.dataprepper.model.event;

public class TestObject {
    private String field1;

    public TestObject() {

    }

    public TestObject(final String field1){
        this.field1 = field1;
    }

    public String getField1() {
        return this.field1;
    }

    public void setField1(final String field1) {
        this.field1 = field1;
    }
}
