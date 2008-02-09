package org.seasar.struts.util;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ModuleConfigUtilForComponentNameTest extends S2TestCase {

    private ModuleConfig moduleConfig = new ModuleConfigImpl();

    private String moduleKey = Globals.MODULE_KEY;

    private boolean useServletContext;

    protected void setUp() throws Exception {
        super.setUp();
        include("ModuleConfigUtilForComponentNameTest.dicon");
    }

    protected void setUpAfterContainerInit() throws Exception {
        if (useServletContext) {
            getServletContext().setAttribute(moduleKey, this.moduleConfig);
        } else {
            getRequest().setAttribute(moduleKey, this.moduleConfig);
        }

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

    public void setUpFindActionConfigForComponentName_module() {
        moduleKey = Globals.MODULE_KEY + "/hoge";
        useServletContext = true;
    }

    public void testFindActionConfigForComponentName_module() {
        ActionConfig config = ModuleConfigUtil.findActionConfigForComponentName("/hoge", "testComponent1Action");

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
