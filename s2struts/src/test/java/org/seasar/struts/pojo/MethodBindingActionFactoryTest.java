package org.seasar.struts.pojo;

import org.apache.struts.action.ActionServlet;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class MethodBindingActionFactoryTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        include("MethodBindingActionFactoryTest.dicon");
    }

    public void testCreateMethodBinding() {
        MockHttpServletRequest request = getRequest();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");
        ActionServlet servlet = null;

        request.setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression(mapping.getPath(), "1234567890", "TEST", "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, mapping, servlet);
        assertNotNull(action);
    }

    public void testCreateMethodBindingForImageTag() {
        MockHttpServletRequest request = getRequest();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");
        ActionServlet servlet = null;

        request.setParameter("1234567890.y", "");
        S2StrutsContextUtil.setMethodBindingExpression(mapping.getPath(), "1234567890", null, "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, mapping, servlet);
        assertNotNull(action);
    }

    public void testCreateMethodBindingForIndexed() {
        MockHttpServletRequest request = getRequest();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");
        ActionServlet servlet = null;

        request.setParameter("1234567890[10]", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression(mapping.getPath(), "1234567890", "TEST", "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, mapping, servlet);
        assertNotNull(action);
    }

    public void testCannotCreateMethodBindingBecauseNoRequestPrameter() {
        MockHttpServletRequest request = getRequest();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");
        ActionServlet servlet = null;

        S2StrutsContextUtil.setMethodBindingExpression(mapping.getPath(), "1234567890", "TEST", "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, mapping, servlet);
        assertNull(action);
    }

    public void testCannotCreateMethodBindingBecauseExpressionDoesNotRegistered() {
        MockHttpServletRequest request = getRequest();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");
        ActionServlet servlet = null;

        request.setParameter("1234567890", "TEST");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, mapping, servlet);
        assertNull(action);
    }

    public void testCannotCreateMethodBindingBecauseDifferentMappingPath () {
        MockHttpServletRequest request = getRequest();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");
        ActionServlet servlet = null;

        request.setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("/different", "1234567890", "TEST", "#{bindingAction.exe}");

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, mapping, servlet);
        assertNull(action);
    }

}
