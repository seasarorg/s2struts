package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SetActionConfigCancelledTest extends S2TestCase {

    private MockActionMapping mapping;

    private ActionContext context;

    private ActionCommand command = new SetActionConfigCancelled();

    protected void setUp() throws Exception {
        super.setUp();
        this.include("SetActionConfigCancelledTest.dicon");
    }

    protected void setUpAfterContainerInit() throws Exception {
        this.mapping = new MockActionMapping();
        this.mapping.setValidate(true);

        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setActionConfig(this.mapping);
    }

    public void testCancelledValidate() throws Exception {
        this.mapping.setPath("/test");
        this.getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setCancelAction(this.mapping.getPath(), "1234567890", "TEST");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertFalse(this.context.getActionConfig().getValidate());
    }

    public void testCancelledValidateForImageTag() throws Exception {
        this.mapping.setPath("/test");
        this.getRequest().setParameter("1234567890.y", "");
        S2StrutsContextUtil.setCancelAction(this.mapping.getPath(), "1234567890", null);

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertFalse(this.context.getActionConfig().getValidate());
    }

    public void testCancelledValidateForIndexed() throws Exception {
        this.mapping.setPath("/test");
        this.getRequest().setParameter("1234567890[10]", "TEST");
        S2StrutsContextUtil.setCancelAction(this.mapping.getPath(), "1234567890", "TEST");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertFalse(this.context.getActionConfig().getValidate());
    }

    public void testNotCancelledValidateBecauseNoRequestPrameter() throws Exception {
        this.mapping.setPath("/test");
        S2StrutsContextUtil.setCancelAction(this.mapping.getPath(), "1234567890", "TEST");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertTrue(this.context.getActionConfig().getValidate());
    }

    public void testNotCancelledValidateBecauseDoesNotRegisteredContext() throws Exception {
        this.mapping.setPath("/test");
        this.getRequest().setParameter("1234567890", "TEST");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertTrue(this.context.getActionConfig().getValidate());
    }

}
