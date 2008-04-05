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
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.log.Logger;
import org.seasar.struts.lessconfig.autoregister.ActionConfigCreator;

/**
 * {@link ModuleConfig#findActionConfig(String)}実行時に{@link ActionConfig}をオンデマンドで作成するインターセプタです。
 * 
 * @author Katsuhiko Nagashima
 */
public class OndemandFindActionConfigInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 2656098840576881310L;

    private static final Logger log = Logger.getLogger(OndemandFindActionConfigInterceptor.class);

    private ActionConfigCreator actionConfigCreator;

    /**
     * {@link ActionConfigCreator}を設定します。
     * 
     * @param actionConfigCreator
     */
    public void setActionConfigCreator(ActionConfigCreator actionConfigCreator) {
        this.actionConfigCreator = actionConfigCreator;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        ModuleConfig config = (ModuleConfig) invocation.getThis();
        String path = (String) invocation.getArguments()[0];

        ActionConfig result = this.actionConfigCreator.createActionConfig(config, path);
        if (result != null) {
            if (log.isDebugEnabled()) {
                log.debug("auto create " + result);
                ForwardConfig[] forwardConfigs = result.findForwardConfigs();
                for (int i = 0; i < forwardConfigs.length; i++) {
                    log.debug("auto create " + forwardConfigs[i]);
                }
            }
            return result;
        }
        return invocation.proceed();
    }

}
