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
package org.seasar.struts.factory;

import java.lang.reflect.Field;

import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;

/**
 * @author Katsuhiko Nagashima
 */
public interface StrutsConfigAnnotationHandler {

    public StrutsActionConfig createStrutsActionConfig(Class clazz);

    public StrutsActionForwardConfig createStrutsActionForwardConfig(Field field);

    public StrutsActionFormConfig createStrutsActionFormConfig(Class clazz);

}