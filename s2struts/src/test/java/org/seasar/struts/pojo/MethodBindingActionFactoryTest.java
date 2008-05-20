package org.seasar.struts.pojo;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.Constants;

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
        request.setAttribute(Constants.ACTION_EXPRESSION_KEY, "#{bindingAction.exe}");
        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, null, null);
        assertNotNull(action);
    }

    public void testCreateMethodBindingForIndexed() {
        MockHttpServletRequest request = getRequest();
        request.setAttribute(Constants.ACTION_EXPRESSION_KEY, "#{bindingAction.exe}[10]");
        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, null, null);
        assertNotNull(action);
    }

    public void testCannotCreateMethodBindingBecauseNoRequestPrameter() {
        MockHttpServletRequest request = getRequest();
        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request, null, null);
        assertNull(action);
    }

}
