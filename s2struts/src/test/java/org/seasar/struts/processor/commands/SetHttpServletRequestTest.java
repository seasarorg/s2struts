package org.seasar.struts.processor.commands;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SetHttpServletRequestTest extends S2TestCase {

    private ActionCommand command = new SetHttpServletRequest();

    public void testExecute() throws Exception {
        ActionContext context = new ServletActionContext(this.getServletContext(), new HttpServletRequestWrapper(this
                .getRequest()), this.getResponse());

        boolean result = this.command.execute(context);

        assertFalse(result);
        assertEquals(HttpServletRequestWrapper.class, this.getContainer().getExternalContext().getRequest().getClass());
    }

}
