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
package org.seasar.struts.lessconfig.util;

import java.io.File;
import java.util.Collection;

import javax.servlet.GenericServlet;

/**
 * パターンにマッチするクラスをクラスパスから見つけるためのインタフェースです。
 * <p>
 * 見つけたクラスは{@link #getClassCollection()}で返します。
 * </p>
 * 
 * @author Satoshi Kimura
 */
public interface ClassFinder {

    /**
     * デフォルトの条件でクラスを見つけます。
     */
    void find();

    /**
     * クラスを見つけます。
     * 
     * @param pattern
     */
    void find(String pattern);

    /**
     * クラスを見つけます。
     * 
     * @param enableJar
     * @param jarFilePattern
     */
    void find(boolean enableJar, String jarFilePattern);

    /**
     * クラスを見つけます。
     * 
     * @param enableJar
     * @param jarFilePattern
     * @param pattern
     */
    void find(boolean enableJar, String jarFilePattern, String pattern);

    /**
     * クラスを見つけます。
     * 
     * @param path
     * @param enableJar
     * @param jarFilePattern
     */
    void find(String path, boolean enableJar, String jarFilePattern);

    /**
     * クラスを見つけます。
     * 
     * @param path
     * @param enableJar
     * @param jarFilePattern
     * @param pattern
     */
    void find(String path, boolean enableJar, String jarFilePattern, String pattern);

    /**
     * クラスを見つけます。
     * 
     * @param file
     * @param enableJar
     * @param jarFilePattern
     */
    void find(File file, boolean enableJar, String jarFilePattern);

    /**
     * クラスを見つけます。
     * 
     * @param file
     * @param enableJar
     * @param jarFilePattern
     * @param pattern
     */
    void find(File file, boolean enableJar, String jarFilePattern, String pattern);

    /**
     * クラスを見つけます。
     * 
     * @param servlet
     * @param enableJar
     * @param jarFilePattern
     */
    void find(GenericServlet servlet, boolean enableJar, String jarFilePattern);

    /**
     * クラスを見つけます。
     * 
     * @param servlet
     * @param enableJar
     * @param jarFilePattern
     * @param pattern
     */
    void find(GenericServlet servlet, boolean enableJar, String jarFilePattern, String pattern);

    /**
     * クラスを見つけます。
     * 
     * @param referenceClass
     */
    void find(Class referenceClass);

    /**
     * クラスを見つけます。
     * 
     * @param referenceClass
     * @param pattern
     */
    void find(Class referenceClass, String pattern);

    /**
     * 見つけたクラスのコレクションを返します。
     * 
     * @return 見つけたクラスのコレクション
     */
    Collection getClassCollection();

    /**
     * 破棄します。
     */
    void destroy();
}
