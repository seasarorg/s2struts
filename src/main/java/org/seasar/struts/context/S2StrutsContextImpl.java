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
package org.seasar.struts.context;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.Base64Util;
import org.seasar.struts.Constants;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class S2StrutsContextImpl implements S2StrutsContext {
    
    private String currentPath;
    
    private String previousPath;

    private Map methodBindingExpressions = new HashMap();

    public void clear(ContentsType type) {
        if (type == ContentsType.MethodBindingExpression) {
            this.methodBindingExpressions = new HashMap();
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

    public void setMethodBindingExpression(String key, String value, String methodBindingExpression) {
        this.methodBindingExpressions.put(key + value, methodBindingExpression);
    }

    public String getMethodBindingExpression() {
        HttpServletRequest request = getRequest();
        for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String key = (String)paramNames.nextElement();
            String value = request.getParameter(key);
            Object ret = this.methodBindingExpressions.get(key + value);
            if (ret == null) {
                key = key.replaceFirst("(\\.x$)|(\\.y$)", "");
                value = request.getParameter(key);
                ret = this.methodBindingExpressions.get(key + value);
            }
            if (ret != null) {
                return (String)ret;
            }
        }
        return null;
    }
    
    public boolean existMethodBindingExpression() {
        return getMethodBindingExpression() != null;
    }

    private static final HttpServletRequest getRequest() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return container.getRequest();
    }
}
