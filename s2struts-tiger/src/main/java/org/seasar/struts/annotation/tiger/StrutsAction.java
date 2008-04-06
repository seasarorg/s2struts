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

import org.seasar.struts.lessconfig.config.StrutsActionConfig;

/**
 * struts-config.xmlのaction要素に対応するアノテーションです。
 * 
 * @author Katsuhiko Nagashima
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StrutsAction {

	/**
	 * path属性
	 */
	String path() default StrutsActionConfig.DEFAULT_PATH;

	/**
	 * name属性
	 */
	String name() default StrutsActionConfig.DEFAULT_NAME;

	/**
	 * scope属性
	 */
	ScopeType scope() default ScopeType.UNDEFINED;

	/**
	 * validate属性
	 */
	BoolType validate() default BoolType.UNDEFINED;

	/**
	 * input属性
	 */
	String input() default StrutsActionConfig.DEFAULT_INPUT;

	/**
	 * parameter属性
	 */
	String parameter() default StrutsActionConfig.DEFAULT_PARAMETER;

	/**
	 * attribute属性
	 */
	String attribute() default StrutsActionConfig.DEFAULT_ATTRIBUTE;

	/**
	 * forward属性
	 */
	String forward() default StrutsActionConfig.DEFAULT_FORWARD;

	/**
	 * include属性
	 */
	String include() default StrutsActionConfig.DEFAULT_INCLUDE;

	/**
	 * prefix属性
	 */
	String prefix() default StrutsActionConfig.DEFAULT_PREFIX;

	/**
	 * suffix属性
	 */
	String suffix() default StrutsActionConfig.DEFAULT_SUFFIX;

	/**
	 * unknown属性
	 */
	BoolType unknown() default BoolType.UNDEFINED;

	/**
	 * roles属性
	 */
	String roles() default StrutsActionConfig.DEFAULT_ROLES;

	/**
	 * cancellable属性
	 */
	BoolType cancellable() default BoolType.UNDEFINED;

	/**
	 * catalog属性
	 */
	String catalog() default StrutsActionConfig.DEFAULT_CATALOG;

	/**
	 * command属性
	 */
	String command() default StrutsActionConfig.DEFAULT_COMMAND;

	/**
	 * extends属性
	 */
	String inherit() default StrutsActionConfig.DEFAULT_INHERIT;

}
