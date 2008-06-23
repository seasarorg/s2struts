/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.Constants;

/**
 * @author taedium
 * 
 */
public class RequestUtilTest extends S2TestCase {

    public void testDecodeBase64Parameter() throws Exception {
        Map params = new HashMap();
        params.put(Constants.PROPERTY_KEY, "hoge");
        params.put(Constants.ACTION_EXPRESSION_KEY, "#{aaa.bbb}");
        params.put(Constants.CANCEL_KEY, "");
        String encoded = Base64ParameterUtil.encode(params);
        getRequest().setParameter(encoded, "foo");

        RequestUtil.decodeBase64Parameter(getRequest());

        HttpServletRequest request = (HttpServletRequest) getContainer().getExternalContext().getRequest();
        assertEquals("hoge", request.getAttribute(Constants.PROPERTY_KEY));
        assertEquals("#{aaa.bbb}", request.getAttribute(Constants.ACTION_EXPRESSION_KEY));
        assertEquals("", request.getAttribute(Constants.CANCEL_KEY));
        assertEquals("foo", request.getParameter("hoge"));
        assertNotNull(request.getAttribute(Constants.BASE64_FORMAT_DECODED));
    }

    public void testDecodeBase64Parameter_emptyParameterNames() throws Exception {
        RequestUtil.decodeBase64Parameter(getRequest());
        assertNull(getRequest().getAttribute(Constants.BASE64_FORMAT_DECODED));
    }

    public void testGetActionExpression() throws Exception {
        Map params = new HashMap();
        params.put(Constants.ACTION_EXPRESSION_KEY, "#{aaa.bbb}");
        String encoded = Base64ParameterUtil.encode(params);
        getRequest().setParameter(encoded, "");
        assertEquals("#{aaa.bbb}", RequestUtil.getActionExpression(getRequest()));
    }

    public void testIsValidationCanceled() throws Exception {
        Map params = new HashMap();
        params.put(Constants.CANCEL_KEY, "");
        String encoded = Base64ParameterUtil.encode(params);
        getRequest().setParameter(encoded, "");
        assertTrue(RequestUtil.isValidationCanceled(getRequest()));
    }

}
