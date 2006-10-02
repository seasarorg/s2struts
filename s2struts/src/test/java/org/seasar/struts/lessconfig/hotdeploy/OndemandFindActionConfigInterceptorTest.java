package org.seasar.struts.lessconfig.hotdeploy;

import org.apache.struts.config.ActionConfig;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.hotdeploy.ModuleConfigWrapper;
import org.seasar.struts.processor.MockModuleConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class OndemandFindActionConfigInterceptorTest extends S2TestCase {

    private ModuleConfigWrapper configWrapper;

    public void setUp() throws Exception {
        super.setUp();
        include("OndemandFindActionConfigInterceptorTest.dicon");
    }

    public void testFindActionConfig() {
        this.configWrapper.init(new MockModuleConfig());

        String path = "/testOndemand";
        ActionConfig actionConfig = this.configWrapper.findActionConfig(path);
        assertNotNull(actionConfig);
        assertEquals(path, actionConfig.getPath());
    }
    
    public void testNotFoundActionConfig() {
        this.configWrapper.init(new MockModuleConfig());

        String path = "/testNotOndemand";
        ActionConfig actionConfig = this.configWrapper.findActionConfig(path);
        assertNull(actionConfig);
    }

}
