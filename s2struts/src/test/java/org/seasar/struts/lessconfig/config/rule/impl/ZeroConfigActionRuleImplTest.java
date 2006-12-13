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
public class ZeroConfigActionRuleImplTest extends S2TestCase {

    private ZeroConfigActionRule zeroConfigActionRule;

    public void setUp() throws Exception {
        super.setUp();
        include("ZeroConfigActionRuleImplTest.dicon");
    }

    public void testAddFowardConfigSimpleComponentName() {
        ModuleConfig config = new ModuleConfigImpl("");
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestForwardSimpleAction.class;

        this.zeroConfigActionRule.addForwardConfig(config, actionClass, mapping);
        assertEquals(1, mapping.findForwardConfigs().length);

        ForwardConfig forwardConfig = mapping.findForwardConfigs()[0];
        assertEquals("success", forwardConfig.getName());
        assertEquals("/org/seasar/struts/lessconfig/config/rule/impl/testForward.jsp", forwardConfig.getPath());
    }

    public void testNotAddFowardConfigSimpleComponentName() {
        ModuleConfig config = new ModuleConfigImpl("");
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestNotForwardSimpleAction.class;

        this.zeroConfigActionRule.addForwardConfig(config, actionClass, mapping);
        assertEquals(0, mapping.findForwardConfigs().length);
    }

    public void testAddFowardConfigSlashComponentName() {
        ModuleConfig config = new ModuleConfigImpl("");
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestForwardSlashAction.class;

        this.zeroConfigActionRule.addForwardConfig(config, actionClass, mapping);
        assertEquals(1, mapping.findForwardConfigs().length);

        ForwardConfig forwardConfig = mapping.findForwardConfigs()[0];
        assertEquals("success", forwardConfig.getName());
        assertEquals("/org/seasar/struts/lessconfig/config/rule/impl/testForward.jsp", forwardConfig.getPath());
    }

    public void testNotAddFowardConfigSlashComponentName() {
        ModuleConfig config = new ModuleConfigImpl("");
        ActionMapping mapping = new MockActionMapping();
        Class actionClass = TestNotForwardSlashAction.class;

        this.zeroConfigActionRule.addForwardConfig(config, actionClass, mapping);
        assertEquals(0, mapping.findForwardConfigs().length);
    }

}
