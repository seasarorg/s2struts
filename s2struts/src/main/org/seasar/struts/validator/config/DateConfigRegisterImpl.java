package org.seasar.struts.validator.config;

import java.util.Map;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;

/**
 * @author Satoshi Kimura
 */
public class DateConfigRegisterImpl implements ConfigRegister {

    private String pattern;

    public void regist(Field field, Map parameter) {
        String datePattern = (String) parameter.get("pattern");

        Var var = new Var();
        var.setName("datePattern");
        if (datePattern != null) {
            var.setValue(datePattern);
        } else {
            var.setValue(this.pattern);
        }
        field.addVar(var);
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
