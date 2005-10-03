package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.struts.validator.annotation.Minlength;

/**
 * @author Satoshi Kimura
 */
public class MinlengthConfigRegisterImpl implements ConfigRegister {

    public void regist(Field field, Method method) {
        Annotation annotation = Annotations.getAnnotation(Minlength.class, method);
        if (annotation == null) {
            return;
        }
        Minlength minlength = (Minlength)annotation;

        Arg arg = new Arg();
        arg.setName(minlength.type());
        arg.setKey("${var:" + minlength.type() + "}");
        arg.setResource(false);
        arg.setPosition(1);
        field.addArg(arg);

        Var var = new Var();
        var.setName(minlength.type());
        var.setValue(String.valueOf(minlength.value()));
        field.addVar(var);
        var = new Var();
        var.setName("charset");
        var.setValue(minlength.charset());
        field.addVar(var);
    }

}
