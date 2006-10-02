package org.seasar.struts.lessconfig.autoregister.impl;

import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.autoregister.ActionFormConfigCreator;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ActionFormConfigCreatorImplTest extends S2TestCase {

    private ActionFormConfigCreator actionFormConfigCreator;

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

}
