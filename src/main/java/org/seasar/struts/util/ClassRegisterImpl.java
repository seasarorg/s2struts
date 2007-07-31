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
package org.seasar.struts.util;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.util.ClassUtil;

/**
 * @author Satoshi Kimura
 */
public class ClassRegisterImpl implements ClassRegister {
    private Map classes = new HashMap();

    public ClassRegisterImpl() {
    }

    public void register(String type) {
        getClass(type);
    }

    public void register(Class clazz) {
        getClass(clazz.getName());
    }

    public synchronized Class getClass(String type) {
        Class clazz = (Class) this.classes.get(type);
        if (clazz == null) {
            clazz = ClassUtil.forName(type);
            this.classes.put(type, clazz);
        }
        return clazz;
    }

    public synchronized void destroy() {
        this.classes = null;
    }

}