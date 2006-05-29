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
package org.seasar.struts.zeroconfig.hotdeploy;

import java.util.Locale;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.FormSet;
import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.ModuleUtils;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.log.Logger;
import org.seasar.struts.util.S2StrutsContextUtil;
import org.seasar.struts.zeroconfig.cooldeploy.ValidationCreator;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class OndemandGetFormInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 5721349582842757290L;

    private static final Logger log = Logger.getLogger(OndemandGetFormInterceptor.class);

    private ValidationCreator validationCreator;

    public void setValidationCreator(ValidationCreator validationCreator) {
        this.validationCreator = validationCreator;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        ValidatorResources resources = (ValidatorResources) invocation.getThis();
        String formKey;
        if (invocation.getArguments()[0] instanceof Locale) {
            formKey = (String) invocation.getArguments()[1];
        } else {
            formKey = (String) invocation.getArguments()[3];
        }

        ModuleConfig config = ModuleUtils.getInstance().getModuleConfig(
                S2StrutsContextUtil.getRequest());
        Form form = this.validationCreator.createForm(config, formKey);
        if (form != null) {
            if (log.isDebugEnabled()) {
                log.debug("auto create " + form);
            }

            // initialize form...
            FormSet formSet = new FormSet();
            formSet.addForm(form);
            resources.addFormSet(formSet);
            resources.process();
            // ...initialized form

            return form;
        }
        return invocation.proceed();
    }

}
