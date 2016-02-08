/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.struts.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.Servlet;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.validator.ValidatorResults;
import org.apache.struts.upload.MultipartRequestHandler;

/**
 * 特定のクラスに属するプロパティへのアクセスを抑制します。
 * <p>
 * {@link Object} クラスに属するプロパティへのアクセスは必ず抑制されます。
 *
 * @author nakamura-to
 *
 */
public class SuppressPropertyUtilsBean extends PropertyUtilsBean {

    private static final List DEFAULT_CLASSES_TO_SUPPRESS = Arrays
            .asList(new Class[] { Class.class, ClassLoader.class, Servlet.class, MultipartRequestHandler.class, ValidatorResults.class });

    private final Set suppressedBeanClasses;

    private final FastHashMap descriptorsCache;

    /**
     * インスタンスを構築します。
     * <p>
     * このコンストラクタを呼び出すと、以下のクラスまたはそのサブクラスに属するプロパティへのアクセスが抑制されます。
     * <ul>
     * <li>{@link Class}</li>
     * <li>{@link ClassLoader}</li>
     * </ul>
     */
    public SuppressPropertyUtilsBean() {
        this(DEFAULT_CLASSES_TO_SUPPRESS);
    }

    /**
     * 抑制対象のプロパティを持つクラスのコレクションを指定してインスタンスを構築します。
     *
     * @param suppressedBeanClasses
     *            抑制対象のプロパティを持つクラスのコレクション
     */
    public SuppressPropertyUtilsBean(Collection suppressedBeanClasses) {
        if (suppressedBeanClasses == null) {
            this.suppressedBeanClasses = Collections.EMPTY_SET;
        } else {
            this.suppressedBeanClasses = Collections
                    .unmodifiableSet(new HashSet(suppressedBeanClasses));
        }
        descriptorsCache = new FastHashMap();
        descriptorsCache.setFast(true);
    }

    /**
     * 抑制対象のプロパティを持つクラスのセットを返します。
     *
     * @return 抑制対象のプロパティを持つクラスのセット
     */
    public Set getSuppressedBeanClasses() {
        return suppressedBeanClasses;
    }

    public PropertyDescriptor[] getPropertyDescriptors(Class beanClass) {
        PropertyDescriptor[] descriptors = (PropertyDescriptor[]) descriptorsCache
                .get(beanClass);
        if (descriptors != null) {
            return descriptors;
        }
        descriptors = filter(super.getPropertyDescriptors(beanClass));
        descriptorsCache.put(beanClass, descriptors);
        return descriptors;
    }

    private PropertyDescriptor[] filter(PropertyDescriptor[] descriptors) {
        List validDescriptors = new ArrayList();
        for (int i = 0; i < descriptors.length; i++) {
            PropertyDescriptor descriptor = descriptors[i];
            if (descriptor == null) {
                continue;
            }
            if (isSuppressedProperty(descriptor)) {
                continue;
            }
            validDescriptors.add(descriptor);
        }
        return (PropertyDescriptor[]) validDescriptors
                .toArray(new PropertyDescriptor[validDescriptors.size()]);
    }

    private boolean isSuppressedProperty(PropertyDescriptor descriptor) {
        Method readMethod = descriptor.getReadMethod();
        if (readMethod != null && isSuppressedMethod(readMethod)) {
            return true;
        }
        Method writeMethod = descriptor.getWriteMethod();
        if (writeMethod != null && isSuppressedMethod(writeMethod)) {
            return true;
        }
        return false;
    }

    private boolean isSuppressedMethod(Method method) {
        Class clazz = method.getDeclaringClass();
        if (clazz == Object.class) {
            return true;
        }
        for (Iterator it = suppressedBeanClasses.iterator(); it.hasNext();) {
            Class beanClass = (Class) it.next();
            if (beanClass.isAssignableFrom(clazz)) {
                return true;
            }
        }
        return false;
    }
}
