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
package org.seasar.struts.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.exception.MethodNotFoundException;
import org.seasar.struts.util.BindingUtil;
import org.seasar.struts.util.ClassRegister;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class PojoActionExecuteCommand implements ActionCommand {

    private ClassRegister classRegister;

    private List commandList = new ArrayList();

    public String execute(HttpServletRequest request,
            HttpServletResponse response, Object action, Object form,
            ActionMapping mapping) {

        String actionName = mapping.getType();
        Class actionInterface = this.classRegister.getClass(actionName);
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ComponentDef componentDef = container.getComponentDef(actionInterface);
        BeanDesc beanDesc = new BeanDescImpl(componentDef.getComponentClass());

        BindingUtil.importProperties(action, container, beanDesc, mapping);
        String forward = executeCommand(request, response, actionInterface,
                action, form, mapping);
        BindingUtil.exportProperties(action, container, beanDesc, mapping);

        return forward;
    }

    private String executeCommand(HttpServletRequest request,
            HttpServletResponse response, Class actionInterface, Object action,
            Object form, ActionMapping mapping) {

        Method[] methods = actionInterface.getMethods();
        if (methods.length == 0) {
            throw new MethodNotFoundException("Action is "
                    + actionInterface.getName());
        }
        for (Iterator it = commandList.iterator(); it.hasNext();) {
            PojoActionCommand command = (PojoActionCommand) it.next();
            String forward = command.execute(request, response, actionInterface,
                    action, form, mapping);
            if (!PojoActionCommand.NOT_EXECUTE.equals(forward)) {
                return forward;
            }
        }

        return NOT_EXECUTE;
    }

    public void addPojoActionCommand(PojoActionCommand pojoActionCommand) {
        commandList.add(pojoActionCommand);
    }

    /**
     * @param classRegister
     *            The classRegister to set.
     */
    public void setClassRegister(ClassRegister classRegister) {
        this.classRegister = classRegister;
    }

}
