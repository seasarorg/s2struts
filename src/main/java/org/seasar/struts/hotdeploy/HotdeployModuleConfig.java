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
package org.seasar.struts.hotdeploy;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class HotdeployModuleConfig extends ModuleConfigImpl {

    private static final long serialVersionUID = -8213815525736934433L;
    
    private static final Logger log = Logger.getLogger(HotdeployModuleConfig.class);

    public HotdeployModuleConfig() {
        super();
    }
    
    public HotdeployModuleConfig(String prefix) {
        super(prefix);
    }

    public ActionConfig findActionConfig(String path) {
        ActionConfig result = getActionConfigCreator().createActionConfig(this, path);
        if (result != null) {
            if (log.isDebugEnabled()) {
                log.debug("auto register " + result);
                ForwardConfig[] forwardConfigs = result.findForwardConfigs();
                for (int i = 0; i < forwardConfigs.length; i++) {
                    log.debug("auto register " + forwardConfigs[i]);
                }
            }
            return result;
        }
        return super.findActionConfig(path);
    }

    public FormBeanConfig findFormBeanConfig(String name) {
        FormBeanConfig result = getActionFormConfigCreator().createFormBeanConfig(this, name);
        if (result != null) {
            if (log.isDebugEnabled()) {
                log.debug("auto register " + result);
            }
            return result;
        }
        return super.findFormBeanConfig(name);
    }
    
    private ActionConfigCreator getActionConfigCreator() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ActionConfigCreator) container.getComponent(ActionConfigCreator.class);
    }

    private ActionFormConfigCreator getActionFormConfigCreator() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ActionFormConfigCreator) container.getComponent(ActionFormConfigCreator.class);
    }

}
