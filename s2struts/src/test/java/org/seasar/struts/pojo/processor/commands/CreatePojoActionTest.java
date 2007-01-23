package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.PojoProcessAction;
import org.seasar.struts.pojo.processor.TestAction;
import org.seasar.struts.pojo.processor.TestPojoAction;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class CreatePojoActionTest extends S2TestCase {

    private MockActionMapping mapping;

    private ActionContext context;

    private ActionCommand command = new CreatePojoAction();

    protected void setUp() throws Exception {
        super.setUp();
        this.include("CreatePojoActionTest.dicon");
    }

    protected void setUpAfterContainerInit() throws Exception {
        this.mapping = new MockActionMapping();

        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setFormValid(Boolean.TRUE);
        this.context.setActionConfig(this.mapping);
    }

    public void testCreatePojoAction() throws Exception {
        this.mapping.setType(TestPojoAction.class.getName());

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertEquals(PojoProcessAction.class, this.context.getAction().getClass());
    }

    public void testNoCreatePojoActionBecauseTypeIsNull() throws Exception {
        this.mapping.setType(null);

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertNull(this.context.getAction());
    }

    public void testNoCreatePojoActionBecauseTypeIsAction() throws Exception {
        this.mapping.setType(TestAction.class.getName());

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertNull(this.context.getAction());
    }

}
