/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.S2Container;

/**
 * ActionMappingから、コンポート名を作成するためのインターフェース。
 * 
 * @author Satoshi Kimura
 */
public interface ComponentNameCreator {
    /**
     * ActionMappingからコンポーネント名を、作成し、そのコンポーネント名が使用対象のコンテナに見つからなければ、 pathをコンポーネント名として返す。
     * 
     * @param container 検索対象のコンテナ
     * @param mapping コンテナ名を作成するのに使用するマッピング
     * @return コンテナ名
     */
    String createComponentName(S2Container container, ActionMapping mapping);
}