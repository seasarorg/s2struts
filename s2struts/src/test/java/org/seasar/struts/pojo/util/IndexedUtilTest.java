package org.seasar.struts.pojo.util;

import org.seasar.struts.pojo.util.IndexedUtil;

import junit.framework.TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class IndexedUtilTest extends TestCase {

    public void testIsIndexedParameter() {
        assertTrue(IndexedUtil.isIndexedParameter("12345[777[88]"));
    }

    public void testNotIndexedParameter() {
        assertFalse(IndexedUtil.isIndexedParameter("12345[777[aa]"));
    }

    public void testGetParameter() {
        assertEquals("12345[777", IndexedUtil.getParameter("12345[777[88]"));
    }

    public void testGetIndex() {
        assertEquals(88, IndexedUtil.getIndex("12345[777[88]"));
    }

}
