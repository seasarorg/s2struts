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
import org.apache.struts.config.MessageResourcesConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.ValidatorPlugIn;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.hotdeploy.ModuleConfigWrapper;
import org.seasar.struts.hotdeploy.ReloadMessageResourcesFactory;
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

        initModuleConfig(context, config);
        initValidatorResources(context, config);
        initMessageResources(context, config);
    }

    private void initModuleConfig(ServletContext context, ModuleConfig config) {
        S2Container container = getContainer();
        if (!container.hasComponentDef(ModuleConfigWrapper.class)) {
            return;
        }

        ModuleConfigWrapper configWrapper = (ModuleConfigWrapper) container.getComponent(ModuleConfigWrapper.class);
        configWrapper.init(config);
        context.setAttribute(Globals.MODULE_KEY + config.getPrefix(), configWrapper);
    }

    private void initValidatorResources(ServletContext context, ModuleConfig config) {
        S2Container container = getContainer();
        if (!container.hasComponentDef(ValidatorResourcesWrapper.class)) {
            return;
        }

        ValidatorResourcesWrapper resourcesWrapper = (ValidatorResourcesWrapper) container
                .getComponent(ValidatorResourcesWrapper.class);
        ValidatorResources resources = (ValidatorResources) context.getAttribute(ValidatorPlugIn.VALIDATOR_KEY
                + config.getPrefix());
        resourcesWrapper.init(resources);
        context.setAttribute(ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix(), resourcesWrapper);
    }

    private void initMessageResources(ServletContext context, ModuleConfig config) {
        S2Container container = getContainer();
        if (!container.hasComponentDef(ReloadMessageResourcesFactory.class)) {
            return;
        }

        ReloadMessageResourcesFactory reloadResourcesFactory = (ReloadMessageResourcesFactory) container
                .getComponent(ReloadMessageResourcesFactory.class);
        MessageResourcesConfig mrcs[] = config.findMessageResourcesConfigs();
        for (int i = 0; i < mrcs.length; i++) {
            MessageResources resources = (MessageResources) context.getAttribute(mrcs[i].getKey() + config.getPrefix());
            MessageResources reloadResources = reloadResourcesFactory.createResources(resources);
            context.setAttribute(mrcs[i].getKey() + config.getPrefix(), reloadResources);
        }
    }

    private S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

}
