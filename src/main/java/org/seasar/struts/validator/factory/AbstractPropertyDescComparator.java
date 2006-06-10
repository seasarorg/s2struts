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
package org.seasar.struts.validator.factory;

import java.util.Comparator;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public abstract class AbstractPropertyDescComparator implements Comparator {
    
    protected BeanDesc beanDesc;
    
    public AbstractPropertyDescComparator(BeanDesc beanDesc) {
        this.beanDesc = beanDesc;
    }

    public int compare(Object arg0, Object arg1) {
        int arg0Order = getOrder((PropertyDesc) arg0);
        int arg1Order = getOrder((PropertyDesc) arg1);
        return arg0Order - arg1Order;
    }
    
    protected abstract int getOrder(PropertyDesc propDesc);

}
