package org.seasar.struts.processor;

import javax.servlet.http.HttpServletRequest;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class ProcessMultipartInterceptorTest extends S2TestCase {

    private ExternalRequestProcessor processor;

    protected void setUp() throws Exception {
        super.setUp();
        include("ProcessMultipartInterceptorTest.dicon");
    }

    public void testIntercept() throws Exception {
        HttpServletRequest request = S2StrutsContextUtil.getRequest();
        assertNotNull(request);
        request = this.processor.processMultipart(request);
        assertNull(request);
        assertNull(S2StrutsContextUtil.getRequest());
    }

}
