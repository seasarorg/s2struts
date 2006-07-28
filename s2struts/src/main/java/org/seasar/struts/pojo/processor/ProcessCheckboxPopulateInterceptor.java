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
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.Constants;
import org.seasar.struts.servlet.http.S2ServletRequestWrapper;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ProcessCheckboxPopulateInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -118149589979795316L;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) invocation.getArguments()[0];

        if (request instanceof MultipartRequestWrapper) {
            invocation.proceed();
            Map parameters = getCheckBoxParameters(request);
            if (!parameters.isEmpty()) {
                ActionForm form = (ActionForm) invocation.getArguments()[2];
                try {
                    BeanUtils.populate(form, parameters);
                } catch (IllegalAccessException e) {
                    throw new ServletException("BeanUtils.populate", e);
                } catch (InvocationTargetException e) {
                    throw new ServletException("BeanUtils.populate", e);
                }
            }
        } else {
            Map parameters = getCheckBoxParameters(request);
            if (!parameters.isEmpty()) {
                S2ServletRequestWrapper s2request = new S2ServletRequestWrapper(request);
                addParameter(s2request, parameters);
                invocation.getArguments()[0] = s2request;
            }
            invocation.proceed();
        }

        return null;
    }

    private Map getCheckBoxParameters(HttpServletRequest request) {
        Map result = new HashMap();
        for (Iterator i = request.getParameterMap().keySet().iterator(); i.hasNext();) {
            String paramName = (String) i.next();
            if (paramName.startsWith(Constants.CHECKBOX_NAME)) {
                String checkboxParamName = paramName.substring(Constants.CHECKBOX_NAME.length());
                String checkboxValue = request.getParameter(checkboxParamName);
                if (checkboxValue == null) {
                    result.put(checkboxParamName, Boolean.FALSE.toString());
                }
            }
        }
        return result;
    }

    private void addParameter(S2ServletRequestWrapper request, Map parameters) {
        for (Iterator i = parameters.entrySet().iterator(); i.hasNext();) {
            Map.Entry parameter = (Map.Entry) i.next();
            request.addParameterValue((String) parameter.getKey(), (String) parameter.getValue());
        }
    }

}
