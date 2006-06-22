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
package org.seasar.struts.context.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.Base64Util;
import org.seasar.struts.Constants;
import org.seasar.struts.context.ContentsType;
import org.seasar.struts.context.S2StrutsContext;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class S2StrutsContextImpl implements S2StrutsContext {
    private static final long serialVersionUID = -4835702530138078142L;

    private String currentPath;

    private String previousPath;

    private Map methodBindingExpressions = new HashMap();

    private Map cancelActions = new HashMap();

    public void clear(ContentsType type) {
        if (type == ContentsType.MethodBindingExpression) {
            this.methodBindingExpressions = new HashMap();
        } else if (type == ContentsType.CancelAction) {
            this.cancelActions = new HashMap();
        }
    }

    public String getCurrentInputPath() {
        String param = getRequest().getParameter(Constants.PAGE_NAME_ELEMENT_VALUE);
        if (param != null) {
            return new String(Base64Util.decode(param));
        } else {
            return this.currentPath;
        }
    }

    public String getPreviousInputPath() {
        String param = getRequest().getParameter(Constants.PAGE_NAME_ELEMENT_VALUE);
        if (param != null) {
            return new String(Base64Util.decode(param));
        } else {
            return this.previousPath;
        }
    }

    public void setPath(String path) {
        if (!path.equals(this.currentPath)) {
            this.previousPath = this.currentPath;
            this.currentPath = path;
        }
    }

    public String getMethodBindingExpression(String mappingName, String key, String value) {
        return (String) this.methodBindingExpressions.get(mappingName + key + value);
    }

    public void setMethodBindingExpression(String mappingName, String key, String value, String methodBindingExpression) {
        this.methodBindingExpressions.put(mappingName + key + value, methodBindingExpression);
    }

    public Boolean isCancelAction(String mappingName, String key, String value) {
        return (Boolean) this.cancelActions.get(mappingName + key + value);
    }

    public void setCancelAction(String mappingName, String key, String value) {
        this.cancelActions.put(mappingName + key + value, Boolean.TRUE);
    }

    private static final HttpServletRequest getRequest() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return S2StrutsContextUtil.getRequest(container);
    }
}
