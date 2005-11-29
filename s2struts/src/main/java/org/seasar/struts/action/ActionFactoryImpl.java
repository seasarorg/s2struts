/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.util.ClassRegister;

/**
 * @author Satoshi Kimura
 * @author higa
 * @author Katsuhiko Nagashima
 */
public class ActionFactoryImpl implements ActionFactory {
    
    private ClassRegister classRegister;
    
    private ComponentNameCreator componentNameCreator;

    public ActionFactoryImpl() {
    }

    public Action getActionWithClassName(String className, ActionServlet servlet) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        Class clazz = this.classRegister.getClass(className);
        synchronized (container) {
            if (false == container.hasComponentDef(clazz)) {
                container.register(clazz);
            }
            Action action = (Action) container.getComponent(clazz);
            setServlet(action, servlet);
            return action;
        }
    }

    public Action getActionWithComponentName(String componentName, ActionServlet servlet)
            throws ComponentNotFoundRuntimeException {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        synchronized (container) {
            Action action = (Action) container.getComponent(componentName);
            setServlet(action, servlet);
            return action;
        }
    }

    public Action processActionCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping,
            Log log, MessageResources internal, ActionServlet servlet) throws IOException {

        Action instance = null;
        try {
            instance = (Action) getActionInstance(request, response, mapping, log, internal, servlet);
        } catch (Exception e) {
            processExceptionActionCreate(response, mapping, log, internal, e);
            return null;
        }
        return instance;
    }

    public Object getActionInstance(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping,
            Log log, MessageResources internal, ActionServlet servlet) throws IOException {
        Object actionInstance = null;
        S2Container container = SingletonS2ContainerFactory.getContainer();
        try {
            if (isCreateActionWithComponentName(mapping)) {
                String componentName = this.componentNameCreator.createComponentName(container, mapping);
                actionInstance = getActionWithComponentName(componentName, servlet);
            } else {
                String actionClassName = mapping.getType();
                if (log.isDebugEnabled()) {
                    log.debug(" Looking for Action instance for class " + actionClassName);
                }
                Class componentKey = this.classRegister.getClass(actionClassName);
                actionInstance = container.getComponent(componentKey);
            }
        } catch (Exception e) {
            processExceptionActionCreate(container.getResponse(), mapping, log, internal, e);
            return null;
        }

        if (actionInstance instanceof Action) {
            setServlet((Action) actionInstance, servlet);
        }

        return actionInstance;
    }

    public void setClassRegister(ClassRegister classRegister) {
        this.classRegister = classRegister;
    }
    
    public void setComponentNameCreator(ComponentNameCreator componentNameCreator) {
        this.componentNameCreator = componentNameCreator;
    }

    private static void processExceptionActionCreate(HttpServletResponse response, ActionMapping mapping, Log log,
            MessageResources internal, Exception e) throws IOException {
        String internalMessage = internal.getMessage("actionCreate", mapping.getPath());

        log.error(internalMessage, e);

        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, internalMessage);
    }

    private static void setServlet(Action action, ActionServlet servlet) {
        if (action != null && servlet != null) {
            action.setServlet(servlet);
        }
    }

    private static boolean isCreateActionWithComponentName(ActionMapping mapping) {
        String type = mapping.getType();
        String forward = mapping.getForward();
        String include = mapping.getInclude();
        if (type == null && forward == null && include == null) {
            return true;
        } else {
            return false;
        }
    }

}