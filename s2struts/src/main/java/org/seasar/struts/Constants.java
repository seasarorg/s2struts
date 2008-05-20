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
package org.seasar.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ForwardConfig;
import org.seasar.struts.taglib.html.PageTag;

/**
 * S2Strutsで使用する定数を集めたクラスです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public interface Constants {

    /**
     * 無設定Strutsにおける{@link ForwardConfig}のデフォルトの名前
     */
    String SUCCESS = "success";

    /**
     * {@link ActionMapping#getScope()}で返されるrequestのスコープ名
     */
    String REQUEST = "request";

    /**
     * {@link ActionMapping#getScope()}で返されるsessionのスコープ名
     */
    String SESSION = "session";

    /**
     * 無設定Strutsにおける未設定の値
     */
    String UNDEFINED = "org.seasar.struts.UNDEFINED";

    /**
     * {@link PageTag}で埋め込まれるページ名のリクエストパラメータ名
     */
    String PAGE_NAME_ELEMENT_VALUE = "org.seasar.struts.page";

    /**
     * {@link PageTag}で埋め込まれるページ名をクリアすることを示す{@link Boolean}を{@link HttpServletRequest}に格納するためのキー
     */
    String PAGE_NAME_ELEMENT_VALUE_CLEAR_MARK = "org.seasar.struts.page.CLEAR_MARK";

    /**
     * {@link CheckboxTag}で埋め込まれるチェックボックスのリクエストパラメータ名
     */
    String CHECKBOX_NAME = "org.seasar.struts.checkbox.";

    /**
     * 使用されていません。
     */
    String BACKUP_SESSION_FORM_KEY = "org.seasar.struts.BACKUP_SESSION_FORM";

    /**
     * Base64のフォーマットでエンコードされたことを示すプレフィックスです。
     */
    String BASE64_FORMAT_PREFIX = "s2struts.BASE64_FORMAT:";

    /**
     * Actionの式を示すキー
     */
    String ACTION_EXPRESSION_KEY = "s2struts.ACTION_EXPRESSION";

    /**
     * 検証をキャンセルを示すキー
     */
    String CANCEL_KEY = "s2struts.CANCEL";

}
