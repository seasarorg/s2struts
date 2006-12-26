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
import org.apache.commons.validator.Msg;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class MessageConfigRegisterImpl implements ConfigRegister {

    private String defaultBundle = null;

    private boolean defaultResource = true;

    public void register(Field field, Map parameter) {
        String name = (String) parameter.get("name");
        String key = (String) parameter.get("key");
        String bundle = (String) parameter.get("bundle");
        if (StringUtil.isEmpty(bundle)) {
            bundle = this.defaultBundle;
        }
        String resourceStr = (String) parameter.get("resource");
        boolean resource = this.defaultResource;
        if (!StringUtil.isEmpty(resourceStr)) {
            resource = BooleanConversionUtil.toPrimitiveBoolean(resourceStr);
        }

        Msg msg = new Msg();
        msg.setBundle(bundle);
        msg.setKey(key);
        msg.setName(name);
        msg.setResource(resource);
        field.addMsg(msg);
    }

    public void setBundle(String bundle) {
        this.defaultBundle = bundle;
    }

    public void setResource(boolean resource) {
        this.defaultResource = resource;
    }

}
