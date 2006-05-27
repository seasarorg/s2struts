package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.PojoProcessAction;
import org.seasar.struts.processor.ExternalRequestProcessor;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ProcessPojoActionCreateInterceptorTest extends S2TestCase {

    private ExternalRequestProcessor processor;

    protected void setUp() throws Exception {
        super.setUp();
        include("ProcessPojoActionCreateInterceptorTest.dicon");
    }

    public void testCreatePojoAction() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionMapping mapping = new MockActionMapping();

        mapping.setType(TestPojoAction.class.getName());

        Action action = this.processor.processActionCreate(request, response, mapping);
        assertNotNull(action);
        assertEquals(PojoProcessAction.class.getName(), action.getClass().getName());
    }

    public void testNoCreatePojoActionBecauseTypeIsNull() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionMapping mapping = new MockActionMapping();

        Action action = this.processor.processActionCreate(request, response, mapping);
        assertNull(action);
    }

    public void testNoCreatePojoActionBecauseTypeIsAction() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionMapping mapping = new MockActionMapping();

        mapping.setType(TestAction.class.getName());

        Action action = this.processor.processActionCreate(request, response, mapping);
        assertNull(action);
    }

}
