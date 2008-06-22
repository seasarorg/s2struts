package org.seasar.struts.glue.action;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.glue.exception.TooManyMethodNameParameterException;

public class MethodNameExtracterImplTest extends S2TestCase {

    private MethodNameExtracterImpl extracter = new MethodNameExtracterImpl();

    public void testExtract() throws Exception {
        getRequest().setParameter("aaa", "");
        getRequest().setParameter("*bbb", "");
        getRequest().setParameter("ccc", "");
        final String name = extracter.extract(getRequest());
        assertEquals("bbb", name);
    }

    public void testExtractMethodName_empty() throws Exception {
        final String name = extracter.extract(getRequest());
        assertNull(name);
    }

    public void testExtractMethodName_tooMany() throws Exception {
        getRequest().setParameter("aaa", "");
        getRequest().setParameter("*bbb", "");
        getRequest().setParameter("*ccc", "");
        try {
            extracter.extract(getRequest());
            fail();
        } catch (final TooManyMethodNameParameterException expected) {
        }
    }

}
