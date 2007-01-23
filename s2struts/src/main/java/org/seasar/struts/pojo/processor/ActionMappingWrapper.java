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
package org.seasar.struts.pojo.processor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;

/**
 * 一部の属性をRequest単位で変更するためにActionMappingをラップしたクラス
 * 
 * validaetのcancelやinputの無設定化（動的設定）を行うために利用する
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ActionMappingWrapper extends ActionMapping {

    private static final long serialVersionUID = 4616353711447102188L;

    private ActionMapping mapping;

    public ActionMappingWrapper(ActionMapping mapping) {
        this.mapping = mapping;
    }

    //
    //
    //

    private Map overwritePrameters = new HashMap();

    private static final String ATTRIBUTE_KEY = "attribute";

    public String getAttribute() {
        if (this.overwritePrameters.containsKey(ATTRIBUTE_KEY)) {
            return (String) this.overwritePrameters.get(ATTRIBUTE_KEY);
        }
        return mapping.getAttribute();
    }

    public void setAttribute(String attribute) {
        this.overwritePrameters.put(ATTRIBUTE_KEY, attribute);
    }

    private static final String CATALOG_KEY = "catalog";

    public String getCatalog() {
        if (this.overwritePrameters.containsKey(CATALOG_KEY)) {
            return (String) this.overwritePrameters.get(CATALOG_KEY);
        }
        return mapping.getCatalog();
    }

    public void setCatalog(String catalog) {
        this.overwritePrameters.put(CATALOG_KEY, catalog);
    }

    private static final String COMMAND_KEY = "command";

    public String getCommand() {
        if (this.overwritePrameters.containsKey(COMMAND_KEY)) {
            return (String) this.overwritePrameters.get(COMMAND_KEY);
        }
        return mapping.getCommand();
    }

    public void setCommand(String command) {
        this.overwritePrameters.put(COMMAND_KEY, command);
    }

    private static final String EXTENDS_KEY = "extends";

    public String getExtends() {
        if (this.overwritePrameters.containsKey(EXTENDS_KEY)) {
            return (String) this.overwritePrameters.get(EXTENDS_KEY);
        }
        return mapping.getExtends();
    }

    public void setExtends(String inherit) {
        this.overwritePrameters.put(EXTENDS_KEY, inherit);
    }

    private static final String FORWARD_KEY = "forward";

    public String getForward() {
        if (this.overwritePrameters.containsKey(FORWARD_KEY)) {
            return (String) this.overwritePrameters.get(FORWARD_KEY);
        }
        return mapping.getForward();
    }

    public void setForward(String forward) {
        this.overwritePrameters.put(FORWARD_KEY, forward);
    }

    private static final String INCLUDE_KEY = "include";

    public String getInclude() {
        if (this.overwritePrameters.containsKey(INCLUDE_KEY)) {
            return (String) this.overwritePrameters.get(INCLUDE_KEY);
        }
        return mapping.getInclude();
    }

    public void setInclude(String include) {
        this.overwritePrameters.put(INCLUDE_KEY, include);
    }

    private static final String INPUT_KEY = "input";

    public String getInput() {
        if (this.overwritePrameters.containsKey(INPUT_KEY)) {
            return (String) this.overwritePrameters.get(INPUT_KEY);
        }
        return mapping.getInput();
    }

    public void setInput(String input) {
        this.overwritePrameters.put(INPUT_KEY, input);
    }

    private static final String NAME_KEY = "name";

    public String getName() {
        if (this.overwritePrameters.containsKey(NAME_KEY)) {
            return (String) this.overwritePrameters.get(NAME_KEY);
        }
        return mapping.getName();
    }

    public void setName(String name) {
        this.overwritePrameters.put(NAME_KEY, name);
    }

    private static final String PARAMETER_KEY = "parameter";

    public String getParameter() {
        if (this.overwritePrameters.containsKey(PARAMETER_KEY)) {
            return (String) this.overwritePrameters.get(PARAMETER_KEY);
        }
        return mapping.getParameter();
    }

    public void setParameter(String parameter) {
        this.overwritePrameters.put(PARAMETER_KEY, parameter);
    }

    private static final String PATH_KEY = "path";

    public String getPath() {
        if (this.overwritePrameters.containsKey(PATH_KEY)) {
            return (String) this.overwritePrameters.get(PATH_KEY);
        }
        return mapping.getPath();
    }

    public void setPath(String path) {
        this.overwritePrameters.put(PATH_KEY, path);
    }

    private static final String PREFIX_KEY = "prefix";

    public String getPrefix() {
        if (this.overwritePrameters.containsKey(PREFIX_KEY)) {
            return (String) this.overwritePrameters.get(PREFIX_KEY);
        }
        return mapping.getPrefix();
    }

    public void setPrefix(String prefix) {
        this.overwritePrameters.put(PREFIX_KEY, prefix);
    }

    private static final String ROLES_KEY = "roles";

    public String getRoles() {
        if (this.overwritePrameters.containsKey(ROLES_KEY)) {
            return (String) this.overwritePrameters.get(ROLES_KEY);
        }
        return mapping.getRoles();
    }

    public void setRoles(String roles) {
        this.overwritePrameters.put(ROLES_KEY, roles);
    }

    private static final String SCOPE_KEY = "scope";

    public String getScope() {
        if (this.overwritePrameters.containsKey(SCOPE_KEY)) {
            return (String) this.overwritePrameters.get(SCOPE_KEY);
        }
        return mapping.getScope();
    }

    public void setScope(String scope) {
        this.overwritePrameters.put(SCOPE_KEY, scope);
    }

    private static final String SUFFIX_KEY = "suffix";

    public String getSuffix() {
        if (this.overwritePrameters.containsKey(SUFFIX_KEY)) {
            return (String) this.overwritePrameters.get(SUFFIX_KEY);
        }
        return mapping.getSuffix();
    }

    public void setSuffix(String suffix) {
        this.overwritePrameters.put(SUFFIX_KEY, suffix);
    }

    private static final String TYPE_KEY = "type";

    public String getType() {
        if (this.overwritePrameters.containsKey(TYPE_KEY)) {
            return (String) this.overwritePrameters.get(TYPE_KEY);
        }
        return mapping.getType();
    }

    public void setType(String type) {
        this.overwritePrameters.put(TYPE_KEY, type);
    }

    private static final String UNKNOWN_KEY = "unknown";

    public boolean getUnknown() {
        if (this.overwritePrameters.containsKey(UNKNOWN_KEY)) {
            return ((Boolean) this.overwritePrameters.get(UNKNOWN_KEY)).booleanValue();
        }
        return mapping.getUnknown();
    }

    public void setUnknown(boolean unknown) {
        this.overwritePrameters.put(UNKNOWN_KEY, Boolean.valueOf(unknown));
    }

    private static final String VALIDATE_KEY = "validate";

    public boolean getValidate() {
        if (this.overwritePrameters.containsKey(VALIDATE_KEY)) {
            return ((Boolean) this.overwritePrameters.get(VALIDATE_KEY)).booleanValue();
        }
        return mapping.getValidate();
    }

    public void setValidate(boolean validate) {
        this.overwritePrameters.put(VALIDATE_KEY, Boolean.valueOf(validate));
    }

    private static final String CANCELLABLE_KEY = "cancellable";

    public boolean getCancellable() {
        if (this.overwritePrameters.containsKey(CANCELLABLE_KEY)) {
            return ((Boolean) this.overwritePrameters.get(CANCELLABLE_KEY)).booleanValue();
        }
        return mapping.getCancellable();
    }

    public void setCancellable(boolean cancellable) {
        this.overwritePrameters.put(CANCELLABLE_KEY, Boolean.valueOf(cancellable));
    }

    //
    //
    //

    public String[] getRoleNames() {
        return mapping.getRoleNames();
    }

    public String getProperty(String key) {
        return mapping.getProperty(key);
    }

    public void setProperty(String key, String value) {
        mapping.setProperty(key, value);
    }

    public ActionForward getInputForward() {
        return mapping.getInputForward();
    }

    public ModuleConfig getModuleConfig() {
        return mapping.getModuleConfig();
    }

    public void setModuleConfig(ModuleConfig moduleConfig) {
        mapping.setModuleConfig(moduleConfig);
    }

    public String getMultipartClass() {
        return mapping.getMultipartClass();
    }

    public void setMultipartClass(String multipartClass) {
        mapping.setMultipartClass(multipartClass);
    }

    public void addExceptionConfig(ExceptionConfig config) {
        mapping.addExceptionConfig(config);
    }

    public void addForwardConfig(ForwardConfig config) {
        mapping.addForwardConfig(config);
    }

    public boolean equals(Object obj) {
        return mapping.equals(obj);
    }

    public ExceptionConfig findException(Class type) {
        return mapping.findException(type);
    }

    public ExceptionConfig findExceptionConfig(String type) {
        return mapping.findExceptionConfig(type);
    }

    public ExceptionConfig[] findExceptionConfigs() {
        return mapping.findExceptionConfigs();
    }

    public ActionForward findForward(String forwardName) {
        return mapping.findForward(forwardName);
    }

    public ForwardConfig findForwardConfig(String name) {
        return mapping.findForwardConfig(name);
    }

    public ForwardConfig[] findForwardConfigs() {
        return mapping.findForwardConfigs();
    }

    public String[] findForwards() {
        return mapping.findForwards();
    }

    public void inheritFrom(ActionConfig config) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        mapping.inheritFrom(config);
    }

    public boolean isExtensionProcessed() {
        return mapping.isExtensionProcessed();
    }

    public void processExtends(ModuleConfig moduleConfig) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        mapping.processExtends(moduleConfig);
    }

    public void removeExceptionConfig(ExceptionConfig config) {
        mapping.removeExceptionConfig(config);
    }

    public void removeForwardConfig(ForwardConfig config) {
        mapping.removeForwardConfig(config);
    }

    public void freeze() {
        mapping.freeze();
    }

    public int hashCode() {
        return mapping.hashCode();
    }

    public void throwIfConfigured() {
        mapping.throwIfConfigured();
    }

    public String toString() {
        return mapping.toString();
    }

}
