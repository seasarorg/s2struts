/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.struts.context;

import java.io.Serializable;

/**
 * アプリケーションごとのコンテキスト情報を扱うインタフェースです。
 * 
 * @author Satoshi Kimura
 */
public interface S2StrutsApplContext extends Serializable {

    /**
     * <code>type</code>に対応するコンテキスト情報をクリアします。
     * 
     * @param type
     *            コンテキスト情報のタイプ
     */
    void clear(ContentsType type);

    /**
     * メソッドバインディング式を返します。
     * 
     * @param mappingName
     * @param key
     * @param value
     * @return
     */
    String getMethodBindingExpression(String mappingName, String key, String value);

    /**
     * メソッドバインディング式を設定します。
     * 
     * @param mappingName
     * @param key
     * @param value
     * @param methodBindingExpression
     */
    void setMethodBindingExpression(String mappingName, String key, String value, String methodBindingExpression);

    /**
     * 検証がキャンセルされたかどうかを返します。
     * 
     * @param mappingName
     * @param key
     * @param value
     * @return
     */
    Boolean isCancelAction(String mappingName, String key, String value);

    /**
     * 検証をキャンセルするかどうを設定します。
     * 
     * @param mappingName
     * @param key
     * @param value
     */
    void setCancelAction(String mappingName, String key, String value);

}
