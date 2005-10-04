package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.seasar.struts.validator.annotation.Range;

public class RangeConfigRegisterImpl implements ConfigRegister {

    public void regist(Field field, Method method) {
        Range annotation = method.getAnnotation(Range.class);
        if (annotation == null) {
            return;
        }

        Arg arg = new Arg();
        arg.setName(annotation.type());
        arg.setKey("${var:min}");
        arg.setResource(false);
        arg.setPosition(1);
        field.addArg(arg);
        arg = new Arg();
        arg.setName(annotation.type());
        arg.setKey("${var:max}");
        arg.setResource(false);
        arg.setPosition(2);
        field.addArg(arg);

        Var var = new Var();
        var.setName("min");
        var.setValue(String.valueOf(annotation.min()));
        field.addVar(var);
        var = new Var();
        var.setName("max");
        var.setValue(String.valueOf(annotation.max()));
        field.addVar(var);
    }

}
