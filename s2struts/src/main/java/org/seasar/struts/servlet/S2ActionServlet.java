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
package org.seasar.struts.servlet;

import javax.servlet.ServletException;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.seasar.struts.S2StrutsInitializer;

/**
 * S2Struts用に{@link ActionServlet}を拡張したクラスです。
 * <p>
 * このクラスは、初期化時に{@link Action}のコンポーネント定義をS2コンテナに自動登録します。
 * </p>
 * <p>
 * S2Strtus1.2との下位互換性を維持するために存在します。 S2Strtus1.2との互換性が重要ではない場合、このクラスは使わないことをお勧めします。
 * </p>
 * <p>
 * 代わりに{@link ActionServlet}を使用してください。また、{@link Action}のコンポーネント定義の登録には次のいずれかの方法を使用してください。
 * <ul>
 * <li>diconファイルで明示的にコンポーネントを定義する</li>
 * <li>AutoRegisterでコンポーネントを自動登録する</li>
 * <li>SMART deployでコンポーネントを自動登録する</li>
 * </ul>
 * </p>
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class S2ActionServlet extends ActionServlet {

    private static final long serialVersionUID = -6821902706517242158L;

    /**
     * インスタンスを構築します。
     */
    public S2ActionServlet() {
        super();
    }

    /**
     * @see ActionServlet#init()
     * @see S2StrutsInitializer#registerActionClass(ActionServlet)
     */
    public void init() throws ServletException {
        try {
            super.init();
            S2StrutsInitializer.initServlet(this);
        } catch (ServletException e) {
            e.printStackTrace();
            throw e;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

}