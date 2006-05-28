package org.seasar.struts.pojo.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.PojoCommand;
import org.seasar.struts.pojo.PojoInvocation;
import org.seasar.struts.pojo.PojoInvocationImpl;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class DispatchCommandTest extends S2TestCase {

    private PojoInvocation invocation;

    protected void setUp() throws Exception {
        super.setUp();
        List commands = new ArrayList();
        commands.add(new DispatchCommand());
        commands.add(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                return "notExecute";
            }
        });

        ActionMapping mapping = new MockActionMapping();
        Class actionInterface = null;
        Object actionInstance = null;
        ActionForm form = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;

        this.invocation = new PojoInvocationImpl(commands, mapping, actionInterface,
                actionInstance, form, request, response);
    }

    public void testExecute() {
        this.invocation.setActionInterface(TestMultiMethodAction.class);
        this.invocation.setActionInstance(new TestMultiMethodActionImpl());
        this.invocation.getActionMapping().setParameter("method");
        this.invocation.setRequest(getRequest());
        getRequest().setParameter("method", "execute");

        String result = this.invocation.execute();
        assertEquals("success", result);
    }

    public void testNotExecuteBecauseActionIsSingleMethodAction() {
        this.invocation.setActionInterface(TestSingleMethodAction.class);
        this.invocation.setActionInstance(new TestSingleMethodActionImpl());
        this.invocation.getActionMapping().setParameter("method");
        this.invocation.setRequest(getRequest());
        getRequest().setParameter("method", "execute");

        String result = this.invocation.execute();
        assertEquals("notExecute", result);
    }

    public void testNotExecuteBecauseParameterIsNull() {
        this.invocation.setActionInterface(TestMultiMethodAction.class);
        this.invocation.setActionInstance(new TestMultiMethodActionImpl());
        this.invocation.setRequest(getRequest());
        getRequest().setParameter("method", "execute");

        String result = this.invocation.execute();
        assertEquals("notExecute", result);
    }

    public void testNotExecuteBecauseNoSetRequestParameter() {
        this.invocation.setActionInterface(TestMultiMethodAction.class);
        this.invocation.setActionInstance(new TestMultiMethodActionImpl());
        this.invocation.getActionMapping().setParameter("method");
        this.invocation.setRequest(getRequest());

        String result = this.invocation.execute();
        assertEquals("notExecute", result);
    }

    public void testNotExecuteBecauseUndefinedMethod() {
        this.invocation.setActionInterface(TestSingleMethodAction.class);
        this.invocation.setActionInstance(new TestSingleMethodActionImpl());
        this.invocation.getActionMapping().setParameter("method");
        this.invocation.setRequest(getRequest());
        getRequest().setParameter("method", "undefinedMethod");

        String result = this.invocation.execute();
        assertEquals("notExecute", result);
    }

}
