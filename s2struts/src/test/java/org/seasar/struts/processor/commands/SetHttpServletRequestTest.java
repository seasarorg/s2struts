package org.seasar.struts.processor.commands;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts.chain.commands.ActionCommandBase;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.processor.commands.SetHttpServletRequest;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SetHttpServletRequestTest extends S2TestCase {

    public void testExecute() throws Exception {
        ActionContext context = new ServletActionContext(this.getServletContext(), new HttpServletRequestWrapper(this
                .getRequest()), this.getResponse());
        ActionCommandBase command = new SetHttpServletRequest();

        boolean result = command.execute(context);

        assertFalse(result);
        assertEquals(HttpServletRequestWrapper.class, this.getContainer().getExternalContext().getRequest().getClass());
    }

}
