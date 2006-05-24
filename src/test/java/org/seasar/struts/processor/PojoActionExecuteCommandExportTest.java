package org.seasar.struts.processor;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.ExportAction;
import org.seasar.struts.action.ExportActionImpl;
import org.seasar.struts.action.ExportForm;
import org.seasar.struts.action.ExportPOJOAction;
import org.seasar.struts.action.ExportPOJOActionImpl;
import org.seasar.struts.action.ExportPOJOForm;
import org.seasar.struts.mock.MockActionMapping;

/**
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class PojoActionExecuteCommandExportTest extends S2TestCase {
	
	private PojoActionExecuteCommand pojoActionExecuteCommand;

    protected void setUp() throws Exception {
        super.setUp();
        include("s2struts_old.dicon");
        include("PojoActionExecuteCommandExportTest.dicon");
    }

    public void testExecuteExportPOJOFormFromRequest() throws Exception {
        ExportPOJOAction action = new ExportPOJOActionImpl();
        Object form = new BeanValidatorForm(new ExportPOJOForm("old"));
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ExportPOJOAction.class.getName());
        mapping.setName("exportPOJOForm");
        mapping.setScope("request");
        
        getRequest().setAttribute("exportPOJOForm", form);

        String forward = pojoActionExecuteCommand.execute(getRequest(),
				getResponse(), action, form, mapping);
        assertEquals("success", forward);

        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getAttribute("exportPOJOForm");
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("updated", resultForm.getMessage());
    }
    
    public void testExecuteExportPOJOFormFromSession() throws Exception {
        ExportPOJOAction action = new ExportPOJOActionImpl();
        Object form = new BeanValidatorForm(new ExportPOJOForm("old"));
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ExportPOJOAction.class.getName());
        mapping.setName("exportPOJOForm");
        mapping.setScope("session");
        
        getRequest().getSession().setAttribute("exportPOJOForm", form);

        String forward = pojoActionExecuteCommand.execute(getRequest(),
				getResponse(), action, form, mapping);
        assertEquals("success", forward);

        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getSession().getAttribute("exportPOJOForm");
        ExportPOJOForm resultForm = (ExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("updated", resultForm.getMessage());
    }
    
    public void testExecuteExportFormFromRequest() throws Exception {
        ExportAction action = new ExportActionImpl();
        Object form = new BeanValidatorForm(new ExportForm("old"));
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ExportAction.class.getName());
        mapping.setName("exportForm");
        mapping.setScope("request");
        
        getRequest().setAttribute("exportForm", form);

        String forward = pojoActionExecuteCommand.execute(getRequest(),
				getResponse(), action, form, mapping);
        assertEquals("success", forward);

        ExportForm resultForm = (ExportForm) getRequest().getAttribute("exportForm");
        assertEquals("updated", resultForm.getMessage());
    }

    public void testExecuteExportFormFromSession() throws Exception {
        ExportAction action = new ExportActionImpl();
        Object form = new BeanValidatorForm(new ExportForm("old"));
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(ExportAction.class.getName());
        mapping.setName("exportForm");
        mapping.setScope("session");
        
        getRequest().getSession().setAttribute("exportForm", form);

        String forward = pojoActionExecuteCommand.execute(getRequest(),
				getResponse(), action, form, mapping);
        assertEquals("success", forward);

        ExportForm resultForm = (ExportForm) getRequest().getSession().getAttribute("exportForm");
        assertEquals("updated", resultForm.getMessage());
    }
    
    // TODO リクエスト セッション フォーム

}
