package org.seasar.struts.util;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.struts.action.ExportSessionPropertyActionImpl;
import org.seasar.struts.unit.mock.MockActionMapping;

/**
 * @author Satoshi Kimura
 */
public class BindingUtilTest extends S2TestCase {

    protected void setUp() throws Exception {
        include("s2struts.dicon");
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public BindingUtilTest(String name) {
        super(name);
    }

    public void testExportSessionProperties() {
        ExportSessionPropertyActionImpl action = new ExportSessionPropertyActionImpl();
        action.setFoo("fooVal");
        action.setBar("barVal");
        action.setBaz("bazVal");
        action.setQux("quxVal");
        
        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(ExportSessionPropertyActionImpl.class), new MockActionMapping());
        assertEquals("fooVal", getRequest().getAttribute("foo"));
        assertEquals("barVal", getRequest().getSession().getAttribute("bar"));
        assertEquals("bazVal", getRequest().getSession().getAttribute("baz"));
        assertEquals("quxVal", getRequest().getSession().getAttribute("qux"));
    }

}