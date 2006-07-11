package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.processor.ExternalRequestProcessor;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ProcessCancelledValidateInterceptorTest extends S2TestCase {

    private ExternalRequestProcessor processor;

    protected void setUp() throws Exception {
        super.setUp();
        include("ProcessCancelledValidateInterceptorTest.dicon");
    }

    public void testCancelledValidate() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionForm form = null;
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");

        request.setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setCancelAction(mapping.getPath(), "1234567890", "TEST");

        boolean result = this.processor.processValidate(request, response, form, mapping);
        assertTrue(result);
    }
    
    public void testCancelledValidateForImageTag() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionForm form = null;
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");

        request.setParameter("1234567890.y", "");
        S2StrutsContextUtil.setCancelAction(mapping.getPath(), "1234567890", null);

        boolean result = this.processor.processValidate(request, response, form, mapping);
        assertTrue(result);
    }
    
    public void testCancelledValidateForIndexed() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionForm form = null;
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");

        request.setParameter("1234567890[10]", "TEST");
        S2StrutsContextUtil.setCancelAction(mapping.getPath(), "1234567890", "TEST");

        boolean result = this.processor.processValidate(request, response, form, mapping);
        assertTrue(result);
    }

    public void testNotCancelledValidateBecauseNoRequestPrameter() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionForm form = null;
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");

        S2StrutsContextUtil.setCancelAction(mapping.getPath(), "1234567890", "TEST");

        boolean result = this.processor.processValidate(request, response, form, mapping);
        assertFalse(result);
    }
    
    public void testNotCancelledValidateBecauseDoesNotRegisteredContext() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        ActionForm form = null;
        MockActionMapping mapping = new MockActionMapping();
        mapping.setPath("/test");

        request.setParameter("1234567890", "TEST");

        boolean result = this.processor.processValidate(request, response, form, mapping);
        assertFalse(result);
    }
    
}
