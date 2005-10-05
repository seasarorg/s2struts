package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.validator.annotation.DateType;

public class DateTypeConfigRegisterImpl implements ConfigRegister {

    private String pattern;

    public void regist(Field field, Method method) {
        DateType annotation = method.getAnnotation(DateType.class);

        Var var = new Var();
        var.setName("datePattern");
        if (annotation == null) {
            // auto validation
            var.setValue(this.pattern);
        } else {
            String datePattern = annotation.pattern();
            if (StringUtil.isEmpty(datePattern)) {
                var.setValue(this.pattern);
            } else {
                var.setValue(datePattern);
            }
        }
        field.addVar(var);
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
