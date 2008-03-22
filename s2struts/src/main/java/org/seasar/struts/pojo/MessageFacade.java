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

/**
 * {@link ActionMessages}と{@link ActionErrors}のファサードです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public interface MessageFacade {

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param values
     */
    void addError(String property, String key, Object[] values);

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     */
    void addError(String property, String key);

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     */
    void addError(String property, String key, Object value0);

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     */
    void addError(String property, String key, Object value0, Object value1);

    /**
     * エラーを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     * @param value2
     */
    void addError(String property, String key, Object value0, Object value1, Object value2);

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
    void addError(String property, String key, Object value0, Object value1, Object value2, Object value3);

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param values
     */
    void addGlobalError(String key, Object[] values);

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     */
    void addGlobalError(String key);

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param value0
     */
    void addGlobalError(String key, Object value0);

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     */
    void addGlobalError(String key, Object value0, Object value1);

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     * @param value2
     */
    void addGlobalError(String key, Object value0, Object value1, Object value2);

    /**
     * グローバルなエラーを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     * @param value2
     * @param value3
     */
    void addGlobalError(String key, Object value0, Object value1, Object value2, Object value3);

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param values
     */
    void addMessage(String property, String key, Object[] values);

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     */
    void addMessage(String property, String key);

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     */
    void addMessage(String property, String key, Object value0);

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     */
    void addMessage(String property, String key, Object value0, Object value1);

    /**
     * メッセージを追加します。
     * 
     * @param property
     * @param key
     * @param value0
     * @param value1
     * @param value2
     */
    void addMessage(String property, String key, Object value0, Object value1, Object value2);

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
    void addMessage(String property, String key, Object value0, Object value1, Object value2, Object value3);

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param values
     */
    void addGlobalMessage(String key, Object[] values);

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     */
    void addGlobalMessage(String key);

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param value0
     */
    void addGlobalMessage(String key, Object value0);

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     */
    void addGlobalMessage(String key, Object value0, Object value1);

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     * @param value2
     */
    void addGlobalMessage(String key, Object value0, Object value1, Object value2);

    /**
     * グローバルなメッセージを追加します。
     * 
     * @param key
     * @param value0
     * @param value1
     * @param value2
     * @param value3
     */
    void addGlobalMessage(String key, Object value0, Object value1, Object value2, Object value3);

    /**
     * エラーを追加します。
     * 
     * @param errors
     */
    void addErrors(ActionMessages errors);

    /**
     * メッセージを追加します。
     * 
     * @param messages
     */
    void addMessages(ActionMessages messages);

    /**
     * リクエストコンテキストからエラーを返します。
     * 
     * @return
     */
    ActionMessages getErrors();

    /**
     * セッションコンテキストからエラーを返します。
     * 
     * @return
     */
    ActionMessages getErrorsFromSession();

    /**
     * リクエストコンテキストからメッセージを返します。
     * 
     * @return
     */
    ActionMessages getMessages();

    /**
     * セッションコンテキストからメッセージを返します。
     * 
     * @return
     */
    ActionMessages getMessagesFromSession();

    /**
     * 追加したエラーをリクエストコンテキストに保存します。
     */
    void saveErrors();

    /**
     * エラーをリクエストコンテキストに保存します。
     * 
     * @param errors
     */
    void saveErrors(ActionMessages errors);

    /**
     * 追加したエラーをセッションコンテキストに保存します。
     */
    void saveErrorsInSession();

    /**
     * エラーをセッションコンテキストに保存します。
     * 
     * @param errors
     */
    void saveErrorsInSession(ActionMessages errors);

    /**
     * 追加したメッセージをリクエストコンテキストに保存します。
     */
    void saveMessages();

    /**
     * メッセージをリクエストコンテキストに保存します。
     * 
     * @param messages
     */
    void saveMessages(ActionMessages messages);

    /**
     * 追加したメッセージをセッションコンテキストに保存します。
     */
    void saveMessagesInSession();

    /**
     * メッセージをセッションコンテキストに保存します。
     * 
     * @param messages
     */
    void saveMessagesInSession(ActionMessages messages);
}
