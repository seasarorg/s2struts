package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.processor.MockRequestProcessor;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class ProcessSetInputPathValidateInterceptorTest extends S2TestCase {

    private MockRequestProcessor processor;

    protected void setUp() throws Exception {
        super.setUp();
        include("ProcessSetInputPathValidateInterceptorTest.dicon");
    }
    
    public void testReplaceArgActionMapping() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm from = null;
        ActionMapping mapping = new MockActionMapping();

        S2StrutsContextUtil.setPath("/previous.html");
        S2StrutsContextUtil.setPath("/current.html");
        
        this.processor.processValidate(request, response, from, mapping);
        ActionMapping actual = this.processor.getArgMapping();
        assertNotSame(mapping, actual);
        assertEquals("/previous.html", actual.getInput());
    }
    
    public void testDoNotReplaceArgActionMappingBecauseNoSetPreviousPath() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm from = null;
        ActionMapping mapping = new MockActionMapping();

        S2StrutsContextUtil.setPath("/current.html");
        
        this.processor.processValidate(request, response, from, mapping);
        ActionMapping actual = this.processor.getArgMapping();
        assertSame(mapping, actual);
        assertNull(actual.getInput());
    }

    public void testDoNotReplaceArgActionMappingBecauseSetInput() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm from = null;
        ActionMapping mapping = new MockActionMapping();

        S2StrutsContextUtil.setPath("/previous.html");
        S2StrutsContextUtil.setPath("/current.html");
        mapping.setInput("/input.html");
        
        this.processor.processValidate(request, response, from, mapping);
        ActionMapping actual = this.processor.getArgMapping();
        assertSame(mapping, actual);
        assertEquals("/input.html", actual.getInput());
    }

}
