package org.seasar.struts.action;

import org.apache.struts.action.ActionMessages;

/**
 * @author Satoshi Kimura
 */
public interface MessageFacade {
    void addError(String key, Object[] values);

    void addError(String key);
    
    void addError(String key, Object value0);

    void addError(String key, Object value0, Object value1);

    void addError(String key, Object value0, Object value1, Object value2);

    void addError(String key, Object value0, Object value1, Object value2, Object value3);

    void addMessage(String key, Object[] values);

    void addMessage(String key);
    
    void addMessage(String key, Object value0);

    void addMessage(String key, Object value0, Object value1);

    void addMessage(String key, Object value0, Object value1, Object value2);

    void addMessage(String key, Object value0, Object value1, Object value2, Object value3);

    void addErrors(ActionMessages errors);

    void addMessages(ActionMessages messages);

    ActionMessages getErrors();

    ActionMessages getErrorsFromSession();

    ActionMessages getMessages();

    ActionMessages getMessagesFromSession();

    void saveErrors();

    void saveErrors(ActionMessages errors);

    void saveErrorsInSession();

    void saveErrorsInSession(ActionMessages errors);

    void saveMessages();

    void saveMessages(ActionMessages messages);

    void saveMessagesInSession();

    void saveMessagesInSession(ActionMessages messages);
}
