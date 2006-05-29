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
package org.seasar.struts.zeroconfig.plugin;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.zeroconfig.cooldeploy.StrutsConfigRegister;
import org.seasar.struts.zeroconfig.cooldeploy.StrutsConfigRuleImpl;
import org.seasar.struts.zeroconfig.util.ClassFinder;
import org.seasar.struts.zeroconfig.util.ClassFinderImpl;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class DeployStrutsConfigRegisterPlugIn implements PlugIn {

    private ClassFinder classFinder = new ClassFinderImpl();

    public void destroy() {
    }

    public void init(ActionServlet actionServlet, ModuleConfig config) throws ServletException {
        try {
            this.classFinder.find(getStrutsConfigRule().isEnableJar());
            if (actionServlet != null) {
                this.classFinder.find(actionServlet, getStrutsConfigRule().isEnableJar());
            }

            getConfigRegister().register(config, this.classFinder.getClassCollection());
        } finally {
            this.classFinder.destroy();
        }
    }

    private StrutsConfigRegister getConfigRegister() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (StrutsConfigRegister) container.getComponent(StrutsConfigRegister.class);
    }

    private StrutsConfigRuleImpl getStrutsConfigRule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (StrutsConfigRuleImpl) container.getComponent(StrutsConfigRuleImpl.class);
    }

    // -----------------------------------------------------------------------
    // set-property

    public void setRootPackageName(String rootPackageName) {
        getStrutsConfigRule().setRootPackageName(rootPackageName);
    }

    public void setEnableJar(boolean enableJar) {
        getStrutsConfigRule().setEnableJar(enableJar);
    }

    public void setActionMiddlePackageName(String actionMiddlePackageName) {
        getStrutsConfigRule().setActionMiddlePackageName(actionMiddlePackageName);
    }

    public void setActionSuffix(String actionSuffix) {
        getStrutsConfigRule().setActionSuffix(actionSuffix);
    }

    public void setActionFormMiddlePackageName(String actionFormMiddlePackageName) {
        getStrutsConfigRule().setActionFormMiddlePackageName(actionFormMiddlePackageName);
    }

    public void setActionFormSuffix(String actionFormSuffix) {
        getStrutsConfigRule().setActionFormSuffix(actionFormSuffix);
    }

    public void setDocRoot(String docRoot) {
        getStrutsConfigRule().setDocRoot(docRoot);
    }

    public void setViewExtension(String viewExtension) {
        String[] extensions = StringUtil.split(viewExtension, ",");
        getStrutsConfigRule().setViewExtension(extensions);
    }

}
