package org.seasar.struts.util;

import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Katsuhiko Nagashima
 */
public class ConstantValueUtilTest extends TestCase {

    public void testToMap() {
        Map result = ConstantValueUtil.toMap("value1=aaa, value2=bbb");

        assertEquals(2, result.size());
        assertEquals("aaa", result.get("value1"));
        assertEquals("bbb", result.get("value2"));
    }
    
    public void testToMapDefaultKey() {
        Map result = ConstantValueUtil.toMap("aaa, value2=bbb", "value1");

        assertEquals(2, result.size());
        assertEquals("aaa", result.get("value1"));
        assertEquals("bbb", result.get("value2"));
    }
    
}
