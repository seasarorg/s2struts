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
package org.seasar.struts.zeroconfig.factory;

import java.lang.reflect.Method;

import org.codehaus.backport175.reader.Annotation;
import org.seasar.struts.zeroconfig.factory.AbstractAnnotationConverter;
import org.seasar.struts.zeroconfig.factory.AnnotationConverter;

/**
 * @author Katsuhiko Nagashima
 */
public class Backport175AnnotationConverter extends AbstractAnnotationConverter {

    private static final AnnotationConverter instance = new Backport175AnnotationConverter();

    private Backport175AnnotationConverter() {
    }

    public static AnnotationConverter getInstance() {
        return instance;
    }

    protected Method[] getAnnotationMethods(Object obj) {
        Annotation annotation = (Annotation) obj;
        return annotation.annotationType().getMethods();
    }

    protected boolean isInstanceOfAnnotation(Object obj) {
        return (obj instanceof Annotation);
    }

}