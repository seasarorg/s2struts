package org.seasar.struts.pojo;

import org.apache.struts.action.ActionServlet;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.util.S2StrutsContextUtil;

public class MethodBindingActionFactoryTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        include("MethodBindingActionFactoryTest.dicon");
    }

    public void testCreateMethodBinding() {
        MockHttpServletRequest request = getRequest();
        ActionServlet servlet = null;

        request.setParameter("1234567890", "TEST");
        S2StrutsContextUtil
                .setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request,
                servlet);
        assertNotNull(action);
    }

    public void testCreateMethodBindingForImageTag() {
        MockHttpServletRequest request = getRequest();
        ActionServlet servlet = null;

        request.setParameter("1234567890.y", "");
        S2StrutsContextUtil.setMethodBindingExpression("1234567890", null, "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request,
                servlet);
        assertNotNull(action);
    }

    public void testCreateMethodBindingForIndexed() {
        MockHttpServletRequest request = getRequest();
        ActionServlet servlet = null;

        request.setParameter("1234567890[10]", "TEST");
        S2StrutsContextUtil
                .setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request,
                servlet);
        assertNotNull(action);
    }

    public void testCannotCreateMethodBindingBecauseNoRequestPrameter() {
        MockHttpServletRequest request = getRequest();
        ActionServlet servlet = null;

        S2StrutsContextUtil
                .setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request,
                servlet);
        assertNull(action);
    }

    public void testCannotCreateMethodBindingBecauseExpressionDoesNotRegistered() {
        MockHttpServletRequest request = getRequest();
        ActionServlet servlet = null;

        request.setParameter("1234567890", "TEST");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request,
                servlet);
        assertNull(action);
    }

}
