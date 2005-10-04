package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.validator.annotation.Validator;
import org.seasar.struts.validator.annotation.ValidatorField;
import org.seasar.struts.validator.annotation.Variable;

public class ValidatorFieldConfigRegisterImpl implements ConfigRegister {

    public void regist(Field field, Method method) {
        ValidatorField validatorField = method.getAnnotation(ValidatorField.class);
        if (validatorField == null) {
            return;
        }

        addValidators(field, validatorField);
    }

    private void addValidators(Field field, ValidatorField validatorField) {

        if (validatorField.validators() == null) {
            return;
        }
        for (int i = 0; i < validatorField.validators().length; i++) {
            Validator val = validatorField.validators()[i];
            
            addValidatorVariable(field, val);
            addValidatorArg(field, val, 1);

            addVariables(field, val);
        }
    }

    private void addVariables(Field field, Validator validator) {

        if (validator.vars() == null) {
            return;
        }
        for (int i = 0; i < validator.vars().length; i++) {
            Variable var = validator.vars()[i];

            addVariable(field, var);
            addArg(field, validator, var, i + 1);
        }
    }

    private void addValidatorVariable(Field field, Validator validator) {

        if (StringUtil.isEmpty(validator.value())) {
            return;
        }
        Var v = new Var();
        v.setName(validator.name());
        v.setValue(validator.value());
        field.addVar(v);
    }

    private void addValidatorArg(Field field, Validator validator, int position) {

        if (StringUtil.isEmpty(validator.key()) && !validator.arg()) {
            return;
        }
        Arg arg = new Arg();
        arg.setName(validator.name());
        if (StringUtil.isEmpty(validator.key())) {
            arg.setKey("${var:" + validator.name() + "}");
            arg.setResource(false);
        } else {
            arg.setKey(validator.key());
            arg.setResource(validator.resource());
        }
        arg.setPosition(position);
        field.addArg(arg);
    }

    private void addVariable(Field field, Variable var) {

        if (StringUtil.isEmpty(var.value())) {
            return;
        }
        Var v = new Var();
        v.setName(var.name());
        v.setValue(var.value());
        field.addVar(v);
    }

    private void addArg(Field field, Validator validator, Variable var, int position) {

        if (StringUtil.isEmpty(var.key()) && !var.arg()) {
            return;
        }
        Arg arg = new Arg();
        arg.setName(validator.name());
        if (StringUtil.isEmpty(var.key())) {
            arg.setKey("${var:" + var.name() + "}");
            arg.setResource(false);
        } else {
            arg.setKey(var.key());
            arg.setResource(var.resource());
        }
        arg.setPosition(position);
        field.addArg(arg);
    }

}
