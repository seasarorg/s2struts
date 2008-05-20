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
package org.seasar.struts.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.pojo.util.IndexedUtil;
import org.seasar.struts.util.Base64ParameterUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * S2Struts用のサーブレットフィルタです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class S2StrutsFilter implements Filter {

    // private FilterConfig config;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getPathInfo();
        if (path == null) {
            path = request.getServletPath();
        }
        if (path != null) {
            S2StrutsContextUtil.setPath(path);
        }

        handleBase64Parameter(request);

        chain.doFilter(req, res);
    }

    protected void handleBase64Parameter(HttpServletRequest req) {
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            String paramName = (String) e.nextElement();
            String s = paramName.replaceFirst("(\\.x$)|(\\.y$)", "");
            if (IndexedUtil.isIndexedParameter(s)) {
                s = IndexedUtil.getParameter(s);
            }
            Map decodedParams = Base64ParameterUtil.decode(s);
            for (Iterator i = decodedParams.entrySet().iterator(); i.hasNext();) {
                Map.Entry entry = (Map.Entry) i.next();
                req.setAttribute((String) entry.getKey(), entry.getValue());
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
