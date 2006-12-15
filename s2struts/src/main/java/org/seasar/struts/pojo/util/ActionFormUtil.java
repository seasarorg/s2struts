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
package org.seasar.struts.pojo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.struts.Constants;

/**
 * @author Katsuhiko Nagashima
 */
public class ActionFormUtil {

    private ActionFormUtil() {
    }

    public static Object getActualForm(HttpServletRequest request, ActionMapping mapping) {
        Object form = getActionForm(request, mapping);
        return BeanValidatorFormUtil.toBean(form);
    }

    public static void setActualForm(HttpServletRequest request, Object form, ActionMapping mapping) {
        ActionForm newForm = toActionForm(request, form, mapping);
        setActionForm(request, newForm, mapping, mapping.getScope());
    }

    public static void setRequestActualForm(HttpServletRequest request, Object form, ActionMapping mapping) {
        ActionForm newForm = toActionForm(request, form, mapping);
        setActionForm(request, newForm, mapping, Constants.REQUEST);
    }

    public static void setSessionActualForm(HttpServletRequest request, Object form, ActionMapping mapping) {
        ActionForm newForm = toActionForm(request, form, mapping);
        setActionForm(request, newForm, mapping, Constants.SESSION);
    }

    private static ActionForm toActionForm(HttpServletRequest request, Object form, ActionMapping mapping) {
        if (form instanceof ActionForm) {
            return (ActionForm) form;
        }
        BeanValidatorForm oldForm = (BeanValidatorForm) getActionForm(request, mapping);
        return BeanValidatorFormUtil.toBeanValidatorForm(oldForm, form);
    }

    private static ActionForm getActionForm(HttpServletRequest request, ActionMapping mapping) {
        if (Constants.REQUEST.equals(mapping.getScope())) {
            return (ActionForm) request.getAttribute(mapping.getAttribute());
        } else {
            HttpSession session = request.getSession();
            return (ActionForm) session.getAttribute(mapping.getAttribute());
        }
    }

    private static void setActionForm(HttpServletRequest request, ActionForm form, ActionMapping mapping, String scope) {
        if (form == null) {
            return;
        }

        if (Constants.REQUEST.equals(scope)) {
            request.setAttribute(mapping.getAttribute(), form);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(mapping.getAttribute(), form);
        }
    }

}
