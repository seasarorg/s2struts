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

import org.apache.struts.upload.MultipartRequestWrapper;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.Constants;
import org.seasar.struts.pojo.util.IndexedUtil;
import org.seasar.struts.servlet.http.S2ServletRequestWrapper;

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
        if (!request.getParameterNames().hasMoreElements()) {
            return;
        }
        if (request.getAttribute(Constants.BASE64_FORMAT_DECODED) != null) {
            return;
        }
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
            if (decodedParams.containsKey(Constants.PROPERTY_KEY)) {
                String property = (String) decodedParams.get(Constants.PROPERTY_KEY);
                if (property != null) {
                    String value = request.getParameter(paramName);
                    if (request instanceof MultipartRequestWrapper) {
                        MultipartRequestWrapper wrapper = (MultipartRequestWrapper) request;
                        wrapper.setParameter(property, value);
                    } else if (request instanceof S2ServletRequestWrapper) {
                        S2ServletRequestWrapper wrapper = (S2ServletRequestWrapper) request;
                        wrapper.addParameterValue(property, value);
                    } else {
                        S2ServletRequestWrapper wrapper = new S2ServletRequestWrapper(request);
                        wrapper.addParameterValue(property, value);
                        SingletonS2ContainerFactory.getContainer().getExternalContext().setRequest(wrapper);
                    }
                }
            }
        }
        request.setAttribute(Constants.BASE64_FORMAT_DECODED, "");
    }

    /**
     * actionの式を返します。
     * 
     * @param request
     * @return
     */
    public static String getActionExpression(HttpServletRequest request) {
        decodeBase64Parameter(request);
        return (String) request.getAttribute(Constants.ACTION_EXPRESSION_KEY);
    }

    /**
     * actionの式を除去します。
     * 
     * @param request
     */
    public static void removeActionExpression(HttpServletRequest request) {
        request.removeAttribute(Constants.ACTION_EXPRESSION_KEY);
    }

    /**
     * 検証がキャンセルされている場合に<code>true</code>を返します。
     * 
     * @param request
     * @return
     */
    public static boolean isValidationCanceled(HttpServletRequest request) {
        decodeBase64Parameter(request);
        return request.getAttribute(Constants.CANCEL_KEY) != null;
    }

}
