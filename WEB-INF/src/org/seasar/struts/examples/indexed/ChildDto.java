package org.seasar.struts.examples.indexed;

public class ChildDto {
    
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(value).append("]");
        return buf.toString();
    }

}
