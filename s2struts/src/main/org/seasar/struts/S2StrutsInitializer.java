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
import org.seasar.struts.action.ActionFactory;
import org.seasar.struts.action.ActionFactoryImpl;
import org.seasar.struts.action.ComponentNameCreator;
import org.seasar.struts.action.ComponentNameCreatorImpl;
import org.seasar.struts.action.MessageFacade;
import org.seasar.struts.action.MessageFacadeImpl;
import org.seasar.struts.action.ProxyAction;
import org.seasar.struts.config.AutoStrutsConfigRule;
import org.seasar.struts.config.AutoStrutsConfigRuleImpl;
import org.seasar.struts.config.rule.ZeroConfigActionFormRule;
import org.seasar.struts.config.rule.ZeroConfigActionFormRuleImpl;
import org.seasar.struts.config.rule.ZeroConfigActionRule;
import org.seasar.struts.config.rule.ZeroConfigActionRuleImpl;
import org.seasar.struts.processor.Acceptor;
import org.seasar.struts.processor.AcceptorImpl;
import org.seasar.struts.processor.ActionExecuteProcessor;
import org.seasar.struts.processor.ActionExecuteProcessorImpl;
import org.seasar.struts.processor.ExternalRequestProcessor;
import org.seasar.struts.processor.RequestProcessorFactory;
import org.seasar.struts.processor.RequestProcessorFactoryImpl;
import org.seasar.struts.util.ClassRegister;
import org.seasar.struts.util.ClassRegisterImpl;

/**
 * initialize for use S2Struts.
 * 
 * @author Satoshi Kimura
 */
public abstract class S2StrutsInitializer {
    private static Log log = LogFactory.getLog(S2StrutsInitializer.class);

    /**
     * regist component to {@link S2Container}, for use S2Struts.
     */
    public static void init() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        regist(container, ActionFactory.class, ActionFactoryImpl.class);
        regist(container, ComponentNameCreator.class, ComponentNameCreatorImpl.class);
        regist(container, MessageFacade.class, MessageFacadeImpl.class, InstanceDefFactory.REQUEST);
        regist(container, AutoStrutsConfigRule.class, AutoStrutsConfigRuleImpl.class);
        regist(container, ZeroConfigActionRule.class, ZeroConfigActionRuleImpl.class);
        regist(container, ZeroConfigActionFormRule.class, ZeroConfigActionFormRuleImpl.class);
        regist(container, ClassRegister.class, ClassRegisterImpl.class);
        regist(container, Acceptor.class, AcceptorImpl.class);
        regist(container, ActionExecuteProcessor.class, ActionExecuteProcessorImpl.class);
        regist(container, RequestProcessorFactory.class, RequestProcessorFactoryImpl.class);
    }

    private static void regist(S2Container container, Class interfaceClass, Class component) {
        ComponentDef cd = new ComponentDefImpl(component);
        regist(container, interfaceClass, cd);
    }

    private static void regist(S2Container container, Class interfaceClass, Class component, InstanceDef instanceType) {
        ComponentDef cd = new ComponentDefImpl(component);
        cd.setInstanceDef(instanceType);
        regist(container, interfaceClass, cd);
    }

    private static void regist(S2Container container, Class interfaceClass, ComponentDef component) {
        if (false == container.hasComponentDef(interfaceClass)) {
            log.debug("regist " + interfaceClass + " : component=" + component.getComponentClass());
            container.register(component);
        } else {
            log.debug(interfaceClass + " has already been registered.");
        }
    }

    private static void registActionClass(ActionServlet servlet) {
        ModuleConfig[] configs = getModuleConfigs(servlet);
        for (int i = 0; i < configs.length; i++) {
            registActionClass(servlet, configs[i]);
        }

    }

    public static void initServlet(ActionServlet servlet) {
        registActionClass(servlet);
        registRequestProcessor(servlet);
    }

    public static void registActionClass(ActionServlet servlet, ModuleConfig config) {
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
                    regist(container, actionClass, def);
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

    private static void registRequestProcessor(ActionServlet servlet) {
        ModuleConfig[] configs = getModuleConfigs(servlet);
        for (int i = 0; i < configs.length; i++) {
            registRequestProcessor(servlet, configs[i]);
        }
    }

    private static void registRequestProcessor(ActionServlet servlet, ModuleConfig config) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ControllerConfig controllerConfig = config.getControllerConfig();
        String processorClassName = controllerConfig.getProcessorClass();
        Class processorClass = ClassUtil.forName(processorClassName);
        if (ExternalRequestProcessor.class.isAssignableFrom(processorClass)) {
            regist(container, ExternalRequestProcessor.class, processorClass, InstanceDefFactory.PROTOTYPE);
        }
    }

}