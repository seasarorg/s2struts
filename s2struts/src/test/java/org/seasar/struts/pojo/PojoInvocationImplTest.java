package org.seasar.struts.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class PojoInvocationImplTest extends S2TestCase {

    private final List traceMessage = new ArrayList();

    public void testExecute() throws Exception {
        List commands = new ArrayList();
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Class actionInterface = null;
        Object actionInstance = null;
        ActionForm form = null;
        ActionMapping mapping = null;

        commands.add(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                traceMessage.add("one");
                return invocation.execute();
            }
        });
        commands.add(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                traceMessage.add("two");
                return "success";
            }
        });
        commands.add(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                traceMessage.add("three");
                return "error";
            }
        });
        PojoInvocation invocation = new PojoInvocationImpl(commands, mapping, actionInterface,
                actionInstance, form, request, response);

        String result = invocation.execute();

        assertEquals("success", result);
        assertEquals(2, traceMessage.size());
        assertEquals("one", traceMessage.get(0));
        assertEquals("two", traceMessage.get(1));
    }

    public void testNotExecute() throws Exception {
        List commands = new ArrayList();
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Class actionInterface = null;
        Object actionInstance = null;
        ActionForm form = null;
        ActionMapping mapping = null;

        commands.add(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                traceMessage.add("one");
                return invocation.execute();
            }
        });
        commands.add(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                traceMessage.add("two");
                return invocation.execute();
            }
        });
        commands.add(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                traceMessage.add("three");
                return invocation.execute();
            }
        });
        PojoInvocation invocation = new PojoInvocationImpl(commands, mapping, actionInterface,
                actionInstance, form, request, response);

        try {
            invocation.execute();
            fail();
        } catch (RuntimeException e) {
            assertEquals(3, traceMessage.size());
            assertEquals("one", traceMessage.get(0));
            assertEquals("two", traceMessage.get(1));
            assertEquals("three", traceMessage.get(2));
        }

    }

    public void testEmptyCommand() throws Exception {
        List commands = new ArrayList();
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Class actionInterface = null;
        Object actionInstance = null;
        ActionForm form = null;
        ActionMapping mapping = null;

        PojoInvocation invocation = new PojoInvocationImpl(commands, mapping, actionInterface,
                actionInstance, form, request, response);

        try {
            invocation.execute();
            fail();
        } catch (RuntimeException e) {
        }
    }

}
