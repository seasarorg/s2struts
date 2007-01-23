package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.processor.TestActionForm;
import org.seasar.struts.pojo.processor.TestPojoForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class WrapActionFormTest extends S2TestCase {

    private MockActionMapping mapping;

    private FormBeanConfig beanConfig;

    private ActionContext context;

    private ActionCommand command = new WrapActionForm();

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void setUpAfterContainerInit() throws Exception {
        this.beanConfig = new FormBeanConfig();
        this.beanConfig.setName("beanForm");

        this.mapping = new MockActionMapping();
        this.mapping.setModuleConfig(new ModuleConfigImpl());
        this.mapping.setName("beanForm");
        this.mapping.getModuleConfig().addFormBeanConfig(this.beanConfig);

        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setActionConfig(this.mapping);
    }

    public void testPojoForm() throws Exception {
        this.beanConfig.setType(TestPojoForm.class.getName());
        this.getRequest().getSession().setAttribute(this.mapping.getAttribute(), new TestPojoForm());

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        Object bean = this.getRequest().getSession().getAttribute(this.mapping.getAttribute());
        assertTrue(bean instanceof BeanValidatorForm);
    }

    public void testActionForm() throws Exception {
        this.beanConfig.setType(TestActionForm.class.getName());
        this.getRequest().getSession().setAttribute(this.mapping.getAttribute(), new TestActionForm());

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        Object bean = this.getRequest().getSession().getAttribute(this.mapping.getAttribute());
        assertFalse(bean instanceof BeanValidatorForm);
        assertTrue(bean instanceof ActionForm);
    }

    public void testActionConfigNameNull() throws Exception {
        this.mapping.setName(null);

        this.beanConfig.setType(TestPojoForm.class.getName());
        this.getRequest().getSession().setAttribute(this.mapping.getAttribute(), new TestPojoForm());

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        Object bean = this.getRequest().getSession().getAttribute(this.mapping.getAttribute());
        assertTrue(bean instanceof TestPojoForm);
    }

    public void testNotFoundForwardConfig() throws Exception {
        this.mapping.setName("notFoundForm");

        this.beanConfig.setType(TestPojoForm.class.getName());
        this.getRequest().getSession().setAttribute(this.mapping.getAttribute(), new TestPojoForm());

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        Object bean = this.getRequest().getSession().getAttribute(this.mapping.getAttribute());
        assertTrue(bean instanceof TestPojoForm);
    }

    public void testSessionAttributeNull() throws Exception {
        this.beanConfig.setType(TestPojoForm.class.getName());

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        Object bean = this.getRequest().getSession().getAttribute(this.mapping.getAttribute());
        assertNull(bean);
    }

}
