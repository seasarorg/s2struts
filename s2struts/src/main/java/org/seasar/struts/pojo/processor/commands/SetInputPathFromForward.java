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
package org.seasar.struts.pojo.processor.commands;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.chain.commands.ActionCommandBase;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.ModuleUtils;
import org.apache.struts.util.RequestUtils;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 無設定でActionConfig input属性を設定するための情報としてActionからForwardするuriをS2StrutsContextに設定する。
 * 
 * ここで設定したuriはPageTagでの設定やSetActionConfigInputで利用する。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SetInputPathFromForward extends ActionCommandBase {

    public boolean execute(ActionContext actionContext) throws Exception {
        ForwardConfig forwardConfig = actionContext.getForwardConfig();
        if (forwardConfig == null) {
            return false;
        }

        ServletActionContext saContext = (ServletActionContext) actionContext;
        String uri = forwardConfig.getPath();
        if (uri == null) {
            return false;
        }

        HttpServletRequest request = saContext.getRequest();
        ServletContext servletContext = saContext.getContext();

        if (uri.startsWith("/")) {
            uri = resolveModuleRelativePath(forwardConfig, servletContext, request);
        }

        S2StrutsContextUtil.setPath(uri);
        return false;
    }

    private String resolveModuleRelativePath(ForwardConfig forwardConfig, ServletContext servletContext,
            HttpServletRequest request) {
        String prefix = forwardConfig.getModule();
        ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(prefix, request, servletContext);
        return RequestUtils.forwardURL(request, forwardConfig, moduleConfig);
    }

}
