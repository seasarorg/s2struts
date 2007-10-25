package org.seasar.struts.lessconfig.autoregister.impl;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.autoregister.ActionConfigCreator;
import org.seasar.struts.lessconfig.config.AutoStrutsConfigRule;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ActionConfigCreatorImplTest extends S2TestCase {

    private ActionConfigCreator actionConfigCreator;

    private AutoStrutsConfigRule configRule;

    public void setUp() throws Exception {
        super.setUp();
        include("ActionConfigCreatorImplTest.dicon");
    }

    public void testCreateActionConfigByPath() {
        String path = "/testConfigCreator";
        ModuleConfig config = new ModuleConfigImpl();
        ActionConfig actionConfig = this.actionConfigCreator.createActionConfig(config, path);
        assertNotNull(actionConfig);
        assertEquals(path, actionConfig.getPath());
    }

    public void testNotCreateActionConfigByPath() {
        String path = "/testNotConfigCreator";
        ModuleConfig config = new ModuleConfigImpl();
        ActionConfig actionConfig = this.actionConfigCreator.createActionConfig(config, path);
        assertNull(actionConfig);
    }

    public void testCreateActionConfigByClass() {
        Class clazz = TestConfigCreatorAction.class;
        ModuleConfig config = new ModuleConfigImpl();
        ActionConfig actionConfig = this.actionConfigCreator.createActionConfig(config, clazz);
        assertNotNull(actionConfig);
        assertEquals("/testConfigCreator", actionConfig.getPath());
    }

    public void testNotCreateActionConfigByClass() {
        Class clazz = TestNotConfigCreatorAction.class;
        ModuleConfig config = new ModuleConfigImpl();
        ActionConfig actionConfig = this.actionConfigCreator.createActionConfig(config, clazz);
        assertNull(actionConfig);
    }

    public void testRegister_match() throws Exception {
        configRule.setActionClassPattern(".*HogeAction$");
        ModuleConfig config = new ModuleConfigImpl();
        ActionConfig actionConfig = actionConfigCreator.createActionConfig(config, TestHogeAction.class);
        assertNotNull(actionConfig);
    }

    public void testRegister_unmatch() throws Exception {
        configRule.setActionClassPattern(".*FooAction$");
        ModuleConfig config = new ModuleConfigImpl();
        ActionConfig actionConfig = actionConfigCreator.createActionConfig(config, TestHogeAction.class);
        assertNull(actionConfig);
    }

}
