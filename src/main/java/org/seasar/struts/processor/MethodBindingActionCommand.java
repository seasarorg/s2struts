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
package org.seasar.struts.processor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;
import org.seasar.struts.util.IndexedUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class MethodBindingActionCommand implements ActionCommand {

    public String execute(HttpServletRequest request, HttpServletResponse response, Object action,
            Object form, ActionMapping mapping) {

        MethodBinding methodBinding = createMethodBinding(request, mapping);
        if (methodBinding == null) {
            return NOT_EXECUTE;
        }
        return (String) methodBinding.invoke(mapping);
    }

    private MethodBinding createMethodBinding(HttpServletRequest request, ActionMapping mapping) {
        for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String key = (String) paramNames.nextElement();
            String value = request.getParameter(key);
            String expression = S2StrutsContextUtil.getMethodBindingExpression(mapping.getPath(), key, value);
            if (expression != null) {
                return new MethodBinding(expression);
            }

            // image tag
            String imageKey = key.replaceFirst("(\\.x$)|(\\.y$)", "");
            expression = S2StrutsContextUtil.getMethodBindingExpression(mapping.getPath(), imageKey, null);
            if (expression != null) {
                return new MethodBinding(expression);
            }

            // indexed
            if (IndexedUtil.isIndexedParameter(key)) {
                String indexedKey = IndexedUtil.getParameter(key);
                int index = IndexedUtil.getIndex(key);
                expression = S2StrutsContextUtil.getMethodBindingExpression(mapping.getPath(), indexedKey, value);
                if (expression != null) {
                    return new MethodBinding(expression, index);
                }
            }
        }
        return null;
    }

}
