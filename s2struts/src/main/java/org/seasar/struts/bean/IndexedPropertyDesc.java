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
package org.seasar.struts.bean;

/**
 * インデックスつきプロパティを扱うインタフェースです。
 * 
 * @author Satoshi Kimura
 */
public interface IndexedPropertyDesc {

    /**
     * プロパティに値を設定します。
     * 
     * @param target
     *            プロパティをもつインスタンス
     * @param index
     *            インデックス
     * @param value
     *            値
     */
    void setValue(Object target, int index, Object value);

    /**
     * プロパティの値を返します。
     * 
     * @param target
     *            プロパティをもつインスタンス
     * @param index
     *            インデックス
     * @return 値
     */
    Object getValue(Object target, int index);

    /**
     * getterメソッドを持つならば<code>true</code>を返します。
     * 
     * @return getterメソッドを持つならば<code>true</code>、そうでなければ<code>false</code>
     */
    boolean hasReadMethod();

    /**
     * setterメソッドを持つならば<code>true</code>を返します。
     * 
     * @return setterメソッドを持つならば<code>true</code>、そうでなければ<code>false</code>
     */
    boolean hasWriteMethod();
}