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
 * @see org.seasar.struts.ComponentNameCreator
 * @author Satoshi Kimura
 */
public class ComponentNameCreatorImpl implements ComponentNameCreator {

    /**
     * ActionMappingからコンポーネント名を、作成する。 <br>
     * コンポーネント名を検索する順番は、
     * <UL>
     * <LI>module名(prefix)+path</LI>
     * <LI>path</LI>
     * </UL>
     * となる。
     * 
     * @see org.seasar.struts.ComponentNameCreator#createComponentName(org.seasar.framework.container.S2Container,
     *      org.apache.struts.action.ActionMapping)
     */
    public String createComponentName(S2Container container, ActionMapping mapping) {
        String moduleName = mapping.getModuleConfig().getPrefix();
        String path = mapping.getPath();

        String componentName = moduleName + path;
        if (false == container.hasComponentDef(componentName)) {
            componentName = path;
        }
        return componentName;
    }
}