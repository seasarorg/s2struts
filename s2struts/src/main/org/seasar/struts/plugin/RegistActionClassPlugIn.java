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
package org.seasar.struts.plugin;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.seasar.struts.S2StrutsInitializer;

/**
 * @author Satoshi Kimura
 */
public class RegistActionClassPlugIn implements PlugIn {

    public RegistActionClassPlugIn() {
    }

    /**
     * empty.
     * 
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    public void destroy() {
    }

    /**
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
     *      org.apache.struts.config.ModuleConfig)
     * @see S2StrutsInitializer#registActionClass(ActionServlet, ModuleConfig)
     */
    public void init(ActionServlet servlet, ModuleConfig config) {
        S2StrutsInitializer.registActionClass(servlet, config);
    }

}