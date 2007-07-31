package org.seasar.struts.processor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;

/**
 * @author Katsuhiko Nagashima
 */
public class ActionExecuteProcessorImplTest extends S2TestCase {

    private final List traceMessage = new ArrayList();

    private Object action = null;

    private Object form = null;

    private ActionMapping mapping = new MockActionMapping();

    private ActionExecuteProcessorImpl processor = new ActionExecuteProcessorImpl();

    public void testExecute() throws Exception {
        processor.addActionCommand(new ActionCommand() {
            public String execute(HttpServletRequest request,
                    HttpServletResponse response, Object action, Object form,
                    ActionMapping mapping) {

                traceMessage.add("one");
                return NOT_EXECUTE;
            }
        });
        processor.addActionCommand(new ActionCommand() {
            public String execute(HttpServletRequest request,
                    HttpServletResponse response, Object action, Object form,
                    ActionMapping mapping) {

                traceMessage.add("two");
                return "success";
            }
        });
        processor.addActionCommand(new ActionCommand() {
            public String execute(HttpServletRequest request,
                    HttpServletResponse response, Object action, Object form,
                    ActionMapping mapping) {

                traceMessage.add("three");
                return "error";
            }
        });

        ActionForward forward = processor.processActionExecute(getRequest(),
                getResponse(), action, form, mapping);

        assertEquals("success", forward.getName());
        assertEquals(2, traceMessage.size());
        assertEquals("one", traceMessage.get(0));
        assertEquals("two", traceMessage.get(1));
    }

    public void testExecuteReturnNull() throws Exception {
        processor.addActionCommand(new ActionCommand() {
            public String execute(HttpServletRequest request,
                    HttpServletResponse response, Object action, Object form,
                    ActionMapping mapping) {

                traceMessage.add("one");
                return null;
            }
        });
        processor.addActionCommand(new ActionCommand() {
            public String execute(HttpServletRequest request,
                    HttpServletResponse response, Object action, Object form,
                    ActionMapping mapping) {

                traceMessage.add("two");
                return "error";
            }
        });

        ActionForward forward = processor.processActionExecute(getRequest(),
                getResponse(), action, form, mapping);

        assertNull(forward);
        assertEquals(1, traceMessage.size());
        assertEquals("one", traceMessage.get(0));
    }

    public void testNotExecute() throws Exception {
        ActionForward forward = processor.processActionExecute(getRequest(),
                getResponse(), action, form, mapping);

        assertNull(forward);
    }

}