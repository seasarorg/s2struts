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

import org.seasar.struts.lessconfig.config.StrutsActionFormConfig;

/**
 * struts-config.xmlのform-beans要素に対応するアノテーションです。
 * 
 * @author Katsuhiko Nagashima
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StrutsActionForm {

	/**
	 * name属性
	 */
	String name() default StrutsActionFormConfig.DEFAULT_NAME;

	/**
	 * restricted属性
	 */
	BoolType restricted() default BoolType.UNDEFINED;

	/**
	 * extends属性
	 */
	String inherit() default StrutsActionFormConfig.DEFAULT_INHERIT;

}
