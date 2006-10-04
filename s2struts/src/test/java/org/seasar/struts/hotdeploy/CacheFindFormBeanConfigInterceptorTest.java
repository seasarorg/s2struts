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
public class CacheFindFormBeanConfigInterceptorTest extends S2TestCase {

    private ModuleConfigWrapper configWrapper;

    public void setUp() throws Exception {
        super.setUp();
        include("CacheFindFormBeanConfigInterceptorTest.dicon");
    }

    public void testFindActionConfig() {
        final List called = new ArrayList();
        this.configWrapper.init(new MockModuleConfig() {
            public FormBeanConfig findFormBeanConfig(String name) {
                FormBeanConfig result = new FormBeanConfig();
                result.setName(name);
                called.add("called");
                return result;
            }
        });

        String name = "cache1Dto";
        FormBeanConfig beanConfig = this.configWrapper.findFormBeanConfig(name);
        assertNotNull(beanConfig);
        assertEquals(name, beanConfig.getName());
        assertEquals(1, called.size());

        beanConfig = this.configWrapper.findFormBeanConfig(name);
        assertNotNull(beanConfig);
        assertEquals(name, beanConfig.getName());
        assertEquals(1, called.size());

        name = "cache2Dto";
        beanConfig = this.configWrapper.findFormBeanConfig(name);
        assertNotNull(beanConfig);
        assertEquals(name, beanConfig.getName());
        assertEquals(2, called.size());
    }
    
    public void testFindActionConfigNameIsNull() {
        final List called = new ArrayList();
        this.configWrapper.init(new MockModuleConfig() {
            public FormBeanConfig findFormBeanConfig(String name) {
                called.add("called");
                return null;
            }
        });

        String name = null;
        FormBeanConfig beanConfig = this.configWrapper.findFormBeanConfig(name);
        assertNull(beanConfig);
        assertEquals(1, called.size());
        
        beanConfig = this.configWrapper.findFormBeanConfig(name);
        assertNull(beanConfig);
        assertEquals(1, called.size());
    }

}
