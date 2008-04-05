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
package org.seasar.struts.lessconfig.autoregister;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.seasar.struts.lessconfig.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.lessconfig.factory.StrutsConfigAnnotationHandlerFactory;

/**
 * @author Satoshi Kimura
 */
public class ClassComparator implements Comparator, Serializable {

    private static final long serialVersionUID = -8018713861613285412L;

    public int compare(Object o1, Object o2) {
        Class clazz1 = (Class) o1;
        Class clazz2 = (Class) o2;

        StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
        Object annotation1 = annHandler.createStrutsActionFormConfig(clazz1);
        Object annotation2 = annHandler.createStrutsActionFormConfig(clazz2);
        if (annotation1 == null) {
            annotation1 = annHandler.createStrutsActionConfig(clazz1);
        }
        if (annotation2 == null) {
            annotation2 = annHandler.createStrutsActionConfig(clazz2);
        }
        if (annotation1 != null && annotation2 != null) {
            return 0;
        } else if (annotation1 == null && annotation2 == null) {
            return 0;
        } else if (annotation1 != null) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * クラスのコレクションをソートします。
     * 
     * @param collection
     * @return
     */
    public static List sort(Collection collection) {
        Object[] objects = collection.toArray();
        Arrays.sort(objects, new ClassComparator());
        return Arrays.asList(objects);
    }
}
