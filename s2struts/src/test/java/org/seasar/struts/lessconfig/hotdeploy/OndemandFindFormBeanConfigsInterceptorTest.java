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
public class OndemandFindFormBeanConfigsInterceptorTest extends S2TestCase {

    private ModuleConfigWrapper configWrapper;

    public void setUp() throws Exception {
        super.setUp();
        include("OndemandFindFormBeanConfigsInterceptorTest.dicon");
        include("OndemandComponent.dicon");
    }

    public void testFindFormBeanConfig() {
        this.configWrapper.init(new MockModuleConfig());

        FormBeanConfig[] beanConfigs = this.configWrapper.findFormBeanConfigs();
        assertNotNull(beanConfigs);
        assertEquals(4, beanConfigs.length);
    }

    public void testFindFormBeanConfigAddConcreteMethodResult() {
        this.configWrapper.init(new MockModuleConfig() {
            public FormBeanConfig[] findFormBeanConfigs() {
                FormBeanConfig[] beanConfigs = new FormBeanConfig[2];
                beanConfigs[0] = new FormBeanConfig();
                beanConfigs[0].setName("mock0");
                beanConfigs[1] = new FormBeanConfig();
                beanConfigs[1].setName("mock1");
                return beanConfigs;
            }
        });

        FormBeanConfig[] beanConfigs = this.configWrapper.findFormBeanConfigs();
        assertNotNull(beanConfigs);
        assertEquals(6, beanConfigs.length);
    }

}
