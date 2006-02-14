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
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class MessageManager {

    private MessageManager() {
    }
    
    public static void addError(String property, String key, Object[] values) {
        getMessageFacade().addError(property, key, values);
    }

    public static void addError(String property, String key) {
        getMessageFacade().addError(property, key);
    }

    public static void addError(String property, String key, Object value0) {
        getMessageFacade().addError(property, key, value0);
    }

    public static void addError(String property, String key, Object value0, Object value1) {
        getMessageFacade().addError(property, key, value0, value1);
    }

    public static void addError(String property, String key, Object value0, Object value1, Object value2) {
        getMessageFacade().addError(property, key, value0, value1, value2);
    }

    public static void addError(String property, String key, Object value0, Object value1, Object value2, Object value3) {
        getMessageFacade().addError(property, key, value0, value1, value2, value3);
    }

    public static void addGlobalError(String key, Object[] values) {
        getMessageFacade().addGlobalError(key, values);
    }

    public static void addGlobalError(String key) {
        getMessageFacade().addGlobalError(key);
    }
    
    public static void addGlobalError(String key, Object value0) {
        getMessageFacade().addGlobalError(key, value0);
    }

    public static void addGlobalError(String key, Object value0, Object value1) {
        getMessageFacade().addGlobalError(key, value0, value1);
    }

    public static void addGlobalError(String key, Object value0, Object value1, Object value2) {
        getMessageFacade().addGlobalError(key, value0, value1, value2);
    }

    public static void addGlobalError(String key, Object value0, Object value1, Object value2, Object value3) {
        getMessageFacade().addGlobalError(key, value0, value1, value2, value3);
    }

    public static void addMessage(String property, String key, Object[] values) {
        getMessageFacade().addMessage(property, key, values);
    }
    
    public static void addMessage(String property, String key) {
        getMessageFacade().addMessage(property, key);
    }

    public static void addMessage(String property, String key, Object value0) {
        getMessageFacade().addMessage(property, key, value0);
    }

    public static void addMessage(String property, String key, Object value0, Object value1) {
        getMessageFacade().addMessage(property, key, value0, value1);
    }

    public static void addMessage(String property, String key, Object value0, Object value1, Object value2) {
        getMessageFacade().addMessage(property, key, value0, value1, value2);
    }

    public static void addMessage(String property, String key, Object value0, Object value1, Object value2, Object value3) {
        getMessageFacade().addMessage(property, key, value0, value1, value2, value3);
    }


    public static void addGlobalMessage(String key, Object[] values) {
        getMessageFacade().addGlobalMessage(key, values);
    }
    
    public static void addGlobalMessage(String key) {
        getMessageFacade().addGlobalMessage(key);
    }

    public static void addGlobalMessage(String key, Object value0) {
        getMessageFacade().addGlobalMessage(key, value0);
    }

    public static void addGlobalMessage(String key, Object value0, Object value1) {
        getMessageFacade().addGlobalMessage(key, value0, value1);
    }

    public static void addGlobalMessage(String key, Object value0, Object value1, Object value2) {
        getMessageFacade().addGlobalMessage(key, value0, value1, value2);
    }

    public static void addGlobalMessage(String key, Object value0, Object value1, Object value2, Object value3) {
        getMessageFacade().addGlobalMessage(key, value0, value1, value2, value3);
    }

    public static void addErrors(ActionMessages errors) {
        getMessageFacade().addErrors(errors);
    }

    public static void addMessages(ActionMessages messages) {
        getMessageFacade().addMessages(messages);
    }

    public static ActionMessages getErrors() {
        return getMessageFacade().getErrors();
    }

    public static ActionMessages getErrorsFromSession() {
        return getMessageFacade().getErrorsFromSession();
    }

    public static ActionMessages getMessages() {
        return getMessageFacade().getMessages();
    }

    public static ActionMessages getMessagesFromSession() {
        return getMessageFacade().getMessagesFromSession();
    }

    public static void saveErrors() {
        getMessageFacade().saveErrors();
    }

    public static void saveErrors(ActionMessages errors) {
        getMessageFacade().saveErrors(errors);
    }

    public static void saveErrorsInSession() {
        getMessageFacade().saveErrorsInSession();
    }

    public static void saveErrorsInSession(ActionMessages errors) {
        getMessageFacade().saveErrorsInSession(errors);
    }

    public static void saveMessages() {
        getMessageFacade().saveMessages();
    }

    public static void saveMessages(ActionMessages messages) {
        getMessageFacade().saveMessages(messages);
    }

    public static void saveMessagesInSession() {
        getMessageFacade().saveMessagesInSession();
    }

    public static void saveMessagesInSession(ActionMessages messages) {
        getMessageFacade().saveMessagesInSession(messages);
    }

    private static MessageFacade getMessageFacade() {
        return (MessageFacade) SingletonS2ContainerFactory.getContainer().getComponent(MessageFacade.class);
    }
}
