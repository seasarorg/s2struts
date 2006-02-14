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

import javax.servlet.GenericServlet;

/**
 * @author Satoshi Kimura
 */
public interface ClassFinder {
    void find();

    void find(String pattern);

    void find(boolean enableJar);

    void find(boolean enableJar, String pattern);

    void find(String path, boolean enableJar);

    void find(String path, boolean enableJar, String pattern);

    void find(File file, boolean enableJar);

    void find(File file, boolean enableJar, String pattern);

    void find(GenericServlet servlet, boolean enableJar);

    void find(GenericServlet servlet, boolean enableJar, String pattern);

    Collection getClassCollection();

    void destroy();
}
