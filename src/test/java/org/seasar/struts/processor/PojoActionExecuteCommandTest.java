package org.seasar.struts.processor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.POJOAction;
import org.seasar.struts.action.POJOActionImpl;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.util.ClassRegisterImpl;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class PojoActionExecuteCommandTest extends S2TestCase {

	private final List traceMessage = new ArrayList();

	private POJOActionImpl action = null;

	private Object form = null;

	private ActionMapping mapping = new MockActionMapping();

	private PojoActionExecuteCommand command = new PojoActionExecuteCommand();

	protected void setUp() throws Exception {
		super.setUp();
		include("PojoActionExecuteCommandTest.dicon");
		command.setClassRegister(new ClassRegisterImpl());
		mapping.setType(POJOAction.class.getName());
	}

	public void testExecute() throws Exception {
		command.addPojoActionCommand(new PojoActionCommand() {

			public String execute(HttpServletRequest request,
					HttpServletResponse response, Class actionInterface,
					Object action, Object form, ActionMapping mapping) {

				traceMessage.add("one");
				return NOT_EXECUTE;
			}

		});
		command.addPojoActionCommand(new PojoActionCommand() {

			public String execute(HttpServletRequest request,
					HttpServletResponse response, Class actionInterface,
					Object action, Object form, ActionMapping mapping) {

				traceMessage.add("two");
				return "success";
			}

		});
		command.addPojoActionCommand(new PojoActionCommand() {

			public String execute(HttpServletRequest request,
					HttpServletResponse response, Class actionInterface,
					Object action, Object form, ActionMapping mapping) {

				traceMessage.add("three");
				return "error";
			}

		});

		String forward = command.execute(getRequest(), getResponse(), action,
				form, mapping);

		assertEquals("success", forward);
		assertEquals(2, traceMessage.size());
		assertEquals("one", traceMessage.get(0));
		assertEquals("two", traceMessage.get(1));
	}

	public void testExecuteReturnNull() throws Exception {
		command.addPojoActionCommand(new PojoActionCommand() {

			public String execute(HttpServletRequest request,
					HttpServletResponse response, Class actionInterface,
					Object action, Object form, ActionMapping mapping) {

				traceMessage.add("one");
				return null;
			}

		});
		command.addPojoActionCommand(new PojoActionCommand() {

			public String execute(HttpServletRequest request,
					HttpServletResponse response, Class actionInterface,
					Object action, Object form, ActionMapping mapping) {

				traceMessage.add("two");
				return "error";
			}

		});

		String forward = command.execute(getRequest(), getResponse(), action,
				form, mapping);

		assertNull(forward);
		assertEquals(1, traceMessage.size());
		assertEquals("one", traceMessage.get(0));
	}

	public void testNotExecute() throws Exception {
		String forward = command.execute(getRequest(), getResponse(), action,
				form, mapping);
		
		assertEquals(ActionCommand.NOT_EXECUTE, forward);
	}

}
