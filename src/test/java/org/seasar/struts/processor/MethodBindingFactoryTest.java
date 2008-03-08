package org.seasar.struts.processor;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.util.S2StrutsContextUtil;

public class MethodBindingFactoryTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        include("s2struts.dicon");
    }

    public void testGetMethodBinding() throws Exception {
        MockHttpServletRequest request = getRequest();
        request.setParameter("key", "value");
        S2StrutsContextUtil.setMethodBindingExpression("/path", "key", "value",
                "#{bindingAction.hoge}");
        MethodBinding methodBinding = MethodBindingFactory.getMethodBinding(
                request, "/path");
        assertNotNull(methodBinding);
    }

    public void testGetMethodBinding_null() throws Exception {
        MockHttpServletRequest request = getRequest();
        S2StrutsContextUtil.setMethodBindingExpression("/path", "key", "value",
                "#{bindingAction.hoge}");
        MethodBinding methodBinding = MethodBindingFactory.getMethodBinding(
                request, "/path");
        assertNull(methodBinding);
    }
}
