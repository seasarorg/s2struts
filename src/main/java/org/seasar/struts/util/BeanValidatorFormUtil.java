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

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.struts.form.S2BeanValidatorForm;

/**
 * @author Katsuhiko Nagashima
 */
public class BeanValidatorFormUtil {

    private BeanValidatorFormUtil() {
    }

    public static Object toBean(Object bean) {
        if (bean instanceof BeanValidatorForm) {
            BeanValidatorForm beanValidatorForm = (BeanValidatorForm) bean;
            WrapDynaBean dynaBean = (WrapDynaBean) beanValidatorForm
                    .getDynaBean();
            return dynaBean.getInstance();
        }
        return bean;
    }

    public static BeanValidatorForm toBeanValidatorForm(Object oldForm,
            Object newForm) {
        if (oldForm == null) {
            return new S2BeanValidatorForm(new BeanValidatorForm(newForm));
        }

        if (!(oldForm instanceof BeanValidatorForm)) {
            return new S2BeanValidatorForm(new BeanValidatorForm(newForm));
        }
        BeanValidatorForm beanForm = (BeanValidatorForm) oldForm;

        S2BeanValidatorForm result;
        if (beanForm instanceof S2BeanValidatorForm) {
            result = (S2BeanValidatorForm) oldForm;
        } else {
            result = new S2BeanValidatorForm(beanForm);
        }
        result.initBean(newForm);
        return result;
    }

}
