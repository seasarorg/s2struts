package org.seasar.struts.validator.config;

import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;

/**
 * @author Katsuhiko Nagashima
 */
public abstract class AbstractLengthConfigRegister implements ConfigRegister {

    public void regist(Field field, Map parameters) {
        Object value = parameters.get("value");
        String type = getType();

        Arg arg = new Arg();
        arg.setName(type);
        arg.setKey("${var:" + type + "}");
        arg.setResource(false);
        arg.setPosition(1);
        field.addArg(arg);

        Var var = new Var();
        var.setName(type);
        var.setValue(value.toString());
        field.addVar(var);
    }

    protected abstract String getType();

}