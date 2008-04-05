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
package org.seasar.struts.lessconfig.hotdeploy;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.log.Logger;
import org.seasar.struts.lessconfig.autoregister.ActionFormConfigCreator;

/**
 * {@link ModuleConfig#findFormBeanConfig(String)}実行時に{@link FormBeanConfig}をオンデマンドで作成するインターセプタです。
 * 
 * @author Katsuhiko Nagashima
 */
public class OndemandFindFormBeanConfigInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1039238091161277633L;

    private static final Logger log = Logger.getLogger(OndemandFindFormBeanConfigInterceptor.class);

    private ActionFormConfigCreator formConfigCreator;

    /**
     * {@link ActionFormConfigCreator}を設定します。
     * 
     * @param formConfigCreator
     */
    public void setActionFormConfigCreator(ActionFormConfigCreator formConfigCreator) {
        this.formConfigCreator = formConfigCreator;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        ModuleConfig config = (ModuleConfig) invocation.getThis();
        String name = (String) invocation.getArguments()[0];
        if (name == null) {
            return invocation.proceed();
        }

        FormBeanConfig result = this.formConfigCreator.createFormBeanConfig(config, name);
        if (result != null) {
            if (log.isDebugEnabled()) {
                log.debug("auto create " + result);
            }
            return result;
        }
        return invocation.proceed();
    }

}
