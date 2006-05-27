package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.processor.ExternalRequestProcessor;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class DoSetInputPathForwardInterceptorTest extends S2TestCase {

    private ExternalRequestProcessor processor;

    protected void setUp() throws Exception {
        super.setUp();
        include("DoSetInputPathForwardInterceptorTest.dicon");
    }

    public void testInterceptor() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;

        this.processor.doForward("/next.html", request, response);
        assertEquals("/next.html", S2StrutsContextUtil.getCurrentInputPath());
    }

}
