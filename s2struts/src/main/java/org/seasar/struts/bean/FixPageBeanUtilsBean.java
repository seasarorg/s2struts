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
package org.seasar.struts.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.struts.validator.DynaValidatorForm;
import org.apache.struts.validator.ValidatorForm;

/**
 * {@link ValidatorForm} と {@link DynaValidatorForm} の page
 * プロパティをチェックし、値が不正な場合は修正します。
 * 
 * @author nakamura-to
 */
public class FixPageBeanUtilsBean extends BeanUtilsBean {

    /**
     * インスタンスを生成します。
     * 
     * @param convertUtilsBean
     * @param propertyUtilsBean
     */
    public FixPageBeanUtilsBean(ConvertUtilsBean convertUtilsBean, PropertyUtilsBean propertyUtilsBean) {
        super(convertUtilsBean, propertyUtilsBean);
    }

    public void populate(Object bean, Map properties) throws IllegalAccessException, InvocationTargetException {
        super.populate(bean, properties);
        if ((bean == null) || (properties == null)) {
            return;
        }
        fixPage(bean);
    }

    protected void fixPage(Object bean) {
        if (bean instanceof ValidatorForm) {
            ValidatorForm form = (ValidatorForm) bean;
            if (form.getPage() < 0) {
                form.setPage(Integer.MAX_VALUE);
            }
        } else if (bean instanceof DynaValidatorForm) {
            DynaValidatorForm form = (DynaValidatorForm) bean;
            if (form.getPage() < 0 || getPage(form) < 0) {
                form.setPage(Integer.MAX_VALUE);
                form.getMap().put("page", new Integer(Integer.MAX_VALUE));
            }
        }
    }

    protected int getPage(DynaValidatorForm form) {
        Object page = form.getMap().get("page");
        if (page instanceof Integer) {
            return ((Integer) page).intValue();
        }
        return 0;
    }
}
