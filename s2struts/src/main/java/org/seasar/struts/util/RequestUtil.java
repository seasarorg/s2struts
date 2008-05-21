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

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.struts.pojo.util.IndexedUtil;

/**
 * {@link HttpServletRequest}のためのユーティリティです。
 * 
 * @author Katsuhiko Nagashima
 */
public class RequestUtil {

    private RequestUtil() {

    }

    /**
     * <code>request</code>を使って、<code>name</code>をキーとする値を取り出します。
     * <p>
     * 次の順番で取得を試み、<code>null</code>以外の値が取得できたらそれを返します。
     * <ul>
     * <li>{@link ServletRequest#getAttribute(String)}</li>
     * <li>{@link ServletRequest#getParameter(String)}</li>
     * <li>{@link HttpSession#getAttribute(String)}</li>
     * </ul>
     * </p>
     * <p>
     * 何も取得できない場合は<code>null</code>を返します。
     * </p>
     * 
     * @param request
     * @param name
     * @return
     */
    public static Object getValue(HttpServletRequest request, String name) {
        Object var = request.getAttribute(name);
        if (var != null) {
            return var;
        }
        var = request.getParameter(name);
        if (var != null) {
            return var;
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            var = session.getAttribute(name);
            if (var != null) {
                return var;
            }
        }
        return null;
    }

    /**
     * Base64でエンコードされたパラメータをデコードします。
     * 
     * @param request
     */
    public static void decodeBase64Parameter(HttpServletRequest request) {
        for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
            String paramName = (String) e.nextElement();
            String s = paramName.replaceFirst("(\\.x$)|(\\.y$)", "");
            if (IndexedUtil.isIndexedParameter(s)) {
                s = IndexedUtil.getParameter(s);
            }
            Map decodedParams = Base64ParameterUtil.decode(s);
            for (Iterator i = decodedParams.entrySet().iterator(); i.hasNext();) {
                Map.Entry entry = (Map.Entry) i.next();
                request.setAttribute((String) entry.getKey(), entry.getValue());
            }
        }
    }

}
