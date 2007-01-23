package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.MethodBindingAction;
import org.seasar.struts.util.S2StrutsContextUtil;

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
        this.mapping.setType(MethodBindingAction.class.getName()); // Dummy Type.

        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setFormValid(Boolean.TRUE);
        this.context.setActionConfig(this.mapping);
    }

    public void testCreateMethodBinding() throws Exception {
        this.mapping.setPath("/test");
        this.getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression(this.mapping.getPath(), "1234567890", "TEST",
                "#{bindingAction.exe}");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertEquals(MethodBindingAction.class, this.context.getAction().getClass());
    }

    public void testCreateMethodBindingForImageTag() throws Exception {
        this.mapping.setPath("/test");
        this.getRequest().setParameter("1234567890.y", "");
        S2StrutsContextUtil.setMethodBindingExpression(this.mapping.getPath(), "1234567890", null,
                "#{bindingAction.exe}");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertEquals(MethodBindingAction.class, this.context.getAction().getClass());
    }

    public void testCreateMethodBindingForIndexed() throws Exception {
        this.mapping.setPath("/test");
        this.getRequest().setParameter("1234567890[10]", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression(this.mapping.getPath(), "1234567890", "TEST",
                "#{bindingAction.exe}");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertEquals(MethodBindingAction.class, this.context.getAction().getClass());
    }

    public void testCannotCreateMethodBindingBecauseNoRequestPrameter() throws Exception {
        this.mapping.setPath("/test");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertNull(this.context.getAction());
    }

    public void testCannotCreateMethodBindingBecauseExpressionDoesNotRegistered() throws Exception {
        this.mapping.setPath("/test");
        this.getRequest().setParameter("1234567890", "TEST");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertNull(this.context.getAction());
    }

}
