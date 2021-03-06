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

import javax.servlet.http.HttpSession;

/**
 * {@link HttpSession}ごとのコンテキスト情報を扱うインタフェースです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public interface S2StrutsContext extends Serializable {

    /**
     * パスを設定します。
     * 
     * @param path
     *            パス
     */
    void setPath(String path);

    /**
     * 現在の入力パスを返します。
     * 
     * @return 入力パス
     */
    String getCurrentInputPath();

    /**
     * 前回の入力パスを返します。
     * 
     * @return 入力パス
     */
    String getPreviousInputPath();

    /**
     * ページ名をクリアします。
     */
    void clearPageNameElementValue();

}
