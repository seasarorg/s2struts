package org.seasar.struts.interceptors;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.interceptors.ProcessPojoFormInterceptor.SerializeBeanValidatorForm;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.processor.S2RequestProcessor;

/**
 * @author Katsuhiko Nagashima
 */
public class ProcessPojoFormInterceptorTest extends S2TestCase {

    private S2RequestProcessor requestProcessor;

    public ProcessPojoFormInterceptorTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include("s2struts.dicon");
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

        requestProcessor.init(null, config);
    }

    public void testNewActionForm() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testActionForm");
        mapping.setScope("session");

        ActionForm form = requestProcessor.processActionForm(getRequest(),
                getResponse(), mapping);

        assertFalse(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testActionForm");
        assertEquals(scopeForm, form);

        TestActionForm resultForm = (TestActionForm) form;
        assertEquals("new", resultForm.getMsg());
    }

    public void testReuseActionForm() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testActionForm");
        mapping.setScope("session");

        TestActionForm actionForm = new TestActionForm("reuse");
        getRequest().getSession().setAttribute("testActionForm", actionForm);

        ActionForm form = requestProcessor.processActionForm(getRequest(),
                getResponse(), mapping);

        assertFalse(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testActionForm");
        assertEquals(scopeForm, form);

        TestActionForm resultForm = (TestActionForm) form;
        assertEquals("reuse", resultForm.getMsg());
    }
    
    public void testNewPojoForm() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testPojoForm");
        mapping.setScope("session");

        ActionForm form = requestProcessor.processActionForm(getRequest(),
                getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("new", resultForm.getMsg());
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

        ActionForm form = requestProcessor.processActionForm(getRequest(),
                getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("reuse", resultForm.getMsg());
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

        ActionForm form = requestProcessor.processActionForm(getRequest(),
                getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("extend:reuse", resultForm.getMsg());
    }

    public void testReuseNoWrapPojoFormFromSession() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testPojoForm");
        mapping.setScope("session");
        
        TestPojoForm pojoForm = new TestPojoForm("reuse");
        getRequest().getSession().setAttribute("testPojoForm", pojoForm);
        
        ActionForm form = requestProcessor.processActionForm(getRequest(),
                getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("reuse", resultForm.getMsg());
    }
    
    public void testNotReusePojoFormFromSession() throws Exception {
        initRequestProcessor();
        MockActionMapping mapping = new MockActionMapping();
        mapping.setName("testPojoForm");
        mapping.setScope("session");
        
        Object pojoForm = "notPojoForm";
        getRequest().getSession().setAttribute("testPojoForm", pojoForm);
        
        ActionForm form = requestProcessor.processActionForm(getRequest(),
                getResponse(), mapping);

        assertTrue(BeanValidatorForm.class.isAssignableFrom(form.getClass()));

        Object scopeForm = getRequest().getSession().getAttribute("testPojoForm");
        assertEquals(scopeForm, form);

        TestPojoForm resultForm = (TestPojoForm) ((WrapDynaBean) ((BeanValidatorForm) form)
                .getDynaBean()).getInstance();
        assertEquals("new", resultForm.getMsg());
    }

}