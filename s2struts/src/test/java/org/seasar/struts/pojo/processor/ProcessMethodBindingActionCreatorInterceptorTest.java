package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.pojo.MethodBindingAction;
import org.seasar.struts.processor.ExternalRequestProcessor;
import org.seasar.struts.util.S2StrutsContextUtil;

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
        HttpServletResponse response = null;
        ActionMapping mapping = null;

        request.setParameter("1234567890", "TEST");
        S2StrutsContextUtil
                .setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.exe}");

        Action action = this.processor.processActionCreate(request, response, mapping);
        assertNotNull(action);
        assertEquals(MethodBindingAction.class.getName(), action.getClass().getName());
    }

    public void testCreateMethodBindingForImageTag() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionMapping mapping = null;

        request.setParameter("1234567890.y", "");
        S2StrutsContextUtil.setMethodBindingExpression("1234567890", null, "#{bindingAction.exe}");

        Action action = this.processor.processActionCreate(request, response, mapping);
        assertNotNull(action);
        assertEquals(MethodBindingAction.class.getName(), action.getClass().getName());
    }

    public void testCreateMethodBindingForIndexed() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionMapping mapping = null;

        request.setParameter("1234567890[10]", "TEST");
        S2StrutsContextUtil
                .setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.exe}");

        Action action = this.processor.processActionCreate(getRequest(), response, mapping);
        assertNotNull(action);
        assertEquals(MethodBindingAction.class.getName(), action.getClass().getName());
    }

    public void testCannotCreateMethodBindingBecauseNoRequestPrameter() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionMapping mapping = null;

        S2StrutsContextUtil
                .setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.exe}");

        Action action = this.processor.processActionCreate(request, response, mapping);
        assertNull(action);
    }

    public void testCannotCreateMethodBindingBecauseExpressionDoesNotRegistered() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionMapping mapping = null;

        request.setParameter("1234567890", "TEST");

        Action action = this.processor.processActionCreate(request, response, mapping);
        assertNull(action);
    }

}
