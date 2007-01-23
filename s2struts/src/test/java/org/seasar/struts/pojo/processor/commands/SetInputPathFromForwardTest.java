package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.Globals;
import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SetInputPathFromForwardTest extends S2TestCase {

    private MockActionMapping mapping;

    private ForwardConfig forward;

    private ActionContext context;

    private ActionCommand command = new SetInputPathFromForward();

    protected void setUp() throws Exception {
        super.setUp();
        this.include("SetInputPathFromForwardTest.dicon");
    }

    protected void setUpAfterContainerInit() throws Exception {
        this.mapping = new MockActionMapping();

        this.forward = new ForwardConfig();

        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setActionConfig(this.mapping);
        this.context.setForwardConfig(this.forward);

        this.getRequest().setAttribute(Globals.MODULE_KEY, new ModuleConfigImpl());
    }

    public void testExecute() throws Exception {
        this.forward.setPath("/request.html");
        S2StrutsContextUtil.setPath("/previous.html");
        S2StrutsContextUtil.setPath("/current.html");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertEquals("/request.html", S2StrutsContextUtil.getCurrentInputPath());
        assertEquals("/current.html", S2StrutsContextUtil.getPreviousInputPath());
    }

    public void testExecuteForwardPathNull() throws Exception {
        this.forward.setPath(null);
        S2StrutsContextUtil.setPath("/previous.html");
        S2StrutsContextUtil.setPath("/current.html");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        assertEquals("/current.html", S2StrutsContextUtil.getCurrentInputPath());
        assertEquals("/previous.html", S2StrutsContextUtil.getPreviousInputPath());
    }

}
