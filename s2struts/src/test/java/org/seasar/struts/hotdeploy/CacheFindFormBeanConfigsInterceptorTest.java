package org.seasar.struts.hotdeploy;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.config.FormBeanConfig;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.processor.MockModuleConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class CacheFindFormBeanConfigsInterceptorTest extends S2TestCase {

    private ModuleConfigWrapper configWrapper;

    public void setUp() throws Exception {
        super.setUp();
        include("CacheFindFormBeanConfigsInterceptorTest.dicon");
    }

    public void testFindActionConfig() {
        final List called = new ArrayList();
        this.configWrapper.init(new MockModuleConfig() {
            public FormBeanConfig[] findFormBeanConfigs() {
                FormBeanConfig[] beanConfigs = new FormBeanConfig[2];
                beanConfigs[0] = new FormBeanConfig();
                beanConfigs[0].setName("cache0");
                beanConfigs[1] = new FormBeanConfig();
                beanConfigs[1].setName("cache1");
                called.add("called");
                return beanConfigs;
            }
        });

        FormBeanConfig[] beanConfigs = this.configWrapper.findFormBeanConfigs();
        assertNotNull(beanConfigs);
        assertEquals(2, beanConfigs.length);
        assertEquals(1, called.size());
        
        beanConfigs = this.configWrapper.findFormBeanConfigs();
        assertNotNull(beanConfigs);
        assertEquals(2, beanConfigs.length);
        assertEquals(1, called.size());
    }

}
