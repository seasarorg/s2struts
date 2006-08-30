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
package org.seasar.struts.util;

import java.io.File;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.GenericServlet;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class ClassFinderImpl implements ClassFinder {

    private static final String WEB_CLASSES_DIR = "/WEB-INF/classes";

    private static final String WEB_LIB_DIR = "/WEB-INF/lib";

    private static final String ALL_MATCHE_PATTERN = ".*";

    private ClassPool classPool = new ClassPool();

    public synchronized Collection getClassCollection() {
        return this.classPool.getClassCollection();
    }

    public synchronized void destroy() {
        this.classPool.destroy();
    }

    public void find() {
        find(ALL_MATCHE_PATTERN);
    }

    public void find(String pattern) {
        find(true, ALL_MATCHE_PATTERN, pattern);

    }

    public void find(boolean enableJar, String jarFilePattern) {
        find(enableJar, jarFilePattern, ALL_MATCHE_PATTERN);
    }

    public void find(boolean enableJar, String jarFilePattern, String pattern) {
        String cp = System.getProperty("java.class.path");
        String ps = System.getProperty("path.separator");

        for (StringTokenizer tokenizer = new StringTokenizer(cp, ps); tokenizer.hasMoreTokens();) {
            String path = tokenizer.nextToken();
            this.classPool.loadAllClass(path, enableJar, jarFilePattern, pattern);
        }
    }

    public void find(String path, boolean enableJar, String jarFilePattern) {
        find(path, enableJar, jarFilePattern, ALL_MATCHE_PATTERN);
    }

    public void find(String path, boolean enableJar, String jarFilePattern, String pattern) {
        this.classPool.loadAllClass(path, enableJar, jarFilePattern, pattern);
    }

    public void find(File file, boolean enableJar, String jarFilePattern) {
        find(file, enableJar, jarFilePattern, ALL_MATCHE_PATTERN);
    }

    public void find(File file, boolean enableJar, String jarFilePattern, String pattern) {
        this.classPool.loadAllClass(file, enableJar, jarFilePattern, pattern);
    }

    public void find(GenericServlet servlet, boolean enableJar, String jarFilePattern) {
        find(servlet, enableJar, jarFilePattern, ALL_MATCHE_PATTERN);
    }

    public void find(GenericServlet servlet, boolean enableJar, String jarFilePattern,
            String pattern) {
        String classesDirPath = servlet.getServletContext().getRealPath(WEB_CLASSES_DIR);
        if (classesDirPath != null) {
            find(classesDirPath, enableJar, jarFilePattern, pattern);
        }

        String libDirPath = servlet.getServletContext().getRealPath(WEB_LIB_DIR);
        if (libDirPath != null) {
            File[] files = new File(libDirPath).listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    find(files[i], enableJar, jarFilePattern, pattern);
                }
            }
        }
    }

    public void find(Class referenceClass) {
        find(referenceClass, ALL_MATCHE_PATTERN);
    }

    public void find(Class referenceClass, String pattern) {
        File file = WebResourceUtil.createFile(referenceClass);
        this.classPool.loadAllClass(file, true, ALL_MATCHE_PATTERN, pattern);
    }

}
