package org.seasar.struts.lessconfig.autoregister.impl;

import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.autoregister.ActionFormConfigCreator;
import org.seasar.struts.lessconfig.config.AutoStrutsConfigRule;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ActionFormConfigCreatorImplTest extends S2TestCase {

    private ActionFormConfigCreator actionFormConfigCreator;

    private AutoStrutsConfigRule configRule;

    public void setUp() throws Exception {
        super.setUp();
        include("ActionFormConfigCreatorImplTest.dicon");
    }

    public void testCreateFormBeanConfigByName() {
        String name = "testConfigCreatorDto";
        ModuleConfig config = new ModuleConfigImpl();
        FormBeanConfig beanConfig = this.actionFormConfigCreator.createFormBeanConfig(config, name);
        assertNotNull(beanConfig);
        assertEquals(name, beanConfig.getName());
    }

    public void testNotCreateFormBeanConfigByName() {
        String name = "testNotConfigCreatorBean";
        ModuleConfig config = new ModuleConfigImpl();
        FormBeanConfig beanConfig = this.actionFormConfigCreator.createFormBeanConfig(config, name);
        assertNull(beanConfig);
    }

    public void testCreateFormBeanConfigByClass() {
        Class clazz = TestConfigCreatorDto.class;
        ModuleConfig config = new ModuleConfigImpl();
        FormBeanConfig beanConfig = this.actionFormConfigCreator.createFormBeanConfig(config, clazz);
        assertNotNull(beanConfig);
        assertEquals("testConfigCreatorDto", beanConfig.getName());
    }

    public void testNotCreateFormBeanConfigByClass() {
        Class clazz = TestNotConfigCreatorBean.class;
        ModuleConfig config = new ModuleConfigImpl();
        FormBeanConfig beanConfig = this.actionFormConfigCreator.createFormBeanConfig(config, clazz);
        assertNull(beanConfig);
    }

    public void testRegister_match() throws Exception {
        configRule.setFormClassPattern(".*HogeForm$");
        ModuleConfig config = new ModuleConfigImpl();
        FormBeanConfig actionConfig = actionFormConfigCreator.createFormBeanConfig(config, TestHogeForm.class);
        assertNotNull(actionConfig);
    }

    public void testRegister_unmatch() throws Exception {
        configRule.setFormClassPattern(".*FooForm$");
        ModuleConfig config = new ModuleConfigImpl();
        FormBeanConfig actionConfig = actionFormConfigCreator.createFormBeanConfig(config, TestHogeForm.class);
        assertNull(actionConfig);
    }

}
