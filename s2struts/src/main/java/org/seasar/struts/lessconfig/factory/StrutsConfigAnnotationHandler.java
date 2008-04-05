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
package org.seasar.struts.lessconfig.factory;

import java.lang.reflect.Field;

import org.seasar.struts.lessconfig.config.StrutsActionConfig;
import org.seasar.struts.lessconfig.config.StrutsActionFormConfig;
import org.seasar.struts.lessconfig.config.StrutsActionForwardConfig;

/**
 * 無設定Struts用のアノテーションを扱うインタフェースです。
 * 
 * @author Katsuhiko Nagashima
 */
public interface StrutsConfigAnnotationHandler {

    /**
     * {@link StrutsActionConfig}を作成します。
     * 
     * @param clazz
     * @return
     */
    StrutsActionConfig createStrutsActionConfig(Class clazz);

    /**
     * {@link StrutsActionForwardConfig}を作成します。
     * 
     * @param field
     * @return
     */
    StrutsActionForwardConfig createStrutsActionForwardConfig(Field field);

    /**
     * {@link StrutsActionFormConfig}を作成します。
     * 
     * @param clazz
     * @return
     */
    StrutsActionFormConfig createStrutsActionFormConfig(Class clazz);

}