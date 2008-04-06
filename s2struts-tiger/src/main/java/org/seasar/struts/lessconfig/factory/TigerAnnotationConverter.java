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
package org.seasar.struts.lessconfig.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * アノテーションをアノテーションの属性名をキー、属性値を値とする{@link Map}に変換します。
 * 
 * @author Katsuhiko Nagashima
 */
public class TigerAnnotationConverter extends AbstractAnnotationConverter {

	private static final AnnotationConverter instance = new TigerAnnotationConverter();

	private TigerAnnotationConverter() {
	}

	/**
	 * このクラスのシングルトンであるインスタンスを返します。
	 * 
	 * @return
	 */
	public static AnnotationConverter getInstance() {
		return instance;
	}

	@Override
	protected Method[] getAnnotationMethods(Object obj) {
		List<Method> result = new ArrayList<Method>();

		Annotation annotation = (Annotation) obj;
		for (Method method : annotation.annotationType().getMethods()) {
			if (method.getDeclaringClass() != Annotation.class) {
				result.add(method);
			}
		}

		Method[] array = new Method[result.size()];
		return result.toArray(array);
	}

	@Override
	protected boolean isInstanceOfAnnotation(Object obj) {
		return (obj instanceof Annotation);
	}

}