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
package org.seasar.struts.zeroconfig.hotdeploy;

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

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class HotdeployPlugIn implements PlugIn {

    public void destroy() {
    }

    public void init(ActionServlet actionServlet, ModuleConfig config) throws ServletException {
        ServletContext context = actionServlet.getServletContext();
        
        HotdeployModuleConfig hotdeployConfig = new HotdeployModuleConfig(config);
        context.setAttribute(Globals.MODULE_KEY + config.getPrefix(), hotdeployConfig);
        
        ValidatorResources resources = (ValidatorResources) context.getAttribute(ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix());
        HotdeployValidatorResources hotdeployResources = new HotdeployValidatorResources(resources);
        hotdeployResources.setValidationCreator(getValidationCreator());
        context.setAttribute(ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix(), hotdeployResources);
    }

    private ValidationCreator getValidationCreator() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ValidationCreator) container.getComponent(ValidationCreator.class);
    }

}
