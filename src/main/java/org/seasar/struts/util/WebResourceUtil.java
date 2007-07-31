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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.URLUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class WebResourceUtil {

    private static Map strategies = new HashMap();

    static {
        strategies.put("file", new FileSystemStrategy());
        strategies.put("jar", new JarFileStrategy());
        strategies.put("zip", new ZipFileStrategy());
    }

    private WebResourceUtil() {
    }

    public static File createFile(Class referenceClass) {
        String baseClassPath = ResourceUtil.getResourcePath(referenceClass);
        URL url = ResourceUtil.getResource(baseClassPath);
        Strategy strategy = (Strategy) strategies.get(url.getProtocol());
        return strategy.createFile(referenceClass, url);
    }

    //
    //
    //

    protected interface Strategy {
        File createFile(final Class referenceClass, final URL url);
    }

    protected static class FileSystemStrategy implements Strategy {

        public File createFile(final Class referenceClass, final URL url) {
            final String[] names = referenceClass.getName().split("\\.");
            File path = ResourceUtil.getFile(url);
            for (int i = 0; i < names.length; ++i) {
                path = path.getParentFile();
            }
            return path;
        }

    }

    protected static class JarFileStrategy implements Strategy {

        public File createFile(final Class referenceClass, final URL url) {
            final URL nestedUrl = URLUtil.create(url.getPath());
            String path = nestedUrl.getPath();
            int pos = path.lastIndexOf('!');
            String jarFileName = path.substring(0, pos);
            return new File(decode(jarFileName));
        }

        private String decode(String jarFileName) {
            try {
                return URLDecoder.decode(jarFileName, "UTF8");
            } catch (final UnsupportedEncodingException e) {
                throw new IORuntimeException(e);
            }
        }

    }

    /**
     * WebLogic�ŗL��<code>zip:</code>�v���g�R���ŕ\�������URL��T�|�[�g����X�g���e�W�ł��B
     */
    protected static class ZipFileStrategy implements Strategy {

        public File createFile(final Class referenceClass, final URL url) {
            final String urlString = ResourceUtil.toExternalForm(url);
            final int pos = urlString.lastIndexOf('!');
            final String jarFileName = urlString
                    .substring("zip:".length(), pos);
            return new File(jarFileName);
        }
    }

}
