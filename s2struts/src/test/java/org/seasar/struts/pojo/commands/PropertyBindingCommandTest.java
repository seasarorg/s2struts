package org.seasar.struts.pojo.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.PojoInvocation;
import org.seasar.struts.pojo.impl.PojoInvocationImpl;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class PropertyBindingCommandTest extends S2TestCase {

    private PojoInvocation invocation;

    protected void setUp() throws Exception {
        super.setUp();
        List commands = new ArrayList();
        commands.add(new PropertyBindingCommand());
        commands.add(new SingleMethodCommand());

        ActionMapping mapping = new MockActionMapping();
        Class actionInterface = null;
        Object actionInstance = null;
        ActionForm form = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;

        this.invocation = new PojoInvocationImpl(commands, mapping, actionInterface, actionInstance, form, request, response);
    }

    public void testExecuteExportPOJOFormFromRequest() throws Exception {
        ActionForm form = new BeanValidatorForm(new TestExportPOJOForm("old"));
        getRequest().setAttribute("exportPOJOForm", form);

        this.invocation.setActionForm(form);
        this.invocation.setRequest(getRequest());
        this.invocation.setActionInterface(TestExportPOJOAction.class);
        this.invocation.setActionInstance(new TestExportPOJOActionImpl());

        this.invocation.getActionMapping().setType(TestExportPOJOAction.class.getName());
        this.invocation.getActionMapping().setName("exportPOJOForm");
        this.invocation.getActionMapping().setScope("request");

        String forward = this.invocation.execute();
        assertEquals("success", forward);

        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getAttribute("exportPOJOForm");
        TestExportPOJOForm resultForm = (TestExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("updated", resultForm.getMessage());
    }

    public void testExecuteExportPOJOFormFromSession() throws Exception {
        ActionForm form = new BeanValidatorForm(new TestExportPOJOForm("old"));
        getRequest().getSession().setAttribute("exportPOJOForm", form);

        this.invocation.setActionForm(form);
        this.invocation.setRequest(getRequest());
        this.invocation.setActionInterface(TestExportPOJOAction.class);
        this.invocation.setActionInstance(new TestExportPOJOActionImpl());

        this.invocation.getActionMapping().setType(TestExportPOJOAction.class.getName());
        this.invocation.getActionMapping().setName("exportPOJOForm");
        this.invocation.getActionMapping().setScope("session");

        String forward = this.invocation.execute();
        assertEquals("success", forward);

        BeanValidatorForm beanForm = (BeanValidatorForm) getRequest().getSession().getAttribute("exportPOJOForm");
        TestExportPOJOForm resultForm = (TestExportPOJOForm) ((WrapDynaBean) beanForm.getDynaBean()).getInstance();
        assertEquals("updated", resultForm.getMessage());
    }

    public void testExecuteExportFormFromRequest() throws Exception {
        ActionForm form = new BeanValidatorForm(new TestExportForm("old"));
        getRequest().setAttribute("exportForm", form);

        this.invocation.setActionForm(form);
        this.invocation.setRequest(getRequest());
        this.invocation.setActionInterface(TestExportAction.class);
        this.invocation.setActionInstance(new TestExportActionImpl());

        this.invocation.getActionMapping().setType(TestExportAction.class.getName());
        this.invocation.getActionMapping().setName("exportForm");
        this.invocation.getActionMapping().setScope("request");

        String forward = this.invocation.execute();
        assertEquals("success", forward);

        TestExportForm resultForm = (TestExportForm) getRequest().getAttribute("exportForm");
        assertEquals("updated", resultForm.getMessage());
    }

    public void testExecuteExportFormFromSession() throws Exception {
        ActionForm form = new BeanValidatorForm(new TestExportForm("old"));
        getRequest().getSession().setAttribute("exportForm", form);

        this.invocation.setActionForm(form);
        this.invocation.setRequest(getRequest());
        this.invocation.setActionInterface(TestExportAction.class);
        this.invocation.setActionInstance(new TestExportActionImpl());

        this.invocation.getActionMapping().setType(TestExportAction.class.getName());
        this.invocation.getActionMapping().setName("exportForm");
        this.invocation.getActionMapping().setScope("session");

        String forward = this.invocation.execute();
        assertEquals("success", forward);

        TestExportForm resultForm = (TestExportForm) getRequest().getSession().getAttribute("exportForm");
        assertEquals("updated", resultForm.getMessage());
    }

}
