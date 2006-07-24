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

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.seasar.struts.Constants;
import org.seasar.struts.servlet.http.S2ServletRequestWrapper;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class PopulateProcessorImpl implements PopulateProcessor {

    public void processPopulate(HttpServletRequest request, HttpServletResponse response,
            ActionForm form, ActionMapping mapping, ExternalRequestProcessor requestProcessor)
            throws ServletException {

        if (request instanceof MultipartRequestWrapper) {
            requestProcessor.processPopulate(request, response, form, mapping);
            Map parameters = getCheckBoxParameters(request);
            if (!parameters.isEmpty()) {
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
                request = s2request;
            }
            requestProcessor.processPopulate(request, response, form, mapping);
        }
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
