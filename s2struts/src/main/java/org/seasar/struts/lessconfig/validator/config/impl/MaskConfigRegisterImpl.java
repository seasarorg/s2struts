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
package org.seasar.struts.lessconfig.validator.config.impl;

import java.util.Map;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Msg;
import org.apache.commons.validator.Var;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.lessconfig.validator.config.ConfigRegister;

/**
 * 標準検証ルールのmask用の設定を{@link Field}に登録します。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class MaskConfigRegisterImpl implements ConfigRegister {

    public void register(Field field, Map parameter) {
        String pattern = (String) parameter.get("pattern");
        String messageKey = (String) parameter.get("messageKey");
        String resourceStr = (String) parameter.get("resource");
        boolean resource = true;
        if (!StringUtil.isEmpty(resourceStr)) {
            resource = BooleanConversionUtil.toPrimitiveBoolean(resourceStr);
        }

        Var var = new Var();
        var.setName("mask");
        var.setValue(pattern);
        field.addVar(var);

        if (!StringUtil.isEmpty(messageKey)) {
            Msg message = new Msg();
            message.setName("mask");
            message.setKey(messageKey);
            message.setResource(resource);
            field.addMsg(message);
        }
    }

}
