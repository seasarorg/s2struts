package org.seasar.struts.validator.config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.seasar.framework.util.StringUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class ValidatorFieldConfigRegisterImpl implements ConfigRegister {

    public void regist(Field field, Map parameters) {
        addValidators(field, parameters);
    }

    private void addValidators(Field field, Map validatorField) {
        List validators = (List) validatorField.get("validators");

        if (validators == null) {
            return;
        }
        for (Iterator it = validators.iterator(); it.hasNext();) {
            Map val = (Map) it.next();

            addVariable(field, val);
            addArg(field, val, val, 1);

            addVariables(field, val);
        }
    }

    private void addVariables(Field field, Map validator) {
        List vars = (List) validator.get("vars");

        if (vars == null) {
            return;
        }
        for (int i = 0; i < vars.size(); i++) {
            Map var = (Map) vars.get(i);

            addVariable(field, var);
            addArg(field, validator, var, i + 1);
        }
    }

    private void addVariable(Field field, Map var) {
        String name = (String) var.get("name");
        String value = (String) var.get("value");

        if (StringUtil.isEmpty(value)) {
            return;
        }
        Var v = new Var();
        v.setName(name);
        v.setValue(value);
        field.addVar(v);
    }

    private void addArg(Field field, Map validator, Map var, int position) {
        String validatorName = (String) validator.get("name");
        String varName = (String) var.get("name");
        String varKey = (String) var.get("key");
        Boolean varResource = (Boolean) var.get("resource"); 
        Boolean varArg = (Boolean) var.get("arg"); 

        if (StringUtil.isEmpty(varKey) && !varArg.booleanValue()) {
            return;
        }
        Arg arg = new Arg();
        arg.setName(validatorName);
        if (StringUtil.isEmpty(varKey)) {
            arg.setKey("${var:" + varName + "}");
            arg.setResource(false);
        } else {
            arg.setKey(varKey);
            arg.setResource(varResource.booleanValue());
        }
        arg.setPosition(position);
        field.addArg(arg);
    }

}