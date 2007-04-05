package org.seasar.struts.util;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.TestComponent1Action;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ModuleConfigUtilForComponentNameTest extends S2TestCase {

    private ModuleConfig moduleConfig = new ModuleConfigImpl();

    protected void setUp() throws Exception {
        super.setUp();
        include("ModuleConfigUtilForComponentNameTest.dicon");
    }

    protected void setUpAfterContainerInit() throws Exception {
        getRequest().setAttribute(Globals.MODULE_KEY, this.moduleConfig);

        ActionConfig actionConfig1 = new ActionMapping();
        actionConfig1.setPath("/testComponent1");
        actionConfig1.setType(TestComponent1Action.class.getName());

        this.moduleConfig.addActionConfig(actionConfig1);
    }

    public void testFindActionConfigForComponentName() {
        ActionConfig config = ModuleConfigUtil.findActionConfigForComponentName("testComponent1Action");

        assertNotNull(config);
        assertEquals("/testComponent1", config.getPath());
    }

    public void testNotFoundActionConfigForComponentName1() {
        ActionConfig config = ModuleConfigUtil.findActionConfigForComponentName("testComponent2Action");

        assertNull(config);
    }

    public void testNotFoundActionConfigForComponentName2() {
        ActionConfig config = ModuleConfigUtil.findActionConfigForComponentName("testComponent3Action");

        assertNull(config);
    }

}
