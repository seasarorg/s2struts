package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.seasar.struts.validator.annotation.Minlength;

public class MinlengthConfigRegisterImpl implements ConfigRegister {

    public void regist(Field field, Method method) {
        Minlength annotation = method.getAnnotation(Minlength.class);
        if (annotation == null) {
            return;
        }

        Arg arg = new Arg();
        arg.setName(annotation.type());
        arg.setKey("${var:" + annotation.type() + "}");
        arg.setResource(false);
        arg.setPosition(1);
        field.addArg(arg);

        Var var = new Var();
        var.setName(annotation.type());
        var.setValue(String.valueOf(annotation.value()));
        field.addVar(var);
        var = new Var();
        var.setName("charset");
        var.setValue(annotation.charset());
        field.addVar(var);
    }

}
