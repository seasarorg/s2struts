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
package org.seasar.struts.validator.factory;

import java.lang.reflect.Method;

import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.validator.annotation.backport175.ValidateOrder;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class Backport175PropertyDescComparator extends ConstantPropertyDescComparator {

    public Backport175PropertyDescComparator(BeanDesc beanDesc) {
        super(beanDesc);
    }

    protected int getOrder(PropertyDesc propDesc) {
        Method method = propDesc.getWriteMethod();
        ValidateOrder order = (ValidateOrder) Annotations
                .getAnnotation(ValidateOrder.class, method);
        if (order == null) {
            return super.getOrder(propDesc);
        }
        return order.value();
    }

}
