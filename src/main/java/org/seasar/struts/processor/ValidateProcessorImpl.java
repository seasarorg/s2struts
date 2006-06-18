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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.InvalidCancelException;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.seasar.framework.exception.IllegalAccessRuntimeException;
import org.seasar.framework.exception.InvocationTargetRuntimeException;
import org.seasar.framework.exception.NoSuchMethodRuntimeException;
import org.seasar.framework.log.Logger;
import org.seasar.struts.context.ContentsType;
import org.seasar.struts.form.InputValueForm;
import org.seasar.struts.util.IndexedUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class ValidateProcessorImpl implements ValidateProcessor {

    private static final Logger log = Logger.getLogger(ValidateProcessorImpl.class);

    public boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping, ExternalRequestProcessor requestProcessor) throws IOException, ServletException, InvalidCancelException {
        
        if (isCancel(request)) {
            // Clear
            log.debug(" Cancelled transaction, skipping validation");
            S2StrutsContextUtil.clear(ContentsType.CancelAction);
            return true;
        }

        String input = mapping.getInput();
        if (input == null && S2StrutsContextUtil.getPreviousInputPath() != null) {
            ActionMapping newMapping = new ActionMapping();

            try {
                PropertyUtils.copyProperties(newMapping, mapping);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessRuntimeException(newMapping.getClass(), e);
            } catch (InvocationTargetException e) {
                throw new InvocationTargetRuntimeException(newMapping.getClass(), e);
            } catch (NoSuchMethodException e) {
                throw new NoSuchMethodRuntimeException(newMapping.getClass(), null, null, e);
            }

            input = S2StrutsContextUtil.getPreviousInputPath();
            newMapping.setInput(input);
            mapping = newMapping;
        }
        
        boolean valid = requestProcessor.processValidate(request, response, form, mapping);
        if (valid && form instanceof InputValueForm) {
            ModuleConfig moduleConfig = requestProcessor.getModuleConfig();
            ActionServlet servlet = requestProcessor.getActionServlet();
            ActionForm instance = RequestUtils.createActionForm(request, mapping, moduleConfig, servlet);
            requestProcessor.processPopulate(request, response, instance, mapping);

            valid = requestProcessor.processValidate(request, response, instance, mapping);
        }
        
        return valid;
    }

    private boolean isCancel(HttpServletRequest request) {
        for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String key = (String) paramNames.nextElement();
            String value = request.getParameter(key);
            Boolean cancel = S2StrutsContextUtil.isCancelAction(key, value);
            if (cancel != null) {
                return cancel.booleanValue();
            }

            // image tag
            String imageKey = key.replaceFirst("(\\.x$)|(\\.y$)", "");
            cancel = S2StrutsContextUtil.isCancelAction(imageKey, null);
            if (cancel != null) {
                return cancel.booleanValue();
            }

            // indexed
            if (IndexedUtil.isIndexedParameter(key)) {
                String indexedKey = IndexedUtil.getParameter(key);
                cancel = S2StrutsContextUtil.isCancelAction(indexedKey, value);
                if (cancel != null) {
                    return cancel.booleanValue();
                }
            }
        }
        return false;
    }

}
