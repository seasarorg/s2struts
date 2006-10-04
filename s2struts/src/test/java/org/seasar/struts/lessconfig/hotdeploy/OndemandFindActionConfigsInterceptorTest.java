package org.seasar.struts.lessconfig.hotdeploy;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.hotdeploy.ModuleConfigWrapper;
import org.seasar.struts.processor.MockModuleConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class OndemandFindActionConfigsInterceptorTest extends S2TestCase {

    private ModuleConfigWrapper configWrapper;

    public void setUp() throws Exception {
        super.setUp();
        include("OndemandFindActionConfigsInterceptorTest.dicon");
        include("OndemandComponent.dicon");
    }

    public void testFindActionConfig() {
        this.configWrapper.init(new MockModuleConfig());

        ActionConfig[] actionConfigs = this.configWrapper.findActionConfigs();
        assertNotNull(actionConfigs);
        assertEquals(4, actionConfigs.length);
    }

    public void testFindActionConfigAddConcreteMethodResult() {
        this.configWrapper.init(new MockModuleConfig() {
            public ActionConfig[] findActionConfigs() {
                ActionConfig[] actionConfigs = new ActionConfig[2];
                actionConfigs[0] = new ActionMapping();
                actionConfigs[0].setPath("/mock0");
                actionConfigs[1] = new ActionMapping();
                actionConfigs[1].setPath("/mock1");
                return actionConfigs;
            }
        });

        ActionConfig[] actionConfigs = this.configWrapper.findActionConfigs();
        assertNotNull(actionConfigs);
        assertEquals(6, actionConfigs.length);
    }

}
