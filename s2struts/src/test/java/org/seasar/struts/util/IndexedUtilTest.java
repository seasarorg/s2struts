package org.seasar.struts.util;

import junit.framework.TestCase;

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
