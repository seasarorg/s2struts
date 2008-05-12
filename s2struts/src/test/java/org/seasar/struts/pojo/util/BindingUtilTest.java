package org.seasar.struts.pojo.util;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.framework.convention.impl.NamingConventionImpl;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.lessconfig.config.rule.impl.SubApplicationActionFormNamingRule;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.util.web.aaa.TestForm;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class BindingUtilTest extends S2TestCase {

    private TestPOJOActionImpl testPOJOAction;

    protected void setUp() throws Exception {
        super.setUp();
        register(TestPOJOActionImpl.class, "testPOJOAction");
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

        BindingUtil.importProperties(testPOJOAction, getContainer(), new BeanDescImpl(TestPOJOActionImpl.class),
                new MockActionMapping());

        assertEquals("fooVal", testPOJOAction.getFoo());
        assertEquals(100, testPOJOAction.getBar());
        assertNull(testPOJOAction.getBaz());
        assertEquals("quxValue", testPOJOAction.getQux()[0]);
        assertEquals("", testPOJOAction.getQux()[1]);
        assertNull(testPOJOAction.getQux()[2]);
        assertEquals("quxValue3", testPOJOAction.getQux()[3]);
        assertNotNull(testPOJOAction.getRequest());
    }

    public void testExportProperties() {
        TestPOJOActionImpl action = new TestPOJOActionImpl();
        action.setFoo("fooVal");
        action.setBar(100);
        action.setBaz("bazVal");

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(TestPOJOActionImpl.class),
                new MockActionMapping());
        assertEquals("fooVal", getRequest().getAttribute("foo"));
        assertEquals(new Integer(100), getRequest().getSession().getAttribute("bar"));
        assertEquals("bazVal", getRequest().getAttribute("baz"));
    }

    public void testImportPOJOProperty() {
        Object pojoForm = new BeanValidatorForm(new TestExportPOJOForm("importPOJO"));
        getRequest().setAttribute("exportPOJOForm", pojoForm);

        TestExportPOJOActionImpl action = new TestExportPOJOActionImpl();
        BindingUtil.importProperties(action, getContainer(), new BeanDescImpl(TestExportPOJOActionImpl.class),
                new MockActionMapping());
        assertEquals("importPOJO", action.getExportPOJOForm().getMessage());
    }

    public void testExportPOJOProperty() {
        FormBeanConfig beanConfig = new FormBeanConfig();
        beanConfig.setName("exportPOJOForm");

        ActionMapping actionConfig = new MockActionMapping();
        actionConfig.setName("exportPOJOForm");
        actionConfig.setScope("request");

        ModuleConfig config = new ModuleConfigImpl();
        config.addFormBeanConfig(beanConfig);
        config.addActionConfig(actionConfig);
        getServletContext().setAttribute(Globals.MODULE_KEY, config);

        Object oldPojo = new BeanValidatorForm(new TestExportPOJOForm("oldPOJO"));
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        TestExportPOJOActionImpl action = new TestExportPOJOActionImpl();
        action.setExportPOJOForm(new TestExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(TestExportPOJOActionImpl.class),
                new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        TestExportPOJOForm resultForm = (TestExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("newPOJO", resultForm.getMessage());
    }

    public void testExportPOJOPropertyDifferentActionConfigScopes() {
        FormBeanConfig beanConfig = new FormBeanConfig();
        beanConfig.setName("exportPOJOForm");

        ActionMapping actionConfig1 = new MockActionMapping();
        actionConfig1.setName("exportPOJOForm");
        actionConfig1.setScope("session");
        ActionMapping actionConfig2 = new MockActionMapping();
        actionConfig2.setName("exportPOJOForm");
        actionConfig2.setScope("request");

        ModuleConfig config = new ModuleConfigImpl();
        config.addFormBeanConfig(beanConfig);
        config.addActionConfig(actionConfig1);
        config.addActionConfig(actionConfig2);
        getServletContext().setAttribute(Globals.MODULE_KEY, config);

        Object oldPojo = new BeanValidatorForm(new TestExportPOJOForm("oldPOJO"));
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        TestExportPOJOActionImpl action = new TestExportPOJOActionImpl();
        action.setExportPOJOForm(new TestExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(TestExportPOJOActionImpl.class),
                new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        TestExportPOJOForm resultForm = (TestExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("newPOJO", resultForm.getMessage());
    }

    public void testExportPOJOPropertySameActionConfigScopes() {
        FormBeanConfig beanConfig = new FormBeanConfig();
        beanConfig.setName("exportPOJOForm");

        ActionMapping actionConfig1 = new MockActionMapping();
        actionConfig1.setName("exportPOJOForm");
        actionConfig1.setScope("session");
        ActionMapping actionConfig2 = new MockActionMapping();
        actionConfig2.setName("exportPOJOForm");
        actionConfig2.setScope("session");

        ModuleConfig config = new ModuleConfigImpl();
        config.addFormBeanConfig(beanConfig);
        config.addActionConfig(actionConfig1);
        config.addActionConfig(actionConfig2);
        getServletContext().setAttribute(Globals.MODULE_KEY, config);

        Object oldPojo = new BeanValidatorForm(new TestExportPOJOForm("oldPOJO"));
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        TestExportPOJOActionImpl action = new TestExportPOJOActionImpl();
        action.setExportPOJOForm(new TestExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(TestExportPOJOActionImpl.class),
                new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getSession().getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        TestExportPOJOForm resultForm = (TestExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("newPOJO", resultForm.getMessage());
    }

    public void testExportPOJOPropertyNotExistsRequestAttribute() {
        FormBeanConfig beanConfig = new FormBeanConfig();
        beanConfig.setName("exportPOJOForm");

        ActionMapping actionConfig = new MockActionMapping();
        actionConfig.setName("exportPOJOForm");
        actionConfig.setScope("request");

        ModuleConfig config = new ModuleConfigImpl();
        config.addFormBeanConfig(beanConfig);
        config.addActionConfig(actionConfig);
        getServletContext().setAttribute(Globals.MODULE_KEY, config);

        TestExportPOJOActionImpl action = new TestExportPOJOActionImpl();
        action.setExportPOJOForm(new TestExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(TestExportPOJOActionImpl.class),
                new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        TestExportPOJOForm resultForm = (TestExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("newPOJO", resultForm.getMessage());
    }

    public void testExportPOJOPropertyAndExportAnnotation() {
        FormBeanConfig beanConfig = new FormBeanConfig();
        beanConfig.setName("exportPOJOAnnotationForm");

        ActionMapping actionConfig = new MockActionMapping();
        actionConfig.setName("exportPOJOAnnotationForm");
        actionConfig.setScope("request");

        ModuleConfig config = new ModuleConfigImpl();
        config.addFormBeanConfig(beanConfig);
        config.addActionConfig(actionConfig);
        getServletContext().setAttribute(Globals.MODULE_KEY, config);

        TestExportPOJOAnnotationActionImpl action = new TestExportPOJOAnnotationActionImpl();
        action.setExportPOJOAnnotationForm(new TestExportPOJOAnnotationForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(),
                new BeanDescImpl(TestExportPOJOAnnotationActionImpl.class), new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getSession().getAttribute(
                "exportPOJOAnnotationForm");
        assertNotNull(beanForm);
        TestExportPOJOAnnotationForm resultForm = (TestExportPOJOAnnotationForm) ((WrapDynaBean) beanForm.getDynaBean())
                .getInstance();
        assertEquals("newPOJO", resultForm.getMessage());
    }

    public void testExportPOJOPropertyNotWrapped() {
        FormBeanConfig beanConfig = new FormBeanConfig();
        beanConfig.setName("exportPOJOForm");

        ActionMapping actionConfig = new MockActionMapping();
        actionConfig.setName("exportPOJOForm");
        actionConfig.setScope("request");

        ModuleConfig config = new ModuleConfigImpl();
        config.addFormBeanConfig(beanConfig);
        config.addActionConfig(actionConfig);
        getServletContext().setAttribute(Globals.MODULE_KEY, config);

        Object oldPojo = new TestExportPOJOForm("oldPOJO");
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        TestExportPOJOActionImpl action = new TestExportPOJOActionImpl();
        action.setExportPOJOForm(new TestExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(TestExportPOJOActionImpl.class),
                new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        TestExportPOJOForm resultForm = (TestExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("newPOJO", resultForm.getMessage());
    }

    public void testGetActionFormPropertyName() throws Exception {
        String name = BindingUtil.getActionFormPropertyName(getContainer(), TestForm.class);
        assertNull(name);
    }

    public void setUpGetActionFormPropertyName_subApplication() throws Exception {
        NamingConventionImpl convention = (NamingConventionImpl) getNamingConvention();
        convention.addRootPackageName(ClassUtil.getPackageName(getClass()));
        register(SubApplicationActionFormNamingRule.class);
    }

    public void testGetActionFormPropertyName_subApplication() throws Exception {
        String name = BindingUtil.getActionFormPropertyName(getContainer(), TestForm.class);
        assertNotNull(name);
    }

}