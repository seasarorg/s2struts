package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.form.S2BeanValidatorForm;
import org.seasar.struts.pojo.processor.TestActionForm;
import org.seasar.struts.pojo.processor.TestPojoForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class WrapS2BeanValidatorFormTest extends S2TestCase {

    private MockActionMapping mapping;

    private ActionContext context;

    private ActionCommand command = new WrapS2BeanValidatorForm();

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void setUpAfterContainerInit() throws Exception {
        this.mapping = new MockActionMapping();
        this.mapping.setModuleConfig(new ModuleConfigImpl());
        this.mapping.setName("beanForm");

        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setActionConfig(this.mapping);
    }

    public void testWrap() throws Exception {
        ActionForm form = new BeanValidatorForm(new TestPojoForm());
        this.context.setActionForm(form);
        this.getRequest().getSession().setAttribute(this.mapping.getAttribute(), form);

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        Object bean = this.context.getActionForm();
        assertTrue(bean instanceof S2BeanValidatorForm);

        bean = this.getRequest().getSession().getAttribute(this.mapping.getAttribute());
        assertTrue(bean instanceof S2BeanValidatorForm);
    }

    public void testNoWrap() throws Exception {
        ActionForm form = new TestActionForm();
        this.context.setActionForm(form);
        this.getRequest().getSession().setAttribute(this.mapping.getAttribute(), form);

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        Object bean = this.context.getActionForm();
        assertFalse(bean instanceof S2BeanValidatorForm);

        bean = this.getRequest().getSession().getAttribute(this.mapping.getAttribute());
        assertFalse(bean instanceof S2BeanValidatorForm);
    }

}
