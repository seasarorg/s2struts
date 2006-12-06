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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ReloadFindFormBeanConfigsInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1694505679055164995L;

    private ModuleConfigLoader moduleConfigLoader;

    public void setModuleConfigLoader(ModuleConfigLoader moduleConfigLoader) {
        this.moduleConfigLoader = moduleConfigLoader;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        FormBeanConfig[] result = (FormBeanConfig[]) invocation.proceed();

        ModuleConfig config = (ModuleConfig) invocation.getThis();
        ModuleConfig reloadConfig = this.moduleConfigLoader.load(config.getPrefix());
        FormBeanConfig[] formConfigs = reloadConfig.findFormBeanConfigs();

        List mergeFormConfigs = new ArrayList();
        if (result != null) {
            mergeFormConfigs.addAll(Arrays.asList(result));
        }
        if (formConfigs != null) {
            mergeFormConfigs.addAll(Arrays.asList(formConfigs));
        }

        return mergeFormConfigs.toArray(new FormBeanConfig[mergeFormConfigs.size()]);
    }

}
