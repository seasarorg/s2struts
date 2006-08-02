package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.framework.util.Base64Util;
import org.seasar.struts.Constants;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.processor.MockRequestProcessor;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ProcessSetInputPathActionPerformInterceptorTest extends S2TestCase {

    private MockRequestProcessor processor;

    protected void setUp() throws Exception {
        super.setUp();
        include("ProcessSetInputPathActionPerformInterceptorTest.dicon");
    }

    public void testReplaceArgActionMapping() throws Exception {
        MockHttpServletRequest request = getRequest();
        HttpServletResponse response = null;
        Action action = null;
        ActionForm from = null;
        ActionMapping mapping = new MockActionMapping();

        request.setParameter(Constants.PAGE_NAME_ELEMENT_VALUE, Base64Util.encode("/request.html".getBytes()));
        S2StrutsContextUtil.setPath("/previous.html");
        S2StrutsContextUtil.setPath("/current.html");

        ActionForward foward = this.processor.processActionPerform(request, response, action, from, mapping);
        assertEquals(foward.getPath(), S2StrutsContextUtil.getCurrentInputPath());
    }

}
