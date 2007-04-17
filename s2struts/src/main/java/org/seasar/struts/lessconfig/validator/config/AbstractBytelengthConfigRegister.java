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
package org.seasar.struts.lessconfig.validator.config;

import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.seasar.framework.util.StringUtil;

/**
 * @author Katsuhiko Nagashima
 */
public abstract class AbstractBytelengthConfigRegister implements ConfigRegister {

    private String defaultCharset = null;

    public void register(Field field, Map parameter) {
        Object value = parameter.get("value");
        String charset = (String) parameter.get("charset");
        if (StringUtil.isEmpty(charset)) {
            charset = this.defaultCharset;
        }
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
        var = new Var();
        var.setName("charset");
        var.setValue(charset);
        field.addVar(var);
    }

    public void setCharset(String charset) {
        this.defaultCharset = charset;
    }

    protected abstract String getType();

}