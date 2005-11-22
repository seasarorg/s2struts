package org.seasar.struts.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Satoshi Kimura
 */
public class RandomUtilTest extends TestCase {

    public void testRandomString() {
        Map map = new HashMap();
        for (int i = 0; i < 10000; i++) {
            String value = RandomUtil.randomString();
            map.put(value, null);
        }

        assertEquals(10000, map.size());
    }

}
