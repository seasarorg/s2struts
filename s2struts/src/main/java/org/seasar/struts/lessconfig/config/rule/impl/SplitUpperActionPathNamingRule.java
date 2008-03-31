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
package org.seasar.struts.lessconfig.config.rule.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.util.StringUtil;

/**
 * 大文字をパスの区切り文字<code>/</code>とその文字の小文字の組み合わせに変換したり、その逆を行ったりします。
 * 
 * @author Katsuhiko Nagashima
 * @author Jun Futagawa
 */
public class SplitUpperActionPathNamingRule extends DefaultActionPathNamingRule {

    protected static final char PATH_SEPARATOR = '/';

    /**
     * 大文字をパスに変換するためのパターンを表す。
     */
    private static final Pattern UPPER_NAME_TO_URI_PATTERN = Pattern.compile("[0-9a-z_-]+|[A-Z][0-9a-z]+");

    /**
     * {@inheritDoc}
     */
    public Class toComponentClass(ModuleConfig config, String path) {
        String prevPath;
        do {
            prevPath = path;
            path = toQualifiedComponentClass(path);
            Class clazz = super.toComponentClass(config, path);
            if (clazz != null) {
                return clazz;
            }
            path = toQualifiedNextPath(prevPath);
        } while (!prevPath.equals(path));
        return null;
    }

    protected String fromActionNameToPath(String actionName) {
        String name = super.fromActionNameToPath(actionName);
        String result = name.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
        return toSplitUpperName(result);
    }

    /**
     * 指定された文字列の / を _ に変換し、次の1文字を大文字にします。<br />
     * from "/usr/Login" to "/user_Login"
     * 
     * @param path
     *            アクションクラスパス
     * @return
     */
    public static String toQualifiedComponentClass(String path) {
        path = path.replaceAll("//", "/");
        int length = path.length();
        if (length > 1 && path.charAt(length - 1) == '/') {
            path = path.substring(0, length - 1);
        }
        length = path.length();
        char chars[] = path.toCharArray();
        // 1文字目は無視
        for (int i = 1; i < length; i++) {
            if (chars[i] == '/' && length > i) {
                chars[i] = '_';
                chars[i + 1] = Character.toLowerCase(chars[i + 1]);
            }
        }
        return new String(chars);
    }

    /**
     * 指定された文字列の最後の / を削除し、次の1文字を大文字にします。
     * 
     * @param path
     *            アクションクラスパス
     * @return
     */
    public static String toQualifiedNextPath(String path) {
        int index = path.lastIndexOf("/");
        int length = path.length();
        if (index > 0) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(path.substring(0, index));
            if (length > index + 1)
                buffer.append(Character.toUpperCase(path.charAt(index + 1)));
            if (length > index + 2)
                buffer.append(path.substring(index + 2));
            return buffer.toString();
        }
        return path;
    }

    /**
     * 大文字をパスに変換する。
     * 
     * @param uri
     *            パス表現
     * @return 大文字をパスに変換したパスを返却する。
     */
    public static String toSplitUpperName(String uri) {
        if (StringUtil.isEmpty(uri))
            return null;
        int lastIndex = uri.length();
        Matcher matcher = UPPER_NAME_TO_URI_PATTERN.matcher(uri);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group().toLowerCase();
            if (matcher.end() == lastIndex) {
                buffer.append(group);
            } else {
                buffer.append(group).append(PATH_SEPARATOR);
            }
        }
        return PATH_SEPARATOR + buffer.toString();
    }

}
