package org.seasar.struts.glue.action;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.glue.action.NameExtracterImpl;

public class NameExtracterImplTest extends S2TestCase {

    private NameExtracterImpl extracter = new NameExtracterImpl();

    public void testExtractComponentName() throws Exception {
        String name = extracter.extractComponentName("@aaa");
        assertEquals("aaa", name);
    }

    public void testExtractComponentName_null() throws Exception {
        String name = extracter.extractComponentName(null);
        assertNull(name);
    }

    public void testExtractMethodName() throws Exception {
        String name = extracter.extractMethodName("@aaa");
        assertEquals("aaa", name);
    }

    public void testExtractMethodName_null() throws Exception {
        String name = extracter.extractMethodName((String) null);
        assertNull(name);
    }

    public void testExtractMethodName_request() throws Exception {
        MockHttpServletRequest request = getRequest();
        request.setParameter("aaa", "");
        request.setParameter("@bbb", "");
        request.setParameter("ccc", "");
        String name = extracter.extractMethodName(request);
        assertEquals("bbb", name);
    }

}
