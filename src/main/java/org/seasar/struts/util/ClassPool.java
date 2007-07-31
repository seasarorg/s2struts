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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ClassPool {

    private static final Logger logger = Logger.getLogger(ClassPool.class);

    private static final String CLASS_FILE_EXTENTION = ".class";

    private static final int CLASS_FILE_EXTENTION_LENGTH = CLASS_FILE_EXTENTION
            .length();

    private static final char FILE_SEPARATOR = File.separatorChar;

    private Collection classCollection = new ArrayList();

    public void loadAllClass(String classpath, boolean enableJar,
            String jarFilePattern, String pattern) {
        File path = new File(classpath);
        loadAllClass(path, enableJar, jarFilePattern, pattern);
    }

    public void loadAllClass(File path, boolean enableJar,
            String jarFilePattern, String pattern) {
        if (!path.exists()) {
            return;
        }

        if (path.isDirectory()) {
            loadFromDir(path, path, pattern);
        } else if (enableJar) {
            loadFromJar(path, jarFilePattern, pattern);
        }
    }

    public void loadFromJar(File path, String jarFilePattern, String pattern) {
        if (jarFilePattern == null || jarFilePattern.length() == 0) {
            logger
                    .debug("Not load jarFile because of undefineding jarFilePattern.");
            return;
        }

        JarFile jarFile = createJarFileInstance(path);
        if (jarFile == null) {
            return;
        }

        String jarFileName = getJarFileName(jarFile.getName());
        if (!jarFileName.matches(jarFilePattern)) {
            return;
        }

        logger.debug("loading " + jarFile.getName());
        for (Enumeration entries = jarFile.entries(); entries.hasMoreElements();) {
            String entryName = ((JarEntry) entries.nextElement()).getName();
            if (entryName.endsWith(CLASS_FILE_EXTENTION)) {
                String classResourceName = entryName;
                Class clazz = forResourceName(classResourceName);
                addToCollection(clazz, pattern);
            }
        }
    }

    private String getJarFileName(String fullJarFileName) {
        int index = fullJarFileName.lastIndexOf(File.separatorChar);
        if (index < 0) {
            return fullJarFileName;
        }
        return fullJarFileName.substring(index + 1);
    }

    private static JarFile createJarFileInstance(File path) {
        try {
            return new JarFile(path);
        } catch (IOException e) {
            logger.warn(e.toString());
            return null;
        }
    }

    public void loadFromDir(File rootPath, File path, String pattern) {
        File[] files = path.listFiles();
        int rootPathDirNameLength = rootPath.getAbsolutePath().length() + 1;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                loadFromDir(rootPath, files[i], pattern);
            } else {
                if (files[i].getName().endsWith(CLASS_FILE_EXTENTION)) {
                    String classFilePath = files[i].getAbsolutePath();
                    String classResourceName = classFilePath
                            .substring(rootPathDirNameLength);
                    Class clazz = forResourceName(classResourceName);
                    addToCollection(clazz, pattern);
                }
            }
        }
    }

    private synchronized void addToCollection(Class clazz, String pattern) {
        if (clazz == null) {
            return;
        }
        if (clazz.getName().matches(pattern)) {
            this.classCollection.add(clazz);
        }
    }

    private static final Class forResourceName(String classResourceName) {
        String className = classResourceName.substring(0, classResourceName
                .length()
                - CLASS_FILE_EXTENTION_LENGTH);
        className = className.replace(FILE_SEPARATOR, '.');
        className = className.replace('/', '.');
        try {
            Class clazz = ClassUtil.forName(className);
            return clazz;
        } catch (NoClassDefFoundError e) {
            logger.warn(e.toString());
            return null;
        } catch (UnsatisfiedLinkError e) {
            logger.warn(e.toString());
            return null;
        }
    }

    public synchronized Collection getClassCollection() {
        return this.classCollection;
    }

    public synchronized void destroy() {
        this.classCollection = new ArrayList();
    }

}
