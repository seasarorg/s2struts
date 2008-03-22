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
package org.seasar.struts.pojo.factory;

import java.lang.reflect.Method;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.pojo.config.ActionPropertyConfig;

/**
 * Actionに指定されたアノテーションを扱うインタフェースです。
 * 
 * @author Katsuhiko Nagashima
 */
public interface ActionAnnotationHandler {

    /**
     * {@link ActionPropertyConfig}を取得します。
     * 
     * @param beanDesc
     * @param propertyDesc
     * @return
     */
    ActionPropertyConfig createActionPropertyConfig(BeanDesc beanDesc, PropertyDesc propertyDesc);

    /**
     * パスを取得します。
     * 
     * @param method
     * @return
     */
    String getPath(Method method);

}
