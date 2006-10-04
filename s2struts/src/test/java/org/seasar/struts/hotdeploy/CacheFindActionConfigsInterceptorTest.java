package org.seasar.struts.hotdeploy;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.processor.MockModuleConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class CacheFindActionConfigsInterceptorTest extends S2TestCase {

    private ModuleConfigWrapper configWrapper;

    public void setUp() throws Exception {
        super.setUp();
        include("CacheFindActionConfigsInterceptorTest.dicon");
    }

    public void testFindActionConfig() {
        final List called = new ArrayList();
        this.configWrapper.init(new MockModuleConfig() {
            public ActionConfig[] findActionConfigs() {
                ActionConfig[] result = new ActionConfig[2];
                result[0] = new ActionMapping();
                result[0].setPath("/cache0");
                result[1] = new ActionMapping();
                result[1].setPath("/cache1");
                called.add("called");
                return result;
            }
        });

        ActionConfig[] actionConfigs = this.configWrapper.findActionConfigs();
        assertNotNull(actionConfigs);
        assertEquals(2, actionConfigs.length);
        assertEquals(1, called.size());
        
        actionConfigs = this.configWrapper.findActionConfigs();
        assertNotNull(actionConfigs);
        assertEquals(2, actionConfigs.length);
        assertEquals(1, called.size());
    }

}
