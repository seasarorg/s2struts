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
package org.seasar.struts.config;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.framework.convention.NamingConvention;

/**
 * Seasar2用のモジュール設定です。
 * 
 * @author higa
 * 
 */
public class S2ModuleConfig extends ModuleConfigImpl {

    private static final long serialVersionUID = 1L;

    /**
     * 命名規約です。
     */
    protected NamingConvention namingConvention;

    /**
     * アクション設定のキャッシュです。
     */
    protected ConcurrentHashMap<String, ActionConfig> actionConfigMap = new ConcurrentHashMap<String, ActionConfig>(
            200);

    /**
     * インスタンスを構築します。
     * 
     * @param namingConvention
     *            命名規約
     */
    public S2ModuleConfig(NamingConvention namingConvention) {
        this.namingConvention = namingConvention;
    }

    @Override
    public ActionConfig findActionConfig(String path) {
        ActionConfig actionConfig = actionConfigMap.get(path);
        if (actionConfig != null) {
            return actionConfig;
        }
        actionConfig = createActionMapping(path);
        ActionConfig actionConfig2 = actionConfigMap.putIfAbsent(path,
                actionConfig);
        return actionConfig2 != null ? actionConfig2 : actionConfig;
    }

    protected ActionMapping createActionMapping(String path) {
        String actionName = namingConvention.fromPathToActionName(path);
    }
}