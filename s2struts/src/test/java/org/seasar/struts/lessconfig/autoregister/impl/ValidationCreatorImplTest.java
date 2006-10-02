package org.seasar.struts.lessconfig.autoregister.impl;

import org.apache.commons.validator.Form;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.autoregister.ValidationCreator;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ValidationCreatorImplTest extends S2TestCase {

    private ValidationCreator validationCreator;

    public void setUp() throws Exception {
        super.setUp();
        include("ValidationCreatorImplTest.dicon");
    }

    public void testCreateForm() {
        String name = "testConfigCreatorDto";
        FormBeanConfig beanConfig = new FormBeanConfig();
        beanConfig.setName(name);
        ModuleConfig config = new ModuleConfigImpl();
        config.addFormBeanConfig(beanConfig);
        
        Form form = this.validationCreator.createForm(config, name);
        assertNotNull(form);
        assertEquals(name, form.getName());
    }
    
    public void testNotCreateForm() {
        String name = "testNotConfigCreatorBean";
        ModuleConfig config = new ModuleConfigImpl();
        
        Form form = this.validationCreator.createForm(config, name);
        assertNull(form);
    }

}
