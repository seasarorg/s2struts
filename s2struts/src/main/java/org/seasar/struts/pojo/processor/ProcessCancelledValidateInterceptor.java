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

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.log.Logger;
import org.seasar.struts.pojo.util.IndexedUtil;
import org.seasar.struts.processor.ExternalRequestProcessor;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * {@link ExternalRequestProcessor#processValidate(HttpServletRequest, HttpServletResponse, ActionForm, ActionMapping)}に対するインターセプタです。
 * <p>
 * 検証をスキップするかどうかの判定を行います。
 * </p>
 * 
 * @author Katsuhiko Nagashima
 */
public class ProcessCancelledValidateInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -3956882785774352837L;

    private static final Logger log = Logger.getLogger(ProcessCancelledValidateInterceptor.class);

    public Object invoke(MethodInvocation invocation) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) invocation.getArguments()[0];
        ActionMapping mapping = (ActionMapping) invocation.getArguments()[3];

        if (isCancel(request, mapping)) {
            log.debug(" Cancelled transaction, skipping validation");
            return Boolean.TRUE;
        }
        return invocation.proceed();
    }

    private boolean isCancel(HttpServletRequest request, ActionMapping mapping) {
        for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String key = (String) paramNames.nextElement();
            String value = request.getParameter(key);
            Boolean cancel = S2StrutsContextUtil.isCancelAction(mapping.getPath(), key, value);
            if (cancel != null) {
                return cancel.booleanValue();
            }

            // image tag
            String imageKey = key.replaceFirst("(\\.x$)|(\\.y$)", "");
            cancel = S2StrutsContextUtil.isCancelAction(mapping.getPath(), imageKey, null);
            if (cancel != null) {
                return cancel.booleanValue();
            }

            // indexed
            if (IndexedUtil.isIndexedParameter(key)) {
                String indexedKey = IndexedUtil.getParameter(key);
                cancel = S2StrutsContextUtil.isCancelAction(mapping.getPath(), indexedKey, value);
                if (cancel != null) {
                    return cancel.booleanValue();
                }
            }
        }
        return false;
    }

}
