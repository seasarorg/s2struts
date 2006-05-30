package org.seasar.struts.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ProcessActionCreateInterceptorTest extends S2TestCase {

    private ExternalRequestProcessor processor;

    protected void setUp() throws Exception {
        super.setUp();
        include("ProcessActionCreateInterceptorTest.dicon");
    }

    public void testIntercept() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionMapping mapping = null;

        Action action = this.processor.processActionCreate(request, response, mapping);
        assertNotNull(action);
        assertEquals(MockAction.class.getName(), action.getClass().getName());
    }

}
