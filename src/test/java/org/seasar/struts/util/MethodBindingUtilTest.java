package org.seasar.struts.util;

import junit.framework.TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class MethodBindingUtilTest extends TestCase {

    public void testGetComponentName() {
        String expression = "#{component.method}";

        String name = MethodBindingUtil.getComponentName(expression);
        assertEquals("component", name);
    }

    public void testNotGetComponentName() {
        String expression = "#{component_method}";

        try {
            MethodBindingUtil.getComponentName(expression);
            fail();
        } catch (Throwable t) {
            // t.printStackTrace();
        }
    }

    public void testGetMethodName() {
        String expression = "#{component.method}";

        String name = MethodBindingUtil.getMethodName(expression);
        assertEquals("method", name);
    }

    public void testNotGetMethodName() {
        String expression = "#{component_method}";

        try {
            MethodBindingUtil.getMethodName(expression);
            fail();
        } catch (Throwable t) {
            // t.printStackTrace();
        }
    }

}
