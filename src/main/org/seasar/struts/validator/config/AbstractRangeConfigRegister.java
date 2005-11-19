/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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

import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;

/**
 * @author Katsuhiko Nagashima
 */
public abstract class AbstractRangeConfigRegister implements ConfigRegister {

    public void regist(Field field, Map parameter) {
        Object min = parameter.get("min");
        Object max = parameter.get("max");
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
