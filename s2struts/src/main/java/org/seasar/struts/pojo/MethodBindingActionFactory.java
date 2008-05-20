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
package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

/**
 * {@link MethodBindingAction}のファクトリです。
 * 
 * @author Katsuhiko Nagashima
 */
public class MethodBindingActionFactory {

    /**
     * {@link MethodBindingAction}を作成します。
     * 
     * @param request
     * @param mapping
     * @param servlet
     * @return {@link MethodBindingAction}
     */
    public static MethodBindingAction createMethodBindingAction(HttpServletRequest request, ActionMapping mapping,
            ActionServlet servlet) {

        MethodBinding methodBinding = MethodBindingFactory.getMethodBinding(request);
        if (methodBinding != null) {
            MethodBindingAction action = new MethodBindingAction(methodBinding);
            action.setServlet(servlet);
            return action;
        }
        return null;
    }

}
