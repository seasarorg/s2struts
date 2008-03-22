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
package org.seasar.struts.pojo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * インデックスつきパラメータに対するユーティリティクラスです。
 * 
 * @author Katsuhiko Nagashima
 */
public class IndexedUtil {

    private static Pattern p = Pattern.compile("\\[([0-9]+)\\]$");

    private IndexedUtil() {
    }

    /**
     * インデックスつきパラメータであれば<code>true</code>を返します。
     * 
     * @param parameter
     *            パラメータ名
     * @return
     */
    public static boolean isIndexedParameter(String parameter) {
        Matcher m = p.matcher(parameter);
        return m.find();
    }

    /**
     * インデックスを除いたパラメータ名のみを返します。
     * 
     * @param parameter
     *            パラメータ名
     * @return
     */
    public static String getParameter(String parameter) {
        Matcher m = p.matcher(parameter);
        return m.replaceAll("");
    }

    /**
     * パラメータ名からインデックスのみ返します。
     * 
     * @param parameter
     *            パラメータ名
     * @return
     */
    public static int getIndex(String parameter) {
        Matcher m = p.matcher(parameter);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        } else {
            throw new IllegalArgumentException("not indexed paramater. arg: " + parameter);
        }
    }

}
