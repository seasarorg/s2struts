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
package org.seasar.struts;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ControllerConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.action.ClassRegister;
import org.seasar.struts.action.ProxyAction;
import org.seasar.struts.processor.ExternalRequestProcessor;

/**
 * initialize for use S2Struts.
 * 
 * @author Satoshi Kimura
 */
public abstract class S2StrutsInitializer {
    private static Log log = LogFactory.getLog(S2StrutsInitializer.class);

    private static void register(S2Container container, Class interfaceClass, Class component, InstanceDef instanceType) {
        ComponentDef cd = new ComponentDefImpl(component);
        cd.setInstanceDef(instanceType);
        register(container, interfaceClass, cd);
    }

    private static void register(S2Container container, Class interfaceClass, ComponentDef component) {
        if (false == container.hasComponentDef(interfaceClass)) {
            log.debug("register " + interfaceClass + " : component=" + component.getComponentClass());
            container.register(component);
        } else {
            log.debug(interfaceClass + " has already been registered.");
        }
    }

    private static void registerActionClass(ActionServlet servlet) {
        ModuleConfig[] configs = getModuleConfigs(servlet);
        for (int i = 0; i < configs.length; i++) {
            registerActionClass(servlet, configs[i]);
        }

    }

    public static void initServlet(ActionServlet servlet) {
        registerActionClass(servlet);
        registerRequestProcessor(servlet);
    }

    public static void registerActionClass(ActionServlet servlet, ModuleConfig config) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ActionConfig[] actionConfigs = config.findActionConfigs();
        ClassRegister classRegister = (ClassRegister) container.getComponent(ClassRegister.class);
        for (int i = 0; i < actionConfigs.length; i++) {
            ActionConfig actionConfig = actionConfigs[i];
            String actionClassName = actionConfig.getType();
            if (actionClassName != null) {
                Class actionClass = classRegister.getClass(actionClassName);
                if (actionClass.isInterface() == false) {
                    ComponentDef def = new ComponentDefImpl(actionClass);
                    if (actionClass.equals(ProxyAction.class) == false) {
                        def.setInstanceDef(InstanceDefFactory.PROTOTYPE);
                    }
                    register(container, actionClass, def);
                }
            }
        }
    }

    private static ModuleConfig[] getModuleConfigs(ActionServlet actionServlet) {
        List values = new ArrayList();
        Enumeration names = actionServlet.getServletContext().getAttributeNames();
        while (names.hasMoreElements()) {
            values.add(names.nextElement());
        }

        Iterator keys = values.iterator();
        List moduleConfigs = new ArrayList();
        while (keys.hasNext()) {
            String name = (String) keys.next();
            Object value = actionServlet.getServletContext().getAttribute(name);

            if (value instanceof ModuleConfig) {
                moduleConfigs.add(value);
            }
        }
        return (ModuleConfig[]) moduleConfigs.toArray(new ModuleConfig[moduleConfigs.size()]);
    }

    private static void registerRequestProcessor(ActionServlet servlet) {
        ModuleConfig[] configs = getModuleConfigs(servlet);
        for (int i = 0; i < configs.length; i++) {
            registerRequestProcessor(servlet, configs[i]);
        }
    }

    private static void registerRequestProcessor(ActionServlet servlet, ModuleConfig config) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ControllerConfig controllerConfig = config.getControllerConfig();
        String processorClassName = controllerConfig.getProcessorClass();
        Class processorClass = ClassUtil.forName(processorClassName);
        if (ExternalRequestProcessor.class.isAssignableFrom(processorClass)) {
            register(container, ExternalRequestProcessor.class, processorClass, InstanceDefFactory.PROTOTYPE);
        }
    }

}