package org.seasar.struts.pojo.processor;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.processor.ProcessPojoFormInterceptor.SerializeBeanValidatorForm;
import org.seasar.struts.processor.ExternalRequestProcessor;

/**
 * @author Katsuhiko Nagashima
 */
public class ProcessPojoFormInterceptorTest extends S2TestCase {

    private ExternalRequestProcessor processor;

    public ProcessPojoFormInterceptorTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("ProcessPojoFormInterceptorTest.dicon");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private void initRequestProcessor() throws Exception {
        ModuleConfig config = new ModuleConfigImpl("");

        FormBeanConfig pojoFormConfig = new FormBeanConfig();
        pojoFormConfig.setName("testPojoForm");
        pojoFormConfig.setType(TestPojoForm.class.getName());
        config.addFormBeanConfig(pojoFormConfig);

        FormBeanConfig actionFormConfig = new FormBeanConfig();
        actionFormConfig.setName("testActionForm");
        actionFormConfig.setType(TestActionForm.class.getName());
        config.addFormBeanConfig(actionFormConfig);

        processor.init(null, config);
    }

    public void testNewActionForm() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testActionForm");
        mapping.setScope("session");

        ActionForm form = processor.processActionForm(getRequest(), getResponse(), mapping);

        assertFalse(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testActionForm");
        assertEquals(scopeForm, form);

        TestActionForm resultForm = (TestActionForm) form;
        assertEquals("new", resultForm.getMessage());
    }

    public void testReuseActionForm() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testActionForm");
        mapping.setScope("session");

        TestActionForm actionForm = new TestActionForm("reuse");
        getRequest().getSession().setAttribute("testActionForm", actionForm);

        ActionForm form = processor.processActionForm(getRequest(), getResponse(), mapping);

        assertFalse(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testActionForm");
        assertEquals(scopeForm, form);

        TestActionForm resultForm = (TestActionForm) form;
        assertEquals("reuse", resultForm.getMessage());
    }

    public void testNewPojoForm() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testPojoForm");
        mapping.setScope("session");

        ActionForm form = processor.processActionForm(getRequest(), getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("new", resultForm.getMessage());
    }

    public void testReusePojoForm() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testPojoForm");
        mapping.setScope("session");

        TestPojoForm pojoForm = new TestPojoForm("reuse");
        BeanValidatorForm beanForm = new BeanValidatorForm(pojoForm);
        getRequest().getSession().setAttribute("testPojoForm",
                new SerializeBeanValidatorForm(beanForm, null));

        ActionForm form = processor.processActionForm(getRequest(), getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("reuse", resultForm.getMessage());
    }

    public void testReuseExetendPojoForm() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testPojoForm");
        mapping.setScope("session");

        TestPojoForm pojoForm = new TestExtendPojoForm("reuse");
        BeanValidatorForm beanForm = new BeanValidatorForm(pojoForm);
        getRequest().getSession().setAttribute("testPojoForm",
                new SerializeBeanValidatorForm(beanForm, null));

        ActionForm form = processor.processActionForm(getRequest(), getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("extend:reuse", resultForm.getMessage());
    }

    public void testReuseNoWrapPojoFormFromSession() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testPojoForm");
        mapping.setScope("session");

        TestPojoForm pojoForm = new TestPojoForm("reuse");
        getRequest().getSession().setAttribute("testPojoForm", pojoForm);

        ActionForm form = processor.processActionForm(getRequest(), getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("reuse", resultForm.getMessage());
    }

    public void testNotReusePojoFormFromSession() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testPojoForm");
        mapping.setScope("session");

        Object pojoForm = "notPojoForm";
        getRequest().getSession().setAttribute("testPojoForm", pojoForm);

        ActionForm form = processor.processActionForm(getRequest(), getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("new", resultForm.getMessage());
    }

}