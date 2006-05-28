package org.seasar.struts.pojo.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.pojo.PojoCommand;
import org.seasar.struts.pojo.PojoInvocation;
import org.seasar.struts.pojo.PojoInvocationImpl;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SingleMethodCommandTest extends S2TestCase {

    private PojoInvocation invocation;

    protected void setUp() throws Exception {
        super.setUp();
        List commands = new ArrayList();
        commands.add(new SingleMethodCommand());
        commands.add(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                return "notExecute";
            }
        });

        ActionMapping mapping = null;
        Class actionInterface = null;
        Object actionInstance = null;
        ActionForm form = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;

        this.invocation = new PojoInvocationImpl(commands, mapping, actionInterface,
                actionInstance, form, request, response);
    }

    public void testExecute() {
        this.invocation.setActionInterface(TestSingleMethodAction.class);
        this.invocation.setActionInstance(new TestSingleMethodActionImpl());

        String result = this.invocation.execute();
        assertEquals("success", result);
    }

    public void testNotExecuteBecauseActionIsMultiMethodAction() {
        this.invocation.setActionInterface(TestMultiMethodAction.class);
        this.invocation.setActionInstance(new TestMultiMethodActionImpl());

        String result = this.invocation.execute();
        assertEquals("notExecute", result);
    }

}
