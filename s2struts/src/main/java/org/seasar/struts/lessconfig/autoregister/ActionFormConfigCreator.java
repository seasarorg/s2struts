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
package org.seasar.struts.lessconfig.autoregister;

import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;

/**
 * {@link FormBeanConfig}を作成するためのインタフェースです。
 * 
 * @author Katsuhiko Nagashima
 */
public interface ActionFormConfigCreator {

    /**
     * {@link FormBeanConfig}を作成します。
     * 
     * @param config
     *            {@link ModuleConfig}
     * @param name
     *            ActionFormの名前
     * @return 作成された場合はその{@link FormBeanConfig}、作成されなかった場合は<code>null</code>
     */
    FormBeanConfig createFormBeanConfig(ModuleConfig config, String name);

    /**
     * {@link FormBeanConfig}を作成します。
     * 
     * @param config
     *            {@link ModuleConfig}
     * @param formClass
     *            ActionFormのクラス
     * @return 作成された場合はその{@link FormBeanConfig}、作成されなかった場合は<code>null</code>
     */
    FormBeanConfig createFormBeanConfig(ModuleConfig config, Class formClass);

}
