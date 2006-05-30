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
package org.seasar.struts.annotation.tiger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.struts.zeroconfig.config.StrutsActionConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StrutsAction {
    
    String path() default StrutsActionConfig.DEFAULT_PATH;

    String name() default StrutsActionConfig.DEFAULT_NAME;

    ScopeType scope() default ScopeType.REQUEST;

    boolean validate() default StrutsActionConfig.DEFAULT_VALIDATE;

    String input() default StrutsActionConfig.DEFAULT_INPUT;

    String parameter() default StrutsActionConfig.DEFAULT_PARAMETER;

    String attribute() default StrutsActionConfig.DEFAULT_ATTRIBUTE;

    String forward() default StrutsActionConfig.DEFAULT_FORWARD;

    String include() default StrutsActionConfig.DEFAULT_INCLUDE;

    String prefix() default StrutsActionConfig.DEFAULT_PREFIX;

    String suffix() default StrutsActionConfig.DEFAULT_SUFFIX;

    boolean unknown() default StrutsActionConfig.DEFAULT_UNKNOWN;

    String roles() default StrutsActionConfig.DEFAULT_ROLES;
    
    boolean cancellable() default StrutsActionConfig.DEFAULT_CANCELLABLE;

}
