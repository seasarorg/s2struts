package org.seasar.struts.config.rule;

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ForwardConfig;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ZeroConfigActionRuleImplTest extends S2TestCase {

    private ZeroConfigActionRule zeroConfigActionRule;

    protected void setUp() throws Exception {
        super.setUp();
        include("s2struts.dicon");
        include("ZeroConfigActionRuleImplTest.dicon");
    }

    public void testAddFowardConfigSimpleComponentName() {
        ServletContext context = getServletContext();
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestForwardSimpleAction.class;

        this.zeroConfigActionRule.addFowardConfig(actionClass, mapping, context);
        assertEquals(1, mapping.findForwardConfigs().length);

        ForwardConfig forwardConfig = mapping.findForwardConfigs()[0];
        assertEquals("success", forwardConfig.getName());
        assertEquals("/org/seasar/struts/config/rule/testForward.jsp", forwardConfig.getPath());
    }

    public void testAddFowardConfigSlashComponentName() {
        ServletContext context = getServletContext();
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestForwardSlashAction.class;

        this.zeroConfigActionRule.addFowardConfig(actionClass, mapping, context);
        assertEquals(1, mapping.findForwardConfigs().length);

        ForwardConfig forwardConfig = mapping.findForwardConfigs()[0];
        assertEquals("success", forwardConfig.getName());
        assertEquals("/org/seasar/struts/config/rule/testForward.jsp", forwardConfig.getPath());
    }

    public void testAddFowardConfigNoRegisteredComponent() {
        ServletContext context = getServletContext();
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestForwardNoRegisteredComponentAction.class;

        this.zeroConfigActionRule.addFowardConfig(actionClass, mapping, context);
        assertEquals(1, mapping.findForwardConfigs().length);

        ForwardConfig forwardConfig = mapping.findForwardConfigs()[0];
        assertEquals("success", forwardConfig.getName());
        assertEquals("/org/seasar/struts/config/rule/testForwardNoRegisteredComponent.jsp",
                forwardConfig.getPath());
    }

// MockServletContext#getRealPath()で存在しないファイルを指定した場合、
// nullではなくExceptionが発生するため
// とりあえずコメント化。S2 2.3.16がリリースされたら、このテストを有効にすること
//
//    public void testNotAddFowardConfigSimpleComponentName() {
//        ServletContext context = getServletContext();
//        ActionMapping mapping = new MockActionMapping();
//        Class actionClass = TestNotForwardSimpleAction.class;
//
//        this.zeroConfigActionRule.addFowardConfig(actionClass, mapping, context);
//        assertEquals(0, mapping.findForwardConfigs().length);
//    }
//
//    public void testNotAddFowardConfigSlashComponentName() {
//        ServletContext context = getServletContext();
//        ActionMapping mapping = new MockActionMapping();
//        Class actionClass = TestNotForwardSlashAction.class;
//
//        this.zeroConfigActionRule.addFowardConfig(actionClass, mapping, context);
//        assertEquals(0, mapping.findForwardConfigs().length);
//    }
//
//    public void testNotAddFowardConfigNoRegisteredComponent() {
//        ServletContext context = getServletContext();
//        ActionMapping mapping = new MockActionMapping();
//        Class actionClass = TestNotForwardNoRegisteredComponentAction.class;
//
//        this.zeroConfigActionRule.addFowardConfig(actionClass, mapping, context);
//        assertEquals(0, mapping.findForwardConfigs().length);
//    }

}
