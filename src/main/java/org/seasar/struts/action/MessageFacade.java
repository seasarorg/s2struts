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
package org.seasar.struts.action;

import org.apache.struts.action.ActionMessages;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public interface MessageFacade {

    void addError(String property, String key, Object[] values);
    
    void addError(String property, String key);
    
    void addError(String property, String key, Object value0);
    
    void addError(String property, String key, Object value0, Object value1);
    
    void addError(String property, String key, Object value0, Object value1, Object value2);

    void addError(String property, String key, Object value0, Object value1, Object value2, Object value3);

    void addGlobalError(String key, Object[] values);

    void addGlobalError(String key);
    
    void addGlobalError(String key, Object value0);

    void addGlobalError(String key, Object value0, Object value1);

    void addGlobalError(String key, Object value0, Object value1, Object value2);

    void addGlobalError(String key, Object value0, Object value1, Object value2, Object value3);

    void addMessage(String property, String key, Object[] values);

    void addMessage(String property, String key);
    
    void addMessage(String property, String key, Object value0);

    void addMessage(String property, String key, Object value0, Object value1);

    void addMessage(String property, String key, Object value0, Object value1, Object value2);

    void addMessage(String property, String key, Object value0, Object value1, Object value2, Object value3);

    void addGlobalMessage(String key, Object[] values);

    void addGlobalMessage(String key);
    
    void addGlobalMessage(String key, Object value0);

    void addGlobalMessage(String key, Object value0, Object value1);

    void addGlobalMessage(String key, Object value0, Object value1, Object value2);

    void addGlobalMessage(String key, Object value0, Object value1, Object value2, Object value3);

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
