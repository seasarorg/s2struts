package org.seasar.struts.validator.config;

import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;

/**
 * @author Katsuhiko Nagashima
 */
public abstract class AbstractRangeConfigRegister implements ConfigRegister {

    public void regist(Field field, Map parameters) {
        Object min = parameters.get("min");
        Object max = parameters.get("max");
        String type = getType();
        
        Arg arg = new Arg();
        arg.setName(type);
        arg.setKey("${var:min}");
        arg.setResource(false);
        arg.setPosition(1);
        field.addArg(arg);
        arg = new Arg();
        arg.setName(type);
        arg.setKey("${var:max}");
        arg.setResource(false);
        arg.setPosition(2);
        field.addArg(arg);

        Var var = new Var();
        var.setName("min");
        var.setValue(min.toString());
        field.addVar(var);
        var = new Var();
        var.setName("max");
        var.setValue(max.toString());
        field.addVar(var);
    }
    
    protected abstract String getType();

}
