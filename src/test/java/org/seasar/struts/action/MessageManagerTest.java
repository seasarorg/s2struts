package org.seasar.struts.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class MessageManagerTest extends S2TestCase {
    public MessageManagerTest(String name) {
        super(name);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        register(MessageFacadeImpl.class);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Class under test for void addError(String, String, Object[])
     */
    public void testAddErrorStringObjectArray() {
        MessageManager.addError("property", "key1", new Object[] {"a1", "b1"});
        MessageManager.addError("property", "key2", new Object[] {"a2", "b2"});
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "b1", "", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "b2", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }
    
    public void testAddErrorStringNoObject() {
        MessageManager.addError("property", "key1");
        MessageManager.addError("property", "key2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"", "", "", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"", "", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }
    
    /*
     * Class under test for void addError(String, String, Object)
     */
    public void testAddErrorStringObject() {
        MessageManager.addError("property", "key1", "a1");
        MessageManager.addError("property", "key2", "a2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "", "", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addError(String, String, Object, Object)
     */
    public void testAddErrorStringObjectObject() {
        MessageManager.addError("property", "key1", "a1", "b1");
        MessageManager.addError("property", "key2", "a2", "b2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "b1", "", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "b2", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addError(String, String, Object, Object, Object)
     */
    public void testAddErrorStringObjectObjectObject() {
        MessageManager.addError("property", "key1", "a1", "b1", "c1");
        MessageManager.addError("property", "key2", "a2", "b2", "c2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "b1", "c1", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "b2", "c2", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addError(String, String, Object, Object, Object, Object)
     */
    public void testAddErrorStringObjectObjectObjectObject() {
        MessageManager.addError("property", "key1", "a1", "b1", "c1", "d1");
        MessageManager.addError("property", "key2", "a2", "b2", "c2", "d2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "b1", "c1", "d1", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "b2", "c2", "d2", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    // -----------------------------------------------------------------------
    
    /*
     * Class under test for void addGlobalError(String, Object[])
     */
    public void testAddGlobalErrorStringObjectArray() {
        MessageManager.addGlobalError("key1", new Object[] {"a1", "b1"});
        MessageManager.addGlobalError("key2", new Object[] {"a2", "b2"});
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "b1", "", "", "", "", "", "", "", ""}));
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "b2", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }
    
    public void testAddGlobalErrorNoObject() {
        MessageManager.addGlobalError("key1");
        MessageManager.addGlobalError("key2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"", "", "", "", "", "", "", "", "", ""}));
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"", "", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }
    
    /*
     * Class under test for void addGlobalError(String, Object)
     */
    public void testAddGlobalErrorStringObject() {
        MessageManager.addGlobalError("key1", "a1");
        MessageManager.addGlobalError("key2", "a2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "", "", "", "", "", "", "", "", ""}));
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addGlobalError(String, Object, Object)
     */
    public void testAddGlobalErrorStringObjectObject() {
        MessageManager.addGlobalError("key1", "a1", "b1");
        MessageManager.addGlobalError("key2", "a2", "b2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "b1", "", "", "", "", "", "", "", ""}));
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "b2", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addGlobalError(String, Object, Object, Object)
     */
    public void testAddGlobalErrorStringObjectObjectObject() {
        MessageManager.addGlobalError("key1", "a1", "b1", "c1");
        MessageManager.addGlobalError("key2", "a2", "b2", "c2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "b1", "c1", "", "", "", "", "", "", ""}));
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "b2", "c2", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addGlobalError(String, Object, Object, Object, Object)
     */
    public void testAddGlobalErrorStringObjectObjectObjectObject() {
        MessageManager.addGlobalError("key1", "a1", "b1", "c1", "d1");
        MessageManager.addGlobalError("key2", "a2", "b2", "c2", "d2");
        MessageManager.saveErrors();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "b1", "c1", "d1", "", "", "", "", "", ""}));
        expected.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "b2", "c2", "d2", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    // -----------------------------------------------------------------------

    /*
     * Class under test for void addMessage(String, String, Object[])
     */
    public void testAddMessageStringObjectArray() {
        MessageManager.addMessage("property", "key1", new Object[] {"a1", "b1"});
        MessageManager.addMessage("property", "key2", new Object[] {"a2", "b2"});
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "b1", "", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "b2", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    public void testAddMessageNoObject() {
        MessageManager.addMessage("property", "key1");
        MessageManager.addMessage("property", "key2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"", "", "", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"", "", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addMessage(String, String, Object)
     */
    public void testAddMessageStringObject() {
        MessageManager.addMessage("property", "key1", "a1");
        MessageManager.addMessage("property", "key2", "a2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "", "", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addMessage(String, String, Object, Object)
     */
    public void testAddMessageStringObjectObject() {
        MessageManager.addMessage("property", "key1", "a1", "b1");
        MessageManager.addMessage("property", "key2", "a2", "b2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "b1", "", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "b2", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addMessage(String, String, Object, Object, Object)
     */
    public void testAddMessageStringObjectObjectObject() {
        MessageManager.addMessage("property", "key1", "a1", "b1", "c1");
        MessageManager.addMessage("property", "key2", "a2", "b2", "c2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "b1", "c1", "", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "b2", "c2", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addMessage(String, String, Object, Object, Object, Object)
     */
    public void testAddMessageStringObjectObjectObjectObject() {
        MessageManager.addMessage("property", "key1", "a1", "b1", "c1", "d1");
        MessageManager.addMessage("property", "key2", "a2", "b2", "c2", "d2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("property", new ActionMessage("key1", new Object[] {"a1", "b1", "c1", "d1", "", "", "", "", "", ""}));
        expected.add("property", new ActionMessage("key2", new Object[] {"a2", "b2", "c2", "d2", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    // -----------------------------------------------------------------------

    /*
     * Class under test for void addGlobalMessage(String, Object[])
     */
    public void testAddGlobalMessageStringObjectArray() {
        MessageManager.addGlobalMessage("key1", new Object[] {"a1", "b1"});
        MessageManager.addGlobalMessage("key2", new Object[] {"a2", "b2"});
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "b1", "", "", "", "", "", "", "", ""}));
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "b2", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    public void testAddGlobalMessageNoObject() {
        MessageManager.addGlobalMessage("key1");
        MessageManager.addGlobalMessage("key2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"", "", "", "", "", "", "", "", "", ""}));
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"", "", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addGlobalMessage(String, Object)
     */
    public void testAddGlobalMessageStringObject() {
        MessageManager.addGlobalMessage("key1", "a1");
        MessageManager.addGlobalMessage("key2", "a2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "", "", "", "", "", "", "", "", ""}));
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addGlobalMessage(String, Object, Object)
     */
    public void testAddGlobalMessageStringObjectObject() {
        MessageManager.addGlobalMessage("key1", "a1", "b1");
        MessageManager.addGlobalMessage("key2", "a2", "b2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "b1", "", "", "", "", "", "", "", ""}));
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "b2", "", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addGlobalMessage(String, Object, Object, Object)
     */
    public void testAddGlobalMessageStringObjectObjectObject() {
        MessageManager.addGlobalMessage("key1", "a1", "b1", "c1");
        MessageManager.addGlobalMessage("key2", "a2", "b2", "c2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "b1", "c1", "", "", "", "", "", "", ""}));
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "b2", "c2", "", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    /*
     * Class under test for void addGlobalMessage(String, Object, Object, Object, Object)
     */
    public void testAddGlobalMessageStringObjectObjectObjectObject() {
        MessageManager.addGlobalMessage("key1", "a1", "b1", "c1", "d1");
        MessageManager.addGlobalMessage("key2", "a2", "b2", "c2", "d2");
        MessageManager.saveMessages();
        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key1", new Object[] {"a1", "b1", "c1", "d1", "", "", "", "", "", ""}));
        expected.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("key2", new Object[] {"a2", "b2", "c2", "d2", "", "", "", "", "", ""}));

        assertEquals(expected.toString(), result.toString());
    }

    // -----------------------------------------------------------------------

    public void testAddErrors() {
        ActionMessages errors = null;
        MessageManager.addErrors(errors);
        MessageManager.saveErrors();

        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);
        assertNull(result);

        errors = new ActionMessages();
        errors.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        MessageManager.addErrors(errors);
        MessageManager.saveErrors();

        result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        assertEquals(expected, result);

        errors = null;
        MessageManager.addErrors(errors);
        MessageManager.saveErrors();

        result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        assertEquals(expected, result);
    }

    public void testAddMessages() {
        ActionMessages messages = null;
        MessageManager.addMessages(messages);
        MessageManager.saveMessages();

        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);
        assertNull(result);

        messages = new ActionMessages();
        messages.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        MessageManager.addMessages(messages);
        MessageManager.saveMessages();

        result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages expected = new ActionMessages();
        expected.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        assertEquals(expected, result);

        messages = null;
        MessageManager.addMessages(messages);
        MessageManager.saveMessages();

        result = (ActionMessages) getRequest().getAttribute(Globals.MESSAGE_KEY);

        assertEquals(expected, result);
    }

    public void testGetErrors() {
        ActionMessages errors = null;
        saveErrors(errors);
        ActionMessages result = MessageManager.getErrors();
        ActionMessages expected = new ActionMessages();
        assertEquals(result, expected);

        errors = new ActionMessages();
        errors.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        saveErrors(errors);

        expected = new ActionMessages();
        expected.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        result = MessageManager.getErrors();
        assertEquals(expected, result);
    }

    public void testGetErrorsFromSession() {
        ActionMessages errors = null;
        saveErrorsInSession(errors);
        ActionMessages result = MessageManager.getErrorsFromSession();
        ActionMessages expected = new ActionMessages();
        assertEquals(result, expected);

        errors = new ActionMessages();
        errors.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        saveErrorsInSession(errors);

        expected = new ActionMessages();
        expected.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        result = MessageManager.getErrorsFromSession();
        assertEquals(expected, result);
    }

    public void testGetMessages() {
        ActionMessages messages = null;
        saveMessages(messages);
        ActionMessages result = MessageManager.getMessages();
        ActionMessages expected = new ActionMessages();
        assertEquals(result, expected);

        messages = new ActionMessages();
        messages.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        saveMessages(messages);

        expected = new ActionMessages();
        expected.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        result = MessageManager.getMessages();
        assertEquals(expected, result);
    }

    public void testGetMessagesFromSession() {
        ActionMessages messages = null;
        saveMessagesInSession(messages);
        ActionMessages result = MessageManager.getMessagesFromSession();
        ActionMessages expected = new ActionMessages();
        assertEquals(result, expected);

        messages = new ActionMessages();
        messages.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        saveMessagesInSession(messages);

        expected = new ActionMessages();
        expected.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        result = MessageManager.getMessagesFromSession();
        assertEquals(expected, result);
    }

    /*
     * Class under test for void saveErrors(ActionMessages)
     */
    public void testSaveErrorsActionMessages() {
        ActionMessages errors = new ActionMessages();
        errors.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        MessageManager.saveErrors(errors);

        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        assertEquals(errors, result);
    }

    /*
     * Class under test for void saveErrorsInSession()
     */
    public void testSaveErrorsInSession() {
        MessageManager.addGlobalError("key1", new Object[] {"a1"});
        MessageManager.saveErrorsInSession();

        ActionMessages result = (ActionMessages) getRequest().getSession().getAttribute(Globals.ERROR_KEY);

        ActionMessages errors = new ActionMessages();
        errors.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        assertEquals(errors, result);
    }

    /*
     * Class under test for void saveErrorsInSession(ActionMessages)
     */
    public void testSaveErrorsInSessionActionMessages() {
        ActionMessages errors = new ActionMessages();
        errors.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        MessageManager.saveErrorsInSession(errors);

        ActionMessages result = (ActionMessages) getRequest().getSession().getAttribute(Globals.ERROR_KEY);

        assertEquals(errors, result);

        errors = new ActionMessages();
        MessageManager.saveErrorsInSession(errors);

        result = (ActionMessages) getRequest().getSession().getAttribute(Globals.ERROR_KEY);
        assertNull(result);
    }

    /*
     * Class under test for void saveMessages(ActionMessages)
     */
    public void testSaveMessagesActionMessages() {
        ActionMessages errors = new ActionMessages();
        errors.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        MessageManager.saveErrors(errors);

        ActionMessages result = (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);

        assertEquals(errors, result);
    }

    /*
     * Class under test for void saveMessagesInSession()
     */
    public void testSaveMessagesInSession() {
        MessageManager.addGlobalMessage("key1", new Object[] {"a1"});
        MessageManager.saveMessagesInSession();

        ActionMessages result = (ActionMessages) getRequest().getSession().getAttribute(Globals.MESSAGE_KEY);

        ActionMessages messages = new ActionMessages();
        messages.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        assertEquals(messages, result);
    }

    /*
     * Class under test for void saveMessagesInSession(ActionMessages)
     */
    public void testSaveMessagesInSessionActionMessages() {
        ActionMessages messages = new ActionMessages();
        messages.add("key1", new ActionMessage("key1", new Object[] {"a1"}));
        MessageManager.saveMessagesInSession(messages);

        ActionMessages result = (ActionMessages) getRequest().getSession().getAttribute(Globals.MESSAGE_KEY);

        assertEquals(messages, result);

        messages = new ActionMessages();
        MessageManager.saveMessagesInSession(messages);

        result = (ActionMessages) getRequest().getSession().getAttribute(Globals.MESSAGE_KEY);
        assertNull(result);
    }

    public void assertEquals(ActionMessages expected, ActionMessages result) {
        assertEquals(expected.size(), result.size());

        Iterator expectedIterator = expected.get();
        Iterator resultIterator = result.get();

        while (expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next().toString(), resultIterator.next().toString().replaceAll("(, )*]$","]"));
        }
    }

    private void saveErrors(ActionMessages errors) {
        TestAction action = new TestAction();
        action.saveErrors(getRequest(), errors);
    }

    private void saveMessages(ActionMessages messages) {
        TestAction action = new TestAction();
        action.saveMessages(getRequest(), messages);
    }

    private void saveErrorsInSession(ActionMessages errors) {
        getRequest().getSession().setAttribute(Globals.ERROR_KEY, errors);
    }

    private void saveMessagesInSession(ActionMessages messages) {
        getRequest().getSession().setAttribute(Globals.MESSAGE_KEY, messages);
    }

    static class TestAction extends Action {
        public void saveErrors(HttpServletRequest req, ActionMessages errors) {
            super.saveErrors(req, errors);
        }

        public void saveMessages(HttpServletRequest req, ActionMessages errors) {
            super.saveMessages(req, errors);
        }
    }

}
