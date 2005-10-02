package org.seasar.httpunit.mock;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Satoshi Kimura
 */
public class MockObjectInterceptorMapping {
    private static Map mapping;

    static {
        mapping = new HashMap();
        mapping.put(MockHttpServletRequest.class, "HttpServletRequestMockObjectInterceptor");
        mapping.put(MockHttpServletResponse.class, "HttpServletResponseMockObjectInterceptor");
    }

    private MockObjectInterceptorMapping() {
    }

    public static String getMockObjectInterceptorName(Class clazz) {
        return (String) mapping.get(clazz);
    }
}