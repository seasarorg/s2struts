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

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;

/**
 * @author Satoshi Kimura
 */
public class StringDynaActionFormClass extends DynaActionFormClass {
    private static final long serialVersionUID = -6780598674384267875L;

	public StringDynaActionFormClass() {
        super(null);
    }
    
    protected StringDynaActionFormClass(FormBeanConfig config) {
        super(config);
    }

    protected void introspect(FormBeanConfig formBeanConfig) {
        if (formBeanConfig == null) {
            return;
        }
        try {
            super.introspect(formBeanConfig);
        } catch (Exception e) {
            // ignore
        }
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public DynaProperty getDynaProperty(String propertyName) {
        return new DynaProperty(propertyName, String.class);
    }

    public DynaProperty[] getDynaProperties() {
        throw new UnsupportedOperationException();
    }

    public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
        throw new UnsupportedOperationException();
    }

}
