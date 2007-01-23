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
public class SetActionConfigInputTest extends S2TestCase {

    private MockActionMapping mapping;

    private ActionContext context;

    private ActionCommand command = new SetActionConfigInput();

    protected void setUp() throws Exception {
        super.setUp();
        this.include("SetActionConfigInputTest.dicon");
    }

    protected void setUpAfterContainerInit() throws Exception {
        this.mapping = new MockActionMapping();

        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setActionConfig(this.mapping);
    }

    public void testReplacePath() throws Exception {
        S2StrutsContextUtil.setPath("/previous.html");
        S2StrutsContextUtil.setPath("/current.html");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertEquals("/previous.html", this.context.getActionConfig().getInput());
    }

    public void testNotReplacePathBecauseNoSetPreviousPath() throws Exception {
        S2StrutsContextUtil.setPath("/current.html");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertNull(this.context.getActionConfig().getInput());
    }

    public void testNotReplacePathBecauseSetInput() throws Exception {
        S2StrutsContextUtil.setPath("/previous.html");
        S2StrutsContextUtil.setPath("/current.html");
        this.mapping.setInput("/input.html");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertEquals("/input.html", this.context.getActionConfig().getInput());
    }

}
