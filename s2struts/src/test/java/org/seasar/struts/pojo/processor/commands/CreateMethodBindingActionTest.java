package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.Constants;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.MethodBindingAction;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class CreateMethodBindingActionTest extends S2TestCase {

    private MockActionMapping mapping;

    private ActionContext context;

    private ActionCommand command = new CreateMethodBindingAction();

    protected void setUp() throws Exception {
        super.setUp();
        this.include("CreateMethodBindingActionTest.dicon");
    }

    protected void setUpAfterContainerInit() throws Exception {
        this.mapping = new MockActionMapping();
        this.mapping.setType(MethodBindingAction.class.getName()); // Dummy
        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setFormValid(Boolean.TRUE);
        this.context.setActionConfig(this.mapping);
    }

    public void testCreateMethodBinding() throws Exception {
        this.getRequest().setAttribute(Constants.ACTION_EXPRESSION_KEY, "#{bindingAction.exe}");
        boolean result = this.command.execute(this.context);
        assertFalse(result);
        assertEquals(MethodBindingAction.class, this.context.getAction().getClass());
    }

    public void testCreateMethodBindingForIndexed() throws Exception {
        this.getRequest().setAttribute(Constants.ACTION_EXPRESSION_KEY, "#{bindingAction.exe}[10]");
        boolean result = this.command.execute(this.context);
        assertFalse(result);
        assertEquals(MethodBindingAction.class, this.context.getAction().getClass());
    }

    public void testCannotCreateMethodBindingBecauseNoRequestPrameter() throws Exception {
        boolean result = this.command.execute(this.context);
        assertFalse(result);
        assertNull(this.context.getAction());
    }

}
