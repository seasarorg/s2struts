package org.seasar.struts.examples.indexed;

import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class ChildDto {
    
    private String value;

    public String getValue() {
        return value;
    }

    @Required
    @Args(keys = "child", resource = false)
    public void setValue(String value) {
        this.value = value;
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(value).append("]");
        return buf.toString();
    }

}
