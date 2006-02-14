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
package org.seasar.struts.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.DynaClass;
import org.apache.struts.validator.BeanValidatorForm;

/**
 * @author Satoshi Kimura
 */
public class InputValueForm extends BeanValidatorForm {
    private Map map = new HashMap();

    private boolean freeze = false;

    public InputValueForm() {
        super(new Object());
        super.dynaBean = null;
    }

    protected InputValueForm(Object bean) {
        super(bean);
    }

    public DynaClass getDynaClass() {
        return new StringDynaActionFormClass();
    }

    public synchronized Object get(String name) {
        Object ret = this.map.get(name);
        if (ret == null && !this.freeze) {
            ret = new InputValueForm();
            this.map.put(name, ret);
        }
        return ret;
    }

    public synchronized void set(String name, Object value) {
        this.map.put(name, value);
    }

    public void freeze() {
        this.freeze = true;
        for (Iterator ite = this.map.values().iterator(); ite.hasNext();) {
            Object value = ite.next();
            if (value instanceof InputValueForm) {
                ((InputValueForm)value).freeze();
            }
        }
    }

}
