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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.commands.AbstractCreateAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.seasar.struts.pojo.MethodBindingAction;
import org.seasar.struts.pojo.MethodBindingActionFactory;

/**
 * MethodBindingで実行された場合、MethodBindingを実行するためのアダプタとなるMethodBindingActionを生成し、ActionContextに設定する
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class CreateMethodBindingAction extends AbstractCreateAction {

    protected Action getAction(ActionContext context, String type, ActionConfig actionConfig) throws Exception {
        ServletActionContext saContext = (ServletActionContext) context;
        ActionServlet actionServlet = saContext.getActionServlet();
        HttpServletRequest request = saContext.getRequest();
        ActionMapping mapping = (ActionMapping) actionConfig;

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, mapping,
                actionServlet);
        if (action != null) {
            return action;
        }

        return null;
    }

}
