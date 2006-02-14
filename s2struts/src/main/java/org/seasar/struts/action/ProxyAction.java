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
package org.seasar.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * diconファイルに記述されたActionクラスを使用して、処理を実行する。 <br>
 * struts-configに記述したactionのpathと、diconファイルに記述したcomponentのnameを一致させる事により、 diconファイルに記述したActionクラスで処理する事が出来る。
 * 
 * @author Satoshi Kimura
 */
public final class ProxyAction extends Action {

    /**
     * @see ComponentNameCreator#createComponentName(S2Container, ActionMapping)
     * @see ActionUtil#getActionWithComponentName(String, ActionServlet)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Action delegateAction = createAction(mapping);
        return delegateAction.execute(mapping, form, request, response);
    }

    private Action createAction(ActionMapping mapping) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ComponentNameCreator componentNameCreator = (ComponentNameCreator) container
                .getComponent(ComponentNameCreator.class);
        String componentName = componentNameCreator.createComponentName(container, mapping);
        Action action = getActionCreator().getActionWithComponentName(componentName, getServlet());
        return action;
    }

    private static ActionFactory getActionCreator() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ActionFactory) container.getComponent(ActionFactory.class);
    }
}