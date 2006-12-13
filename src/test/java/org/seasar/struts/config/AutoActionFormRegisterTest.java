package org.seasar.struts.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class AutoActionFormRegisterTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        include("s2struts.dicon");
    }

    public void testRegisterSimpleForm() {
        ModuleConfig config = new ModuleConfigImpl("");
        List classes = new ArrayList();
        classes.add(TestSimpleForm.class);

        AutoActionFormRegister.register(config, classes);
        assertEquals(1, config.findFormBeanConfigs().length);

        FormBeanConfig beanConfig = config.findFormBeanConfigs()[0];
        assertEquals("testSimpleForm", beanConfig.getName());
    }

    public void testRegisterSimpleDto() {
        ModuleConfig config = new ModuleConfigImpl("");
        List classes = new ArrayList();
        classes.add(TestSimpleDto.class);

        AutoActionFormRegister.register(config, classes);
        assertEquals(1, config.findFormBeanConfigs().length);

        FormBeanConfig beanConfig = config.findFormBeanConfigs()[0];
        assertEquals("testSimpleDto", beanConfig.getName());
    }

    public void testNotRegister() {
        ModuleConfig config = new ModuleConfigImpl("");
        List classes = new ArrayList();
        classes.add(TestIgnoreTarget.class);

        AutoActionFormRegister.register(config, classes);
        assertEquals(0, config.findFormBeanConfigs().length);
    }

}
