package org.seasar.struts.util;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ModuleConfigUtilForFormBeanNameTest extends S2TestCase {

    private ModuleConfig moduleConfig = new ModuleConfigImpl();

    protected void setUp() throws Exception {

    }

    protected void setUpAfterContainerInit() throws Exception {
        getRequest().setAttribute(Globals.MODULE_KEY, this.moduleConfig);

        FormBeanConfig beanConfig1 = new FormBeanConfig();
        beanConfig1.setName("findForm");

        FormBeanConfig beanConfig2 = new FormBeanConfig();
        beanConfig2.setName("noActionForm");

        ActionConfig actionConfig1 = new ActionMapping();
        actionConfig1.setName("findForm");
        actionConfig1.setPath("/find1");

        ActionConfig actionConfig2 = new ActionMapping();
        actionConfig2.setName("findForm");
        actionConfig2.setPath("/find2");

        ActionConfig actionConfig3 = new ActionMapping();
        actionConfig3.setName("noForm");
        actionConfig3.setPath("/find3");

        this.moduleConfig.addFormBeanConfig(beanConfig1);
        this.moduleConfig.addFormBeanConfig(beanConfig2);
        this.moduleConfig.addActionConfig(actionConfig1);
        this.moduleConfig.addActionConfig(actionConfig2);
        this.moduleConfig.addActionConfig(actionConfig3);
    }

    public void testFindActionConfigForFormBeanName() {
        ActionConfig config = ModuleConfigUtil
                .findActionConfigForFormBeanName("findForm");

        assertNotNull(config);
        assertEquals("findForm", config.getName());
        assertEquals("/find1", config.getPath());
    }

    public void testNotFoundActionConfigForFormBeanName1() {
        ActionConfig config = ModuleConfigUtil
                .findActionConfigForFormBeanName("noActionForm");

        assertNull(config);
    }

    public void testNotFoundActionConfigForFormBeanName2() {
        ActionConfig config = ModuleConfigUtil
                .findActionConfigForFormBeanName("noForm");

        assertNull(config);
    }

    public void testFindActionConfigsForFormBeanName() {
        ActionConfig[] configs = ModuleConfigUtil
                .findActionConfigsForFormBeanName("findForm");

        assertEquals(2, configs.length);
        assertEquals("findForm", configs[0].getName());
        assertEquals("/find1", configs[0].getPath());
        assertEquals("findForm", configs[1].getName());
        assertEquals("/find2", configs[1].getPath());
    }

    public void testNotFoundActionConfigsForFormBeanName1() {
        ActionConfig[] configs = ModuleConfigUtil
                .findActionConfigsForFormBeanName("noActionForm");

        assertEquals(0, configs.length);
    }

    public void testNotFoundActionConfigsForFormBeanName2() {
        ActionConfig[] configs = ModuleConfigUtil
                .findActionConfigsForFormBeanName("noForm");

        assertEquals(0, configs.length);
    }

}
