package org.seasar.struts.util;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.struts.action.ExportPOJOActionImpl;
import org.seasar.struts.action.ExportPOJOAnnotationActionImpl;
import org.seasar.struts.action.ExportPOJOAnnotationForm;
import org.seasar.struts.action.ExportPOJOForm;
import org.seasar.struts.action.POJOActionImpl;
import org.seasar.struts.mock.MockActionMapping;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
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

    public void testImportProperties() {
        getRequest().setParameter("foo", "fooVal");
        getRequest().getSession().setAttribute("bar", new Integer(100));
        getRequest().setAttribute("baz", new Integer(0));
        getRequest().setParameter("qux[0]", "quxValue");
        getRequest().setParameter("qux[3]", "quxValue3");

        POJOActionImpl action = new POJOActionImpl();
        BindingUtil.importProperties(action, getContainer(),
                new BeanDescImpl(POJOActionImpl.class), new MockActionMapping());

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
        POJOActionImpl action = new POJOActionImpl();
        action.setFoo("fooVal");
        action.setBar(100);
        action.setBaz("bazVal");

        BindingUtil.exportProperties(action, getContainer(),
                new BeanDescImpl(POJOActionImpl.class), new MockActionMapping());
        assertEquals("fooVal", getRequest().getAttribute("foo"));
        assertEquals(new Integer(100), getRequest().getSession().getAttribute("bar"));
        assertEquals("bazVal", getRequest().getAttribute("baz"));
    }

    public void testImportPOJOProperty() {
        Object pojoForm = new BeanValidatorForm(new ExportPOJOForm("importPOJO"));
        getRequest().setAttribute("exportPOJOForm", pojoForm);

        ExportPOJOActionImpl action = new ExportPOJOActionImpl();
        BindingUtil.importProperties(action, getContainer(), new BeanDescImpl(
                ExportPOJOActionImpl.class), new MockActionMapping());
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

        Object oldPojo = new BeanValidatorForm(new ExportPOJOForm("oldPOJO"));
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        ExportPOJOActionImpl action = new ExportPOJOActionImpl();
        action.setExportPOJOForm(new ExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(
                ExportPOJOActionImpl.class), new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest()
                .getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean())
                .getInstance();
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

        Object oldPojo = new BeanValidatorForm(new ExportPOJOForm("oldPOJO"));
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        ExportPOJOActionImpl action = new ExportPOJOActionImpl();
        action.setExportPOJOForm(new ExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(ExportPOJOActionImpl.class),
                new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
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

        Object oldPojo = new BeanValidatorForm(new ExportPOJOForm("oldPOJO"));
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        ExportPOJOActionImpl action = new ExportPOJOActionImpl();
        action.setExportPOJOForm(new ExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(ExportPOJOActionImpl.class),
                new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getSession().getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
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

        ExportPOJOActionImpl action = new ExportPOJOActionImpl();
        action.setExportPOJOForm(new ExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(
                ExportPOJOActionImpl.class), new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest()
                .getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean())
                .getInstance();
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

        ExportPOJOAnnotationActionImpl action = new ExportPOJOAnnotationActionImpl();
        action.setExportPOJOAnnotationForm(new ExportPOJOAnnotationForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(
                ExportPOJOAnnotationActionImpl.class), new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getSession().getAttribute(
                "exportPOJOAnnotationForm");
        assertNotNull(beanForm);
        ExportPOJOAnnotationForm resultForm = (ExportPOJOAnnotationForm) ((WrapDynaBean) beanForm
                .getDynaBean()).getInstance();
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

        Object oldPojo = new ExportPOJOForm("oldPOJO");
        getRequest().setAttribute("exportPOJOForm", oldPojo);

        ExportPOJOActionImpl action = new ExportPOJOActionImpl();
        action.setExportPOJOForm(new ExportPOJOForm("newPOJO"));

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(
                ExportPOJOActionImpl.class), new MockActionMapping());
        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest()
                .getAttribute("exportPOJOForm");
        assertNotNull(beanForm);
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean())
                .getInstance();
        assertEquals("newPOJO", resultForm.getMessage());
    }

}