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
package org.seasar.struts.lessconfig.plugin;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.lessconfig.autoregister.StrutsConfigRegister;
import org.seasar.struts.lessconfig.config.AutoStrutsConfigRule;
import org.seasar.struts.lessconfig.util.ClassFinder;
import org.seasar.struts.lessconfig.util.ClassFinderImpl;

/**
 * Example.
 * <p>
 * 
 * <plug-in className="org.seasar.struts.lessconfig.plugin.AutoStrutsConfigRegisterPlugIn">
 *   <set-property property="enableJar" value="false"/>
 *   <set-property property="actionClassPattern" value="foo.bar.action.*Action"/>
 *   <set-property property="formClassPattern" value="foo.bar.form.*Form"/>
 *   <set-property property="docRoot" value="/WEB-INF/jsp"/>
 *   <set-property property="viewExtension" value="jsp,html,view"/>
 * </plug-in>
 * 
 * @author Satoshi Kimura
 */
public class AutoStrutsConfigRegisterPlugIn implements PlugIn {

    private ClassFinder classFinder = new ClassFinderImpl();

    private boolean enableJar;

    private String jarFilePattern = "";

    public AutoStrutsConfigRegisterPlugIn() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    public void destroy() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
     *      org.apache.struts.config.ModuleConfig)
     */
    public void init(ActionServlet actionServlet, ModuleConfig config) throws ServletException {
        try {
            this.classFinder.find(isEnableJar(), getJarFilePattern());

            if (actionServlet != null) {
                this.classFinder.find(actionServlet, isEnableJar(), getJarFilePattern());
            }

            getConfigRegister().register(config, this.classFinder.getClassCollection());
        } finally {
            this.classFinder.destroy();
        }
    }

    // -----------------------------------------------------------------------
    // set-property

    public boolean isEnableJar() {
        return this.enableJar;
    }

    public void setEnableJar(boolean enableJar) {
        this.enableJar = enableJar;
    }

    public String getJarFilePattern() {
        return this.jarFilePattern;
    }

    public void setJarFilePattern(String jarFilePattern) {
        this.jarFilePattern = jarFilePattern;
    }

    public void setActionClassPattern(String actionClassPattern) {
        configRule().setActionClassPattern(actionClassPattern);
    }

    public void setFormClassPattern(String formClassPattern) {
        configRule().setFormClassPattern(formClassPattern);
    }

    public void setDocRoot(String docRoot) {
        configRule().setDocRoot(docRoot);
    }

    public void setViewExtension(String viewExtension) {
        configRule().setViewExtension(viewExtension);
    }

    // -----------------------------------------------------------------------

    private static StrutsConfigRegister getConfigRegister() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (StrutsConfigRegister) container.getComponent(StrutsConfigRegister.class);
    }

    private static AutoStrutsConfigRule configRule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (AutoStrutsConfigRule) container.getComponent(AutoStrutsConfigRule.class);
    }

}