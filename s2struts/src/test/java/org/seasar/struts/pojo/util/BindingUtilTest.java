package org.seasar.struts.pojo.util;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.util.BindingUtil;

/**
 * @author Satoshi Kimura
 */
public class BindingUtilTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
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

    public void testImportProperties() {
        getRequest().setParameter("foo", "fooVal");
        getRequest().getSession().setAttribute("bar", new Integer(100));
        getRequest().setAttribute("baz", new Integer(0));
        getRequest().setParameter("qux[0]", "quxValue");
        getRequest().setParameter("qux[3]", "quxValue3");

        TestPOJOActionImpl action = new TestPOJOActionImpl();
        BindingUtil.importProperties(action, getContainer(), new BeanDescImpl(
                TestPOJOActionImpl.class), new MockActionMapping());

        assertEquals("fooVal", action.getFoo());
        assertEquals(100, action.getBar());
        assertNull(action.getBaz());
        assertEquals("quxValue", action.getQux()[0]);
        assertEquals("", action.getQux()[1]);
        assertNull(action.getQux()[2]);
        assertEquals("quxValue3", action.getQux()[3]);
        assertNotNull(action.getRequest());
    }

    public void testExportProperties() {
        TestPOJOActionImpl action = new TestPOJOActionImpl();
        action.setFoo("fooVal");
        action.setBar(100);
        action.setBaz("bazVal");

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(
                TestPOJOActionImpl.class), new MockActionMapping());
        assertEquals("fooVal", getRequest().getAttribute("foo"));
        assertEquals(new Integer(100), getRequest().getSession().getAttribute("bar"));
        assertEquals("bazVal", getRequest().getAttribute("baz"));
    }

    public void testImportPOJOProperty() {
        Object pojoForm = new BeanValidatorForm(new TestExportPOJOForm("importPOJO"));
        getRequest().setAttribute("exportPOJOForm", pojoForm);

        TestExportPOJOActionImpl action = new TestExportPOJOActionImpl();
        BindingUtil.importProperties(action, getContainer(), new BeanDescImpl(
                TestExportPOJOActionImpl.class), new MockActionMapping());
        assertEquals("importPOJO", action.getExportPOJOForm().getMessage());
    }

    public void testExportPOJOProperty() {
        Object oldPojo = new BeanValidatorForm(new TestExportPOJOForm("oldPOJO"));
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        TestExportPOJOActionImpl action = new TestExportPOJOActionImpl();
        action.setExportPOJOForm(new TestExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(
                TestExportPOJOActionImpl.class), new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest()
                .getAttribute("exportPOJOForm");
        TestExportPOJOForm resultForm = (TestExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean())
                .getInstance();
        assertEquals("newPOJO", resultForm.getMessage());

    }

}