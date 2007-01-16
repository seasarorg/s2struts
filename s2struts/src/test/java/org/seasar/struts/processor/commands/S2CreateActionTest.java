package org.seasar.struts.processor.commands;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.commands.ActionCommandBase;
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

    protected void setUp() throws Exception {
        super.setUp();
        include("S2CreateActionTest.dicon");
    }

    public void testExecute() throws Exception {
        ActionConfig config = new ActionMapping();
        config.setType(MockAction.class.getName()); // Dummy Type.

        ActionContext context = new ServletActionContext(this.getServletContext(), this.getRequest(), this
                .getResponse());
        context.setFormValid(Boolean.TRUE);
        context.setActionConfig(config);

        ActionCommandBase command = new S2CreateAction();

        boolean result = command.execute(context);

        assertFalse(result);
        assertEquals(MockAction.class, context.getAction().getClass());
    }

}
