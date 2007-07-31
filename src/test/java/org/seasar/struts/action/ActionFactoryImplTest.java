package org.seasar.struts.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;

/**
 * @author Satoshi Kimura
 */
public class ActionFactoryImplTest extends S2TestCase {
    private ActionFactory actionFactory;

    public ActionFactoryImplTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        include("s2struts.dicon");
        include("TestAction.dicon");
    }

    public void testGetActionWithClassName() {
        Action firstInstance = null;
        Action secondInstance = null;

        ActionServlet servlet = new ActionServlet();
        firstInstance = actionFactory.getActionWithClassName(
                "org.seasar.struts.action.Test1Action", servlet);
        secondInstance = actionFactory.getActionWithClassName(
                "org.seasar.struts.action.Test1Action", servlet);
        assertTest1Action(firstInstance, secondInstance);

        firstInstance = actionFactory.getActionWithClassName(
                "org.seasar.struts.action.Test2Action", servlet);
        secondInstance = actionFactory.getActionWithClassName(
                "org.seasar.struts.action.Test2Action", servlet);
        assertTest2Action(firstInstance, secondInstance);

        firstInstance = actionFactory.getActionWithClassName(
                "org.seasar.struts.action.Test3Action", servlet);
        secondInstance = actionFactory.getActionWithClassName(
                "org.seasar.struts.action.Test3Action", servlet);
        assertTest3Action(firstInstance, secondInstance);
    }

    public void testGetActionWithComponentName() {
        Action firstInstance = null;
        Action secondInstance = null;

        ActionServlet servlet = new ActionServlet();
        firstInstance = actionFactory.getActionWithComponentName("/test1",
                servlet);
        secondInstance = actionFactory.getActionWithComponentName("/test1",
                servlet);
        assertTest1Action(firstInstance, secondInstance);

        firstInstance = actionFactory.getActionWithComponentName("/test2",
                servlet);
        secondInstance = actionFactory.getActionWithComponentName("/test2",
                servlet);
        assertTest2Action(firstInstance, secondInstance);

        try {
            firstInstance = actionFactory.getActionWithComponentName("/test3",
                    servlet);
            fail();
        } catch (ComponentNotFoundRuntimeException e) {
            // success
        }
    }

    public void testProcessActionCreate() {
    }

    public void testGetActionInstance() {
    }

    private void assertTest1Action(Action firstInstance, Action secondInstance) {
        assertSame(firstInstance, secondInstance);
        assertTrue(firstInstance instanceof org.seasar.struts.action.Test1Action);
        assertFalse(((Test1Action) firstInstance).isExecutedInitMethod());
        assertTrue(((Test1Action) firstInstance).hasService());
    }

    private void assertTest2Action(Action firstInstance, Action secondInstance) {
        assertNotSame(firstInstance, secondInstance);
        assertTrue(firstInstance instanceof org.seasar.struts.action.Test2Action);
        assertTrue(((Test2Action) firstInstance).isExecutedInitMethod());
    }

    private void assertTest3Action(Action firstInstance, Action secondInstance) {
        assertSame(firstInstance, secondInstance);
        assertTrue(firstInstance instanceof org.seasar.struts.action.Test3Action);
        assertFalse(((Test3Action) firstInstance).isExecutedInitMethod());
        assertTrue(((Test3Action) firstInstance).hasService());
    }

}