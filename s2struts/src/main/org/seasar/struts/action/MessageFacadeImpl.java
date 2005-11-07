package org.seasar.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author Satoshi Kimura
 */
public class MessageFacadeImpl implements MessageFacade {
    private ActionMessages actionMessages = new ActionMessages();
    private ActionErrors actionErrors = new ActionErrors();

    public MessageFacadeImpl() {
    }

    public void addError(String key, Object[] values) {
        this.actionErrors.add(key, new ActionMessage(key, convertMaxArguments(values)));
    }

    public void addError(String key) {
        addError(key, null);
    }

    public void addError(String key, Object value0) {
        addError(key, new Object[] {value0});
    }

    public void addError(String key, Object value0, Object value1) {
        addError(key, new Object[] {value0, value1});
    }

    public void addError(String key, Object value0, Object value1, Object value2) {
        addError(key, new Object[] {value0, value1, value2});
    }

    public void addError(String key, Object value0, Object value1, Object value2, Object value3) {
        addError(key, new Object[] {value0, value1, value2, value3});
    }

    public void addMessage(String key, Object[] values) {
        this.actionMessages.add(key, new ActionMessage(key, convertMaxArguments(values)));
    }

    public void addMessage(String key) {
        addMessage(key, null);
    }

    public void addMessage(String key, Object value0) {
        addMessage(key, new Object[] {value0});
    }

    public void addMessage(String key, Object value0, Object value1) {
        addMessage(key, new Object[] {value0, value1});
    }

    public void addMessage(String key, Object value0, Object value1, Object value2) {
        addMessage(key, new Object[] {value0, value1, value2});
    }

    public void addMessage(String key, Object value0, Object value1, Object value2, Object value3) {
        addMessage(key, new Object[] {value0, value1, value2, value3});
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
        Object[] ret = {"", "", "", "", "", "", "", "", "", ""};
        if (args == null) {
            return ret;
        }
        
        for (int i = 0; i < args.length; i++) {
            ret[i] = args[i];
        }
        return ret;
    }

    private static HttpServletRequest getRequest() {
        return SingletonS2ContainerFactory.getContainer().getRequest();
    }

    private static HttpSession getSession() {
        return SingletonS2ContainerFactory.getContainer().getSession();
    }

}
