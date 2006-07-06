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
package org.seasar.struts.hotdeploy;

import java.util.Locale;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.ValidatorResources;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ReloadGetFormInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -8512168891032370226L;

    private ValidatorResourcesLoader validatorResourcesLoader;

    public void setValidatorResourcesLoader(ValidatorResourcesLoader validatorResourcesLoader) {
        this.validatorResourcesLoader = validatorResourcesLoader;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Form result;
        ValidatorResources reloadResources = validatorResourcesLoader.load();
        if (invocation.getArguments().length == 2) {
            Locale locale = (Locale) invocation.getArguments()[0];
            String formKey = (String) invocation.getArguments()[1];
            result = reloadResources.getForm(locale, formKey);
        } else {
            String language = (String) invocation.getArguments()[0];
            String country = (String) invocation.getArguments()[1];
            String variant = (String) invocation.getArguments()[2];
            String formKey = (String) invocation.getArguments()[3];
            result = reloadResources.getForm(language, country, variant, formKey);
        }
        if (result != null) {
            return result;
        }
        return invocation.proceed();
    }

}
