package org.seasar.struts.pojo.processor;

import org.apache.struts.action.Action;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.Constants;
import org.seasar.struts.pojo.MethodBindingAction;
import org.seasar.struts.processor.ExternalRequestProcessor;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ProcessMethodBindingActionCreatorInterceptorTest extends S2TestCase {

    private ExternalRequestProcessor processor;

    protected void setUp() throws Exception {
        super.setUp();
        include("ProcessMethodBindingActionCreatorInterceptorTest.dicon");
    }

    public void testCreateMethodBinding() throws Exception {
        MockHttpServletRequest request = getRequest();
        request.setAttribute(Constants.ACTION_EXPRESSION_KEY, "#{bindingAction.exe}");
        Action action = this.processor.processActionCreate(request, null, null);
        assertNotNull(action);
        assertEquals(MethodBindingAction.class, action.getClass());
    }

    public void testCreateMethodBindingForIndexed() throws Exception {
        MockHttpServletRequest request = getRequest();
        request.setAttribute(Constants.ACTION_EXPRESSION_KEY, "#{bindingAction.exe}[10]");
        Action action = this.processor.processActionCreate(request, null, null);
        assertNotNull(action);
        assertEquals(MethodBindingAction.class, action.getClass());
    }

    public void testCannotCreateMethodBindingBecauseNoRequestPrameter() throws Exception {
        MockHttpServletRequest request = getRequest();
        Action action = this.processor.processActionCreate(request, null, null);
        assertNull(action);
    }

}
