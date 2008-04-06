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
package org.seasar.struts.validator.annotation.tiger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.struts.annotation.tiger.BoolType;

/**
 * validation.xmlの複数のarg要素に対応するアノテーションです。
 * 
 * @author Katsuhiko Nagashima
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Args {

	/**
	 * 複数のarg要素に対するキー
	 * <p>
	 * カンマ区切りで値を指定することで複数のarg要素を表すことができます。 それぞれのarg要素に対し{@link #bundle()}と{@link #resource()}の値が適用されます。
	 * </p>
	 * <p>
	 * ここに値を指定した場合、{@link #value()}の指定は参照されません。
	 * </p>
	 */
	String keys() default "";

	/**
	 * {@link #keys()}に指定した複数のarg要素に共通のbundle属性
	 */
	String bundle() default "";

	/**
	 * {@link #keys()}に指定した複数のarg要素に共通のresource属性
	 */
	BoolType resource() default BoolType.UNDEFINED;

	/**
	 * {@link Arg}の配列
	 * <p>
	 * {@link #keys()}に値を指定した場合、ここに指定した値は使用されません。
	 * </p>
	 * <p>
	 * 個々のarg要素を別々に設定したい場合に使用します。 {@link #bundle()}と{@link #resource()}の値は使用されません。
	 * </p>
	 */
	Arg[] value() default {};

}
