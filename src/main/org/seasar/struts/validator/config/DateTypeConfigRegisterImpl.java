package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.struts.validator.annotation.DateType;

/**
 * @author Satoshi Kimura
 */
public class DateTypeConfigRegisterImpl implements ConfigRegister {

    private String pattern;

    public void regist(Field field, Method method) {
        Annotation annotation = Annotations.getAnnotation(DateType.class, method);

        Var var = new Var();
        var.setName("datePattern");
        if (annotation == null) {
            // auto validation
            var.setValue(this.pattern);
        } else {
            String datePattern = ((DateType) annotation).pattern();
            if (datePattern != null) {
                var.setValue(datePattern);
            } else {
                var.setValue(this.pattern);
            }
        }
        field.addVar(var);
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
