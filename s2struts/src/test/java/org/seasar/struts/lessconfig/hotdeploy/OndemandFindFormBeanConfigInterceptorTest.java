package org.seasar.struts.lessconfig.hotdeploy;

import org.apache.struts.config.FormBeanConfig;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.hotdeploy.ModuleConfigWrapper;
import org.seasar.struts.processor.MockModuleConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class OndemandFindFormBeanConfigInterceptorTest extends S2TestCase {

    private ModuleConfigWrapper configWrapper;

    public void setUp() throws Exception {
        super.setUp();
        include("OndemandFindFormBeanConfigInterceptorTest.dicon");
    }

    public void testFindFormBeanConfig() {
        this.configWrapper.init(new MockModuleConfig());

        String name = "testOndemandDto";
        FormBeanConfig beanConfig = this.configWrapper.findFormBeanConfig(name);
        assertNotNull(beanConfig);
        assertEquals(name, beanConfig.getName());
    }
    
    public void testNotFoundFormBeanConfig() {
        this.configWrapper.init(new MockModuleConfig());
        
        String name = "testOndemandBean";
        FormBeanConfig beanConfig = this.configWrapper.findFormBeanConfig(name);
        assertNull(beanConfig);
    }
    
    public void testFindFormBeanConfigNullName() {
        this.configWrapper.init(new MockModuleConfig());
        
        String name = null;
        FormBeanConfig beanConfig = this.configWrapper.findFormBeanConfig(name);
        assertNull(beanConfig);
    }
    
}
