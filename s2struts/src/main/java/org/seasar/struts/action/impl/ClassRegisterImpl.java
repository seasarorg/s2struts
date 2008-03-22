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
package org.seasar.struts.action.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.Disposable;
import org.seasar.framework.util.DisposableUtil;
import org.seasar.struts.action.ClassRegister;

/**
 * {@link ClassRegister}の実装クラスです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class ClassRegisterImpl implements ClassRegister {

    private Map classes = new HashMap();

    private boolean initialized;

    /**
     * インスタンスを構築します。
     */
    public ClassRegisterImpl() {
    }

    public void register(String type) {
        getClass(type);
    }

    public void register(Class clazz) {
        getClass(clazz.getName());
    }

    public synchronized Class getClass(String type) {
        if (!initialized) {
            initialize();
        }

        Class clazz = (Class) this.classes.get(type);
        if (clazz == null) {
            clazz = ClassUtil.forName(type);
            this.classes.put(type, clazz);
        }
        return clazz;
    }

    /**
     * 初期化します。
     */
    public void initialize() {
        DisposableUtil.add(new Disposable() {
            public void dispose() {
                destroy();
            }
        });
        this.initialized = true;
    }

    public synchronized void destroy() {
        this.classes.clear();
        this.initialized = false;
    }

    /**
     * 登録されたクラスの数を返します。
     * 
     * @return 登録されたクラスの数
     */
    public int getCacheSize() {
        return this.classes.size();
    }

}