package org.seasar.struts.processor.commands;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.processor.MockAction;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class S2CreateActionTest extends S2TestCase {

    private ActionCommand command = new S2CreateAction();

    protected void setUp() throws Exception {
        super.setUp();
        this.include("S2CreateActionTest.dicon");
    }

    public void testExecute() throws Exception {
        ActionConfig config = new ActionMapping();
        config.setType(MockAction.class.getName()); // Dummy Type.

        ActionContext context = new ServletActionContext(this.getServletContext(), this.getRequest(), this
                .getResponse());
        context.setFormValid(Boolean.TRUE);
        context.setActionConfig(config);

        boolean result = this.command.execute(context);

        assertFalse(result);
        assertEquals(MockAction.class, context.getAction().getClass());
    }

}
