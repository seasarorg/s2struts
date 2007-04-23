package org.seasar.struts.lessconfig.config.rule.impl;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.config.rule.ZeroConfigActionRule;
import org.seasar.struts.mock.MockActionMapping;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ZeroConfigActionRuleImplSubApplicationTest extends S2TestCase {

    private ZeroConfigActionRule zeroConfigActionRule;

    public void setUp() throws Exception {
        super.setUp();
        include("ZeroConfigActionRuleImplSubApplicationTest.dicon");
    }

    public void testAddFowardConfigSubApplicationComponentName() {
        ModuleConfig config = new ModuleConfigImpl("");
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestForwardSubApplicationAction.class;

        this.zeroConfigActionRule.addForwardConfig(config, actionClass, mapping);
        assertEquals(1, mapping.findForwardConfigs().length);

        ForwardConfig forwardConfig = mapping.findForwardConfigs()[0];
        assertEquals("success", forwardConfig.getName());
        assertEquals("/org/seasar/struts/lessconfig/config/rule/impl/test/testForward.jsp", forwardConfig.getPath());
    }

    public void testNotAddFowardConfigSubApplicationComponentName() {
        ModuleConfig config = new ModuleConfigImpl("");
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestNotForwardSubApplicationAction.class;

        this.zeroConfigActionRule.addForwardConfig(config, actionClass, mapping);
        assertEquals(0, mapping.findForwardConfigs().length);
    }

}
