package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.struts.validator.annotation.Maxlength;

/**
 * @author Satoshi Kimura
 */
public class MaxlengthConfigRegisterImpl implements ConfigRegister {

    public void regist(Field field, Method method) {
        Annotation annotation = Annotations.getAnnotation(Maxlength.class, method);
        if (annotation == null) {
            return;
        }
        Maxlength maxlength = (Maxlength)annotation;

        Arg arg = new Arg();
        arg.setName(maxlength.type());
        arg.setKey("${var:" + maxlength.type() + "}");
        arg.setResource(false);
        arg.setPosition(1);
        field.addArg(arg);

        Var var = new Var();
        var.setName(maxlength.type());
        var.setValue(String.valueOf(maxlength.value()));
        field.addVar(var);
    }

}
