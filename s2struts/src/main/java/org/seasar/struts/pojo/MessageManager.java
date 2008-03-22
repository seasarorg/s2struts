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
package org.seasar.struts.pojo;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * {@link ActionMessages}と{@link ActionErrors}のマネージャです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class MessageManager {

    private MessageManager() {
    }

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param values
     */
    public static void addError(String property, String key, Object[] values) {
        getMessageFacade().addError(property, key, values);
    }

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     */
    public static void addError(String property, String key) {
        getMessageFacade().addError(property, key);
    }

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     */
    public static void addError(String property, String key, Object value0) {
        getMessageFacade().addError(property, key, value0);
    }

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     */
    public static void addError(String property, String key, Object value0, Object value1) {
        getMessageFacade().addError(property, key, value0, value1);
    }

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     * @param value2
     */
    public static void addError(String property, String key, Object value0, Object value1, Object value2) {
        getMessageFacade().addError(property, key, value0, value1, value2);
    }

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     * @param value2
     * @param value3
     */
    public static void addError(String property, String key, Object value0, Object value1, Object value2, Object value3) {
        getMessageFacade().addError(property, key, value0, value1, value2, value3);
    }

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param values
     */
    public static void addGlobalError(String key, Object[] values) {
        getMessageFacade().addGlobalError(key, values);
    }

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     */
    public static void addGlobalError(String key) {
        getMessageFacade().addGlobalError(key);
    }

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param value0
     */
    public static void addGlobalError(String key, Object value0) {
        getMessageFacade().addGlobalError(key, value0);
    }

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     */
    public static void addGlobalError(String key, Object value0, Object value1) {
        getMessageFacade().addGlobalError(key, value0, value1);
    }

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     * @param value2
     */
    public static void addGlobalError(String key, Object value0, Object value1, Object value2) {
        getMessageFacade().addGlobalError(key, value0, value1, value2);
    }

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     * @param value2
     * @param value3
     */
    public static void addGlobalError(String key, Object value0, Object value1, Object value2, Object value3) {
        getMessageFacade().addGlobalError(key, value0, value1, value2, value3);
    }

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param values
     */
    public static void addMessage(String property, String key, Object[] values) {
        getMessageFacade().addMessage(property, key, values);
    }

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     */
    public static void addMessage(String property, String key) {
        getMessageFacade().addMessage(property, key);
    }

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     */
    public static void addMessage(String property, String key, Object value0) {
        getMessageFacade().addMessage(property, key, value0);
    }

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     */
    public static void addMessage(String property, String key, Object value0, Object value1) {
        getMessageFacade().addMessage(property, key, value0, value1);
    }

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     * @param value2
     */
    public static void addMessage(String property, String key, Object value0, Object value1, Object value2) {
        getMessageFacade().addMessage(property, key, value0, value1, value2);
    }

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     * @param value2
     * @param value3
     */
    public static void addMessage(String property, String key, Object value0, Object value1, Object value2,
            Object value3) {
        getMessageFacade().addMessage(property, key, value0, value1, value2, value3);
    }

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param values
     */
    public static void addGlobalMessage(String key, Object[] values) {
        getMessageFacade().addGlobalMessage(key, values);
    }

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     */
    public static void addGlobalMessage(String key) {
        getMessageFacade().addGlobalMessage(key);
    }

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param value0
     */
    public static void addGlobalMessage(String key, Object value0) {
        getMessageFacade().addGlobalMessage(key, value0);
    }

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     */
    public static void addGlobalMessage(String key, Object value0, Object value1) {
        getMessageFacade().addGlobalMessage(key, value0, value1);
    }

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     * @param value2
     */
    public static void addGlobalMessage(String key, Object value0, Object value1, Object value2) {
        getMessageFacade().addGlobalMessage(key, value0, value1, value2);
    }

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     * @param value2
     * @param value3
     */
    public static void addGlobalMessage(String key, Object value0, Object value1, Object value2, Object value3) {
        getMessageFacade().addGlobalMessage(key, value0, value1, value2, value3);
    }

    /**
     * エラーを追加します。
     * 
     * @param errors
     */
    public static void addErrors(ActionMessages errors) {
        getMessageFacade().addErrors(errors);
    }

    /**
     * メッセージを追加します。
     * 
     * @param messages
     */
    public static void addMessages(ActionMessages messages) {
        getMessageFacade().addMessages(messages);
    }

    /**
     * リクエストスコープからエラーを取得します。
     * 
     * @return
     */
    public static ActionMessages getErrors() {
        return getMessageFacade().getErrors();
    }

    /**
     * セッションコンテキストからエラーを取得します。
     * 
     * @return
     */
    public static ActionMessages getErrorsFromSession() {
        return getMessageFacade().getErrorsFromSession();
    }

    /**
     * リクエストコンテキストからメッセージを取得します。
     * 
     * @return
     */
    public static ActionMessages getMessages() {
        return getMessageFacade().getMessages();
    }

    /**
     * セッションコンテキストからメッセージを取得します。
     * 
     * @return
     */
    public static ActionMessages getMessagesFromSession() {
        return getMessageFacade().getMessagesFromSession();
    }

    /**
     * 追加したエラーをリクエストコンテキストに保存します。
     */
    public static void saveErrors() {
        getMessageFacade().saveErrors();
    }

    /**
     * エラーをリクエストコンテキストに保存します。
     * 
     * @param errors
     */
    public static void saveErrors(ActionMessages errors) {
        getMessageFacade().saveErrors(errors);
    }

    /**
     * 追加したエラーをセッションコンテキストに保存します。
     */
    public static void saveErrorsInSession() {
        getMessageFacade().saveErrorsInSession();
    }

    /**
     * エラーをセッションコンテキストに保存します。
     * 
     * @param errors
     */
    public static void saveErrorsInSession(ActionMessages errors) {
        getMessageFacade().saveErrorsInSession(errors);
    }

    /**
     * メッセージをリクエストコンテキストに保存します。
     */
    public static void saveMessages() {
        getMessageFacade().saveMessages();
    }

    /**
     * メッセージをリクエストコンテキストに保存します。
     * 
     * @param messages
     */
    public static void saveMessages(ActionMessages messages) {
        getMessageFacade().saveMessages(messages);
    }

    /**
     * 追加したメッセージをセッションコンテキストに保存します。
     */
    public static void saveMessagesInSession() {
        getMessageFacade().saveMessagesInSession();
    }

    /**
     * メッセージをセッションコンテキストに保存します。
     * 
     * @param messages
     */
    public static void saveMessagesInSession(ActionMessages messages) {
        getMessageFacade().saveMessagesInSession(messages);
    }

    private static MessageFacade getMessageFacade() {
        return (MessageFacade) SingletonS2ContainerFactory.getContainer().getComponent(MessageFacade.class);
    }
}
