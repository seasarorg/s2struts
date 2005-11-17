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

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;

/**
 * @author Satoshi Kimura
 */
public class DateConfigRegisterImpl implements ConfigRegister {

    private String pattern;

    public void regist(Field field, Map parameter) {
        String datePattern = (String) parameter.get("pattern");

        Var var = new Var();
        var.setName("datePattern");
        if (datePattern != null) {
            var.setValue(datePattern);
        } else {
            var.setValue(this.pattern);
        }
        field.addVar(var);
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
