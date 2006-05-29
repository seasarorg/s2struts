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
package org.seasar.struts.hotdeploy.plugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.ValidatorPlugIn;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.hotdeploy.ModuleConfigWrapper;
import org.seasar.struts.hotdeploy.ValidatorResourcesWrapper;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class HotdeployPlugIn implements PlugIn {

    public void destroy() {
    }

    public void init(ActionServlet actionServlet, ModuleConfig config) throws ServletException {
        ServletContext context = actionServlet.getServletContext();
        
        ModuleConfigWrapper configWrapper = getModuleConfigWrapper();
        configWrapper.init(config);
        context.setAttribute(Globals.MODULE_KEY + config.getPrefix(), configWrapper);
        
        ValidatorResources resources = (ValidatorResources) context.getAttribute(ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix());
        ValidatorResourcesWrapper resourcesWrapper = getValidatorResourcesWrapper();
        resourcesWrapper.init(resources);
        context.setAttribute(ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix(), resourcesWrapper);
    }
    
    private ModuleConfigWrapper getModuleConfigWrapper() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ModuleConfigWrapper) container.getComponent(ModuleConfigWrapper.class);
    }

    private ValidatorResourcesWrapper getValidatorResourcesWrapper() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ValidatorResourcesWrapper) container.getComponent(ValidatorResourcesWrapper.class);
    }

}
