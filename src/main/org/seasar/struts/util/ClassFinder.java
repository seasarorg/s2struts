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
