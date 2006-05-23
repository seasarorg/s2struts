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

import java.io.Serializable;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ControllerConfig;
import org.apache.struts.config.DataSourceConfig;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.MessageResourcesConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class HotdeployModuleConfig implements ModuleConfig, Serializable {
    
    private static final long serialVersionUID = -3864217606693855421L;

    private static final Logger log = Logger.getLogger(HotdeployModuleConfig.class);

    private ModuleConfig config;
    
    public HotdeployModuleConfig(ModuleConfig config) {
        this.config = config;
    }

    public void addActionConfig(ActionConfig arg0) {
        config.addActionConfig(arg0);
    }
    
    public void addDataSourceConfig(DataSourceConfig arg0) {
        config.addDataSourceConfig(arg0);
    }

    public void addExceptionConfig(ExceptionConfig arg0) {
        config.addExceptionConfig(arg0);
    }

    public void addFormBeanConfig(FormBeanConfig arg0) {
        config.addFormBeanConfig(arg0);
    }

    public void addForwardConfig(ForwardConfig arg0) {
        config.addForwardConfig(arg0);
    }

    public void addMessageResourcesConfig(MessageResourcesConfig arg0) {
        config.addMessageResourcesConfig(arg0);
    }

    public void addPlugInConfig(PlugInConfig arg0) {
        config.addPlugInConfig(arg0);
    }

    public ActionConfig findActionConfig(String path) {
        ActionConfig result = getActionConfigCreator().createActionConfig(this, path);
        if (result != null) {
            if (log.isDebugEnabled()) {
                log.debug("auto create " + result);
                ForwardConfig[] forwardConfigs = result.findForwardConfigs();
                for (int i = 0; i < forwardConfigs.length; i++) {
                    log.debug("auto create " + forwardConfigs[i]);
                }
            }
            return result;
        }
        return config.findActionConfig(path);
    }

    public ActionConfig[] findActionConfigs() {
        return config.findActionConfigs();
    }

    public DataSourceConfig findDataSourceConfig(String arg0) {
        return config.findDataSourceConfig(arg0);
    }

    public DataSourceConfig[] findDataSourceConfigs() {
        return config.findDataSourceConfigs();
    }

    public ExceptionConfig findExceptionConfig(String arg0) {
        return config.findExceptionConfig(arg0);
    }

    public ExceptionConfig[] findExceptionConfigs() {
        return config.findExceptionConfigs();
    }

    public FormBeanConfig findFormBeanConfig(String name) {
        FormBeanConfig result = getActionFormConfigCreator().createFormBeanConfig(this, name);
        if (result != null) {
            if (log.isDebugEnabled()) {
                log.debug("auto create " + result);
            }
            return result;
        }
        return config.findFormBeanConfig(name);
    }

    public FormBeanConfig[] findFormBeanConfigs() {
        return config.findFormBeanConfigs();
    }

    public ForwardConfig findForwardConfig(String arg0) {
        return config.findForwardConfig(arg0);
    }

    public ForwardConfig[] findForwardConfigs() {
        return config.findForwardConfigs();
    }

    public MessageResourcesConfig findMessageResourcesConfig(String arg0) {
        return config.findMessageResourcesConfig(arg0);
    }

    public MessageResourcesConfig[] findMessageResourcesConfigs() {
        return config.findMessageResourcesConfigs();
    }

    public PlugInConfig[] findPlugInConfigs() {
        return config.findPlugInConfigs();
    }

    public void freeze() {
        config.freeze();
    }

    public String getActionFormBeanClass() {
        return config.getActionFormBeanClass();
    }

    public String getActionForwardClass() {
        return config.getActionForwardClass();
    }

    public String getActionMappingClass() {
        return config.getActionMappingClass();
    }

    public boolean getConfigured() {
        return config.getConfigured();
    }

    public ControllerConfig getControllerConfig() {
        return config.getControllerConfig();
    }

    public String getPrefix() {
        return config.getPrefix();
    }

    public void removeActionConfig(ActionConfig arg0) {
        config.removeActionConfig(arg0);
    }

    public void removeDataSourceConfig(DataSourceConfig arg0) {
        config.removeDataSourceConfig(arg0);
    }

    public void removeExceptionConfig(ExceptionConfig arg0) {
        config.removeExceptionConfig(arg0);
    }

    public void removeFormBeanConfig(FormBeanConfig arg0) {
        config.removeFormBeanConfig(arg0);
    }

    public void removeForwardConfig(ForwardConfig arg0) {
        config.removeForwardConfig(arg0);
    }

    public void removeMessageResourcesConfig(MessageResourcesConfig arg0) {
        config.removeMessageResourcesConfig(arg0);
    }

    public void setActionFormBeanClass(String arg0) {
        config.setActionFormBeanClass(arg0);
    }

    public void setActionForwardClass(String arg0) {
        config.setActionForwardClass(arg0);
    }

    public void setActionMappingClass(String arg0) {
        config.setActionMappingClass(arg0);
    }

    public void setControllerConfig(ControllerConfig arg0) {
        config.setControllerConfig(arg0);
    }

    public void setPrefix(String arg0) {
        config.setPrefix(arg0);
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
