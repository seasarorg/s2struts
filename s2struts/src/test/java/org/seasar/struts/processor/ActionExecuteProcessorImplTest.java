package org.seasar.struts.processor;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.struts.action.ExportAction;
import org.seasar.struts.action.ExportActionImpl;
import org.seasar.struts.action.ExportForm;
import org.seasar.struts.action.ExportPOJOAction;
import org.seasar.struts.action.ExportPOJOActionImpl;
import org.seasar.struts.action.ExportPOJOForm;
import org.seasar.struts.action.POJOAction;
import org.seasar.struts.action.POJOActionImpl;
import org.seasar.struts.action.ReturnNullPOJOAction;
import org.seasar.struts.action.ReturnNullPOJOActionImpl;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class ActionExecuteProcessorImplTest extends S2TestCase {
    private ActionExecuteProcessor actionExecuteProcessor;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        include("s2struts.dicon");
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for ActionExecuteProcessorImplTest.
     * 
     * @param arg0
     */
    public ActionExecuteProcessorImplTest(String arg0) {
        super(arg0);
    }

    public void setUpProcessActionExecute() {
        include("ActionExecuteProcessorImplTest.dicon");
    }

    public void testProcessActionExecute() throws Exception {
        POJOActionImpl action = new POJOActionImpl();
        Object form = null;
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(POJOAction.class.getName());

        ActionForward actionForward = actionExecuteProcessor.processActionExecute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success", actionForward.getName());
    }

    public void setUpProcessActionExecuteReturnNull() {
        include("ActionExecuteProcessorImplReturnNullTest.dicon");
    }

    public void testProcessActionExecuteReturnNull() throws Exception {
        ReturnNullPOJOAction action = new ReturnNullPOJOActionImpl();
        Object form = null;
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ReturnNullPOJOAction.class.getName());

        ActionForward actionForward = actionExecuteProcessor.processActionExecute(getRequest(), getResponse(), action,
                form, mapping);
        assertNull(actionForward);
    }
    
    public void setUpProcessActionExecuteExportPOJOFormFromRequest() {
        include("ActionExecuteProcessorImplExportPOJOFormTest.dicon");
    }

    public void testProcessActionExecuteExportPOJOFormFromRequest() throws Exception {
        ExportPOJOAction action = new ExportPOJOActionImpl();
        Object form = new BeanValidatorForm(new ExportPOJOForm("old"));
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ExportPOJOAction.class.getName());
        mapping.setName("exportPOJOForm");
        mapping.setScope("request");
        
        getRequest().setAttribute("exportPOJOForm", form);

        ActionForward actionForward = actionExecuteProcessor.processActionExecute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success", actionForward.getName());

        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getAttribute("exportPOJOForm");
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("updated", resultForm.getMessage());
    }
    
    public void setUpProcessActionExecuteExportPOJOFormFromSession() {
        include("ActionExecuteProcessorImplExportPOJOFormTest.dicon");
    }

    public void testProcessActionExecuteExportPOJOFormFromSession() throws Exception {
        ExportPOJOAction action = new ExportPOJOActionImpl();
        Object form = new BeanValidatorForm(new ExportPOJOForm("old"));
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ExportPOJOAction.class.getName());
        mapping.setName("exportPOJOForm");
        mapping.setScope("session");
        
        getRequest().getSession().setAttribute("exportPOJOForm", form);

        ActionForward actionForward = actionExecuteProcessor.processActionExecute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success", actionForward.getName());

        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getSession().getAttribute("exportPOJOForm");
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("updated", resultForm.getMessage());
    }
    
    public void setUpProcessActionExecuteExportFormFromRequest() {
        include("ActionExecuteProcessorImplExportFormTest.dicon");
    }

    public void testProcessActionExecuteExportFormFromRequest() throws Exception {
        ExportAction action = new ExportActionImpl();
        Object form = new BeanValidatorForm(new ExportForm("old"));
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ExportAction.class.getName());
        mapping.setName("exportForm");
        mapping.setScope("request");
        
        getRequest().setAttribute("exportForm", form);

        ActionForward actionForward = actionExecuteProcessor.processActionExecute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success", actionForward.getName());

        ExportForm resultForm = (ExportForm) getRequest().getAttribute("exportForm");
        assertEquals("updated", resultForm.getMessage());
    }

    public void setUpProcessActionExecuteExportFormFromSession() {
        include("ActionExecuteProcessorImplExportFormTest.dicon");
    }

    public void testProcessActionExecuteExportFormFromSession() throws Exception {
        ExportAction action = new ExportActionImpl();
        Object form = new BeanValidatorForm(new ExportForm("old"));
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ExportAction.class.getName());
        mapping.setName("exportForm");
        mapping.setScope("session");
        
        getRequest().getSession().setAttribute("exportForm", form);

        ActionForward actionForward = actionExecuteProcessor.processActionExecute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success", actionForward.getName());

        ExportForm resultForm = (ExportForm) getRequest().getSession().getAttribute("exportForm");
        assertEquals("updated", resultForm.getMessage());
    }
    
    public void setUpMethodBinding() throws Exception {
        include("MethodBindingTest.dicon");
    }
    
    public void testMethodBinding() throws Exception {
        getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.exe}");
        
        Object action = null;
        Object form = null;
        ActionMapping mapping = new MockActionMapping();
        
        ActionForward actionForward = actionExecuteProcessor.processActionExecute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success", actionForward.getName());
    }
    
    public void setUpMethodBindingDownload() throws Exception {
        include("MethodBindingTest.dicon");
    }
    
    public void testMethodBindingDownload() throws Exception {
        getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.download}");
        
        Object action = null;
        Object form = null;
        ActionMapping mapping = new MockActionMapping();
        
        ActionForward actionForward = actionExecuteProcessor.processActionExecute(getRequest(), getResponse(), action,
                form, mapping);
        assertNull(actionForward);
    }
    
    public void testMethodBindingNoRegisteredComponent() throws Exception {
        getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("1234567890", "TEST", "#{bindingAction.exe}");
        
        Object action = null;
        Object form = null;
        ActionMapping mapping = new MockActionMapping();
        
        try {
            ActionForward actionForward = actionExecuteProcessor
                    .processActionExecute(getRequest(), getResponse(), action, form, mapping);
            fail();
        } catch (ComponentNotFoundRuntimeException e) {
            // success
        }
    }

    // TODO リクエスト セッション フォーム

}