package org.seasar.struts.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class Base64ParameterUtilTest extends TestCase {

    public void test() throws Exception {
        Map expected = new HashMap();
        expected.put("aaa", "123");
        expected.put("bbb", "<>&=");
        expected.put("ccc", "");
        expected.put("ddd", "ABC");
        String s = Base64ParameterUtil.encode(expected);
        Map actual = Base64ParameterUtil.decode(s);
        assertEquals(expected, actual);
    }

    public void testEncodeEmptyMap() throws Exception {
        String s = Base64ParameterUtil.encode(new HashMap());
        assertEquals("", s);
    }

    public void testDecodeNonBase64FormatValue() throws Exception {
        Map map = Base64ParameterUtil.decode("aaa");
        assertTrue(map.isEmpty());
    }

}
