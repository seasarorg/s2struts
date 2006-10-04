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
public class CacheFindActionConfigInterceptorTest extends S2TestCase {

    private ModuleConfigWrapper configWrapper;

    public void setUp() throws Exception {
        super.setUp();
        include("CacheFindActionConfigInterceptorTest.dicon");
    }

    public void testFindActionConfig() {
        final List called = new ArrayList();
        this.configWrapper.init(new MockModuleConfig() {
            public ActionConfig findActionConfig(String path) {
                ActionConfig result = new ActionMapping();
                result.setPath(path);
                called.add("called");
                return result;
            }
        });

        String path = "/cache1";
        ActionConfig actionConfig = this.configWrapper.findActionConfig(path);
        assertNotNull(actionConfig);
        assertEquals(path, actionConfig.getPath());
        assertEquals(1, called.size());
        
        actionConfig = this.configWrapper.findActionConfig(path);
        assertNotNull(actionConfig);
        assertEquals(path, actionConfig.getPath());
        assertEquals(1, called.size());
        
        path = "/cache2";
        actionConfig = this.configWrapper.findActionConfig(path);
        assertNotNull(actionConfig);
        assertEquals(path, actionConfig.getPath());
        assertEquals(2, called.size());
    }

}
