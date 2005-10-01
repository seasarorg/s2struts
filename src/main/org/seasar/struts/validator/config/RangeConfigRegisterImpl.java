package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.struts.validator.annotation.Range;

/**
 * @author Satoshi Kimura
 */
public class RangeConfigRegisterImpl implements ConfigRegister {

    public void regist(Field field, Method method) {
        Annotation annotation = Annotations.getAnnotation(Range.class, method);
        if (annotation == null) {
            return;
        }
        Range range = (Range)annotation;

        Arg arg = new Arg();
        arg.setName(range.type());
        arg.setKey("${var:min}");
        arg.setResource(false);
        arg.setPosition(1);
        field.addArg(arg);
        arg = new Arg();
        arg.setName(range.type());
        arg.setKey("${var:max}");
        arg.setResource(false);
        arg.setPosition(2);
        field.addArg(arg);

        Var var = new Var();
        var.setName("min");
        var.setValue(String.valueOf(range.min()));
        field.addVar(var);
        var = new Var();
        var.setName("max");
        var.setValue(String.valueOf(range.max()));
        field.addVar(var);
    }

}
