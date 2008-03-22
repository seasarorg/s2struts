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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.exception.IllegalAccessRuntimeException;
import org.seasar.framework.exception.InvocationTargetRuntimeException;
import org.seasar.framework.exception.NoSuchMethodRuntimeException;
import org.seasar.struts.processor.ExternalRequestProcessor;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * {@link ExternalRequestProcessor#processValidate(HttpServletRequest, HttpServletResponse, ActionForm, ActionMapping)}に対するインターセプタです。
 * <p>
 * 検証に失敗した場合の遷移先パスを自動で設定します。
 * </p>
 * 
 * @author Katsuhiko Nagashima
 */
public class ProcessSetInputPathValidateInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -3183639341132304339L;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        ActionMapping mapping = (ActionMapping) invocation.getArguments()[3];

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
            invocation.getArguments()[3] = newMapping;
        }
        return invocation.proceed();
    }

}
