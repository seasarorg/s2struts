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
package org.seasar.struts.pojo.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.pojo.MessageFacade;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * {@link MessageFacade}の実装クラスです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class MessageFacadeImpl implements MessageFacade {
    private ActionMessages actionMessages = new ActionMessages();

    private ActionErrors actionErrors = new ActionErrors();

    /**
     * インスタンスを構築します。
     */
    public MessageFacadeImpl() {
    }

    public void addError(String property, String key, Object[] values) {
        this.actionErrors.add(property, new ActionMessage(key, convertMaxArguments(values)));
    }

    public void addError(String property, String key) {
        addError(property, key, new Object[] {});
    }

    public void addError(String property, String key, Object value0) {
        addError(property, key, new Object[] { value0 });
    }

    public void addError(String property, String key, Object value0, Object value1) {
        addError(property, key, new Object[] { value0, value1 });
    }

    public void addError(String property, String key, Object value0, Object value1, Object value2) {
        addError(property, key, new Object[] { value0, value1, value2 });
    }

    public void addError(String property, String key, Object value0, Object value1, Object value2, Object value3) {
        addError(property, key, new Object[] { value0, value1, value2, value3 });
    }

    public void addGlobalError(String key, Object[] values) {
        this.actionErrors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(key, convertMaxArguments(values)));
    }

    public void addGlobalError(String key) {
        addGlobalError(key, new Object[] {});
    }

    public void addGlobalError(String key, Object value0) {
        addGlobalError(key, new Object[] { value0 });
    }

    public void addGlobalError(String key, Object value0, Object value1) {
        addGlobalError(key, new Object[] { value0, value1 });
    }

    public void addGlobalError(String key, Object value0, Object value1, Object value2) {
        addGlobalError(key, new Object[] { value0, value1, value2 });
    }

    public void addGlobalError(String key, Object value0, Object value1, Object value2, Object value3) {
        addGlobalError(key, new Object[] { value0, value1, value2, value3 });
    }

    public void addMessage(String property, String key, Object[] values) {
        this.actionMessages.add(property, new ActionMessage(key, convertMaxArguments(values)));
    }

    public void addMessage(String property, String key) {
        addMessage(property, key, new Object[] {});
    }

    public void addMessage(String property, String key, Object value0) {
        addMessage(property, key, new Object[] { value0 });
    }

    public void addMessage(String property, String key, Object value0, Object value1) {
        addMessage(property, key, new Object[] { value0, value1 });
    }

    public void addMessage(String property, String key, Object value0, Object value1, Object value2) {
        addMessage(property, key, new Object[] { value0, value1, value2 });
    }

    public void addMessage(String property, String key, Object value0, Object value1, Object value2, Object value3) {
        addMessage(property, key, new Object[] { value0, value1, value2, value3 });
    }

    public void addGlobalMessage(String key, Object[] values) {
        this.actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, convertMaxArguments(values)));
    }

    public void addGlobalMessage(String key) {
        addGlobalMessage(key, new Object[] {});
    }

    public void addGlobalMessage(String key, Object value0) {
        addGlobalMessage(key, new Object[] { value0 });
    }

    public void addGlobalMessage(String key, Object value0, Object value1) {
        addGlobalMessage(key, new Object[] { value0, value1 });
    }

    public void addGlobalMessage(String key, Object value0, Object value1, Object value2) {
        addGlobalMessage(key, new Object[] { value0, value1, value2 });
    }

    public void addGlobalMessage(String key, Object value0, Object value1, Object value2, Object value3) {
        addGlobalMessage(key, new Object[] { value0, value1, value2, value3 });
    }

    public void addErrors(ActionMessages errors) {
        if (errors == null) {
            return;
        }

        this.actionErrors.add(errors);
    }

    public void addMessages(ActionMessages messages) {
        if (messages == null) {
            return;
        }

        this.actionMessages.add(messages);
    }

    public ActionMessages getErrors() {
        ActionMessages errors = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);
        if (errors == null) {
            errors = new ActionMessages();
        }
        return errors;
    }

    public ActionMessages getErrorsFromSession() {
        ActionMessages errors = (ActionMessages) getSession().getAttribute(Globals.ERROR_KEY);
        if (errors == null) {
            errors = new ActionMessages();
        }
        return errors;
    }

    public ActionMessages getMessages() {
        ActionMessages messages = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);
        if (messages == null) {
            messages = new ActionMessages();
        }
        return messages;
    }

    public ActionMessages getMessagesFromSession() {
        ActionMessages messages = (ActionMessages) getSession().getAttribute(Globals.MESSAGE_KEY);
        if (messages == null) {
            messages = new ActionMessages();
        }
        return messages;
    }

    public void saveErrors() {
        saveErrors(this.actionErrors);
    }

    public void saveErrors(ActionMessages errors) {
        HttpServletRequest request = getRequest();

        if ((errors == null) || errors.isEmpty()) {
            request.removeAttribute(Globals.ERROR_KEY);
            return;
        }

        request.setAttribute(Globals.ERROR_KEY, errors);

    }

    public void saveErrorsInSession() {
        saveErrorsInSession(this.actionErrors);
    }

    public void saveErrorsInSession(ActionMessages errors) {
        HttpSession session = getSession();

        if ((errors == null) || errors.isEmpty()) {
            session.removeAttribute(Globals.ERROR_KEY);
            return;
        }

        session.setAttribute(Globals.ERROR_KEY, errors);
    }

    public void saveMessages() {
        saveMessages(this.actionMessages);
    }

    public void saveMessages(ActionMessages messages) {
        HttpServletRequest request = getRequest();

        if ((messages == null) || messages.isEmpty()) {
            request.removeAttribute(Globals.MESSAGE_KEY);
            return;
        }

        request.setAttribute(Globals.MESSAGE_KEY, messages);
    }

    public void saveMessagesInSession() {
        saveMessagesInSession(this.actionMessages);
    }

    public void saveMessagesInSession(ActionMessages messages) {
        HttpSession session = getSession();

        if ((messages == null) || messages.isEmpty()) {
            session.removeAttribute(Globals.MESSAGE_KEY);
            return;
        }

        session.setAttribute(Globals.MESSAGE_KEY, messages);
    }

    private Object[] convertMaxArguments(Object[] args) {
        if (args != null && args.length >= 10) {
            return args;
        }
        Object[] ret = { "", "", "", "", "", "", "", "", "", "" };
        if (args == null) {
            return ret;
        }

        for (int i = 0; i < args.length; i++) {
            ret[i] = args[i];
        }
        return ret;
    }

    private static HttpServletRequest getRequest() {
        return S2StrutsContextUtil.getRequest();
    }

    private static HttpSession getSession() {
        return S2StrutsContextUtil.getSession();
    }

}
