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

/**
 * クラスのレジスタです。
 * 
 * @author Satoshi Kimura
 */
public interface ClassRegister {
    /**
     * クラス名でクラスを登録します。
     * 
     * @param type
     *            クラス名
     */
    void register(String type);

    /**
     * クラスを登録します。
     * 
     * @param clazz
     *            クラス
     */
    void register(Class clazz);

    /**
     * クラス名でクラスを取得します。
     * <p>
     * 登録されたクラスがレジスタに存在しない場合、レジスタに登録してからクラスを返します。
     * </p>
     * 
     * @param type
     *            クラス名
     * @return
     */
    Class getClass(String type);

    /**
     * 登録されたクラスを破棄します。
     */
    void destroy();
}