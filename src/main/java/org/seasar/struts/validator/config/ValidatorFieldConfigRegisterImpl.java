/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.validator.config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class ValidatorFieldConfigRegisterImpl implements ConfigRegister {

    public void register(Field field, Map parameter) {
        addValidators(field, parameter);
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
        boolean varResource = BooleanConversionUtil.toPrimitiveBoolean(var
                .get("resource"));
        boolean varArg = BooleanConversionUtil.toPrimitiveBoolean(var
                .get("arg"));

        if (StringUtil.isEmpty(varKey) && !varArg) {
            return;
        }
        Arg arg = new Arg();
        arg.setName(validatorName);
        if (StringUtil.isEmpty(varKey)) {
            arg.setKey("${var:" + varName + "}");
            arg.setResource(false);
        } else {
            arg.setKey(varKey);
            arg.setResource(varResource);
        }
        arg.setPosition(position);
        field.addArg(arg);
    }

}