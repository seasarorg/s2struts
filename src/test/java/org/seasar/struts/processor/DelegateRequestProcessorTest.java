package org.seasar.struts.processor;

import org.apache.struts.action.RequestProcessor;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class DelegateRequestProcessorTest extends S2TestCase {
    
    protected void setUp() throws Exception {
        super.setUp();
        include("DelegateRequestProcessorTest.dicon");
    }

    public void testDelegate() {
        RequestProcessor processor = new DelegateRequestProcessor();
        assertEquals("MockRequestProcessor", processor.toString());
    }

}
