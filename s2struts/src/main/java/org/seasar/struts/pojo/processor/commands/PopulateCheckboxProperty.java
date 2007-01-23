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
package org.seasar.struts.pojo.processor.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.commands.ActionCommandBase;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;
import org.seasar.struts.Constants;

/**
 * Checkboxタグを利用してできたRequest情報を元にCheckboxを選択していない場合、FormBeanのプロパティに対してfalseを設定する。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class PopulateCheckboxProperty extends ActionCommandBase {

    public boolean execute(ActionContext actionContext) throws Exception {
        ActionForm actionForm = actionContext.getActionForm();
        if (actionForm == null) {
            return (false);
        }

        populate(actionContext, actionForm);

        return false;
    }

    private void populate(ActionContext actionContext, ActionForm actionForm) throws ServletException {
        Map parameters = getCheckBoxParameters(actionContext.getActionConfig(), actionContext.getParameterMap());
        if (!parameters.isEmpty()) {
            try {
                BeanUtils.populate(actionForm, parameters);
            } catch (IllegalAccessException e) {
                throw new ServletException("BeanUtils.populate", e);
            } catch (InvocationTargetException e) {
                throw new ServletException("BeanUtils.populate", e);
            }
        }
    }

    private Map getCheckBoxParameters(ActionConfig actionConfig, Map paramValues) {
        Map result = new HashMap();
        for (Iterator i = paramValues.keySet().iterator(); i.hasNext();) {
            String paramName = (String) i.next();
            if (!paramName.startsWith(Constants.CHECKBOX_NAME)) {
                continue;
            }

            String checkboxParamName = paramName.substring(Constants.CHECKBOX_NAME.length());
            String trimName = trimParameterName(actionConfig, checkboxParamName);
            if (trimName == null) {
                continue;
            }

            Object checkboxValue = paramValues.get(checkboxParamName);
            if (checkboxValue == null) {
                result.put(trimName, Boolean.FALSE.toString());
            }
        }
        return result;
    }

    private String trimParameterName(ActionConfig actionConfig, String name) {
        String stripped = name;
        String prefix = actionConfig.getPrefix();
        String suffix = actionConfig.getSuffix();

        if (prefix != null) {
            if (!stripped.startsWith(prefix)) {
                return null;
            }

            stripped = stripped.substring(prefix.length());
        }

        if (suffix != null) {
            if (!stripped.endsWith(suffix)) {
                return null;
            }

            stripped = stripped.substring(0, stripped.length() - suffix.length());
        }

        return stripped;
    }
}
