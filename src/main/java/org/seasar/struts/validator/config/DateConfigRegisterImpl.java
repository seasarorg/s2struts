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

import java.util.Map;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class DateConfigRegisterImpl implements ConfigRegister {

    private String defaultPattern = null;

    private boolean defaultStrict = false;

    public void register(Field field, Map parameter) {
        String pattern = (String) parameter.get("pattern");
        if (StringUtil.isEmpty(pattern)) {
            pattern = this.defaultPattern;
        }
        String strictStr = (String) parameter.get("strict");
        boolean strict = this.defaultStrict;
        if (!StringUtil.isEmpty(strictStr)) {
            strict = BooleanConversionUtil.toPrimitiveBoolean(strictStr);
        }

        Var var = new Var();
        if (strict) {
            var.setName("datePatternStrict");
        } else {
            var.setName("datePattern");
        }
        var.setValue(pattern);
        field.addVar(var);
    }

    public void setPattern(String pattern) {
        this.defaultPattern = pattern;
    }

    public void setStrict(boolean strict) {
        this.defaultStrict = strict;
    }

}
