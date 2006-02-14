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
package org.seasar.struts.config.rule;

import javax.servlet.ServletContext;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;

/**
 * @author Satoshi Kimura
 */
public interface ZeroConfigActionRule {
    String getPath(Class actionClass, ModuleConfig config);

    String getName(Class actionClass, ModuleConfig config);

    String getScope(Class actionClass, ModuleConfig config);

    boolean getValidate(Class actionClass, ModuleConfig config);

    String getInput(Class actionClass, ModuleConfig config);

    String getParameter(Class actionClass, ModuleConfig config);

    String getAttribute(Class actionClass, ModuleConfig config);

    String getForward(Class actionClass, ModuleConfig config);

    String getInclude(Class actionClass, ModuleConfig config);

    String getPrefix(Class actionClass, ModuleConfig config);

    String getSuffix(Class actionClass, ModuleConfig config);

    boolean getUnknown(Class actionClass, ModuleConfig config);

    String getRoles(Class actionClass, ModuleConfig config);

    void addFowardConfig(Class actionClass, ActionConfig actionConfig, ServletContext servletContext);

}
