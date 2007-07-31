package org.seasar.struts.util;

import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Katsuhiko Nagashima
 */
public class ConstantValueUtilTest extends TestCase {

    public void testToMap() {
        Map result = ConstantValueUtil.toMap("value1 = aaa, value2=bbb");

        assertEquals(2, result.size());
        assertEquals("aaa", result.get("value1"));
        assertEquals("bbb", result.get("value2"));
    }

    public void testToMapDefaultKey() {
        Map result = ConstantValueUtil.toMap(" aaa , value2=bbb", "value1");

        assertEquals(2, result.size());
        assertEquals("aaa", result.get("value1"));
        assertEquals("bbb", result.get("value2"));
    }

    public void testToMapManyEqual() {
        Map result = ConstantValueUtil
                .toMap("value1 = aaa.jsp?method=go, value2 = bbb.jsp?method=do");

        assertEquals(2, result.size());
        assertEquals("aaa.jsp?method=go", result.get("value1"));
        assertEquals("bbb.jsp?method=do", result.get("value2"));
    }

    public void testToMapEmptyValue() {
        Map result = ConstantValueUtil.toMap("value1 = , value2=");

        assertEquals(2, result.size());
        assertEquals("", result.get("value1"));
        assertEquals("", result.get("value2"));
    }

    public void testToMapNoDefaultKeyNeeding() {
        try {
            ConstantValueUtil.toMap("aaa, value2=bbb");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("aaa, value2=bbb", e.getMessage());
        }
    }

    public void testToMapValueEnclosedWithSingleCortation() {
        Map result = ConstantValueUtil
                .toMap("value1 = 'aaa, bbb', value2='bbb, ccc'");

        assertEquals(2, result.size());
        assertEquals("aaa, bbb", result.get("value1"));
        assertEquals("bbb, ccc", result.get("value2"));
    }

    public void testToMapEmptyValueEnclosedWithSingleCortation() {
        Map result = ConstantValueUtil.toMap("value1 = '', value2=''");

        assertEquals(2, result.size());
        assertEquals("", result.get("value1"));
        assertEquals("", result.get("value2"));
    }

    public void testToMapValueEnclosedWithDoubleCortation() {
        Map result = ConstantValueUtil
                .toMap("value1 = \"aaa, bbb\", value2=\"bbb, ccc\"");

        assertEquals(2, result.size());
        assertEquals("aaa, bbb", result.get("value1"));
        assertEquals("bbb, ccc", result.get("value2"));
    }

    public void testToMapEmptyValueEnclosedWithDoubleCortation() {
        Map result = ConstantValueUtil.toMap("value1 = \"\", value2=\"\"");

        assertEquals(2, result.size());
        assertEquals("", result.get("value1"));
        assertEquals("", result.get("value2"));
    }

    public void testToMapValueNoCloseSingleCortation1() {
        try {
            ConstantValueUtil.toMap("value1 = 'aaa, bbb', value2='bbb, ccc");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Not closed value. 'bbb, ccc", e.getMessage());
        }
    }

    public void testToMapValueNoCloseSingleCortation2() {
        try {
            ConstantValueUtil.toMap("value1 = 'aaa, bbb, value2='bbb, ccc'");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Illegal separator value. 'aaa, bbb, value2='bbb, ccc'", e
                            .getMessage());
        }
    }

}
