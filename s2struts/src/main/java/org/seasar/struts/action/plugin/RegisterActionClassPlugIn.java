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
package org.seasar.struts.action.plugin;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.seasar.struts.S2StrutsInitializer;

/**
 * {@link Action}のコンポーネント定義をS2コンテナに自動登録するクラスです。
 * <p>
 * S2Strtus1.2との下位互換性を維持するために存在します。 S2Strtus1.2との互換性が重要ではない場合、このクラスは使わないことをお勧めします。
 * </p>
 * <p>
 * {@link Action}のコンポーネント定義をS2コンテナに登録するには、代わりに次の方法のいずれかを使用してください。
 * <ul>
 * <li>diconファイルで明示的にコンポーネントを定義する</li>
 * <li>AutoRegisterでコンポーネントを自動登録する</li>
 * <li>SMART deployでコンポーネントを自動登録する</li>
 * </ul>
 * </p>
 * 
 * @author Satoshi Kimura
 */
public class RegisterActionClassPlugIn implements PlugIn {

    /**
     * インスタンスを構築します。
     */
    public RegisterActionClassPlugIn() {
    }

    public void destroy() {
    }

    public void init(ActionServlet servlet, ModuleConfig config) {
        S2StrutsInitializer.registerActionClass(servlet, config);
    }

}