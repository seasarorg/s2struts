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
package org.seasar.struts.processor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.seasar.framework.exception.IllegalAccessRuntimeException;
import org.seasar.framework.exception.InvocationTargetRuntimeException;
import org.seasar.framework.exception.NoSuchMethodRuntimeException;
import org.seasar.struts.form.InputValueForm;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 */
public class ValidateProcessorImpl implements ValidateProcessor {

    public boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping, ExternalRequestProcessor requestProcessor) throws IOException, ServletException {

        String input = mapping.getInput();
        if (input == null && S2StrutsContextUtil.getPath() != null) {
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

            input = S2StrutsContextUtil.getPath();
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

}
