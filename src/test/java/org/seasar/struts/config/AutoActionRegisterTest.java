package org.seasar.struts.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class AutoActionRegisterTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        include("s2struts.dicon");
        include("AutoActionRegisterTest.dicon");
    }

    public void testRegisterSimpleComponentName() {
        ServletContext context = getServletContext();
        ModuleConfig config = new ModuleConfigImpl("");
        List classes = new ArrayList();
        classes.add(TestSimpleAction.class);

        AutoActionRegister.register(context, config, classes);
        assertEquals(1, config.findActionConfigs().length);

        ActionConfig actionConfig = config.findActionConfigs()[0];
        assertEquals("/simple", actionConfig.getPath());
    }

    public void testRegisterSlashComponentName() {
        ServletContext context = getServletContext();
        ModuleConfig config = new ModuleConfigImpl("");
        List classes = new ArrayList();
        classes.add(TestSlashAction.class);

        AutoActionRegister.register(context, config, classes);
        assertEquals(1, config.findActionConfigs().length);

        ActionConfig actionConfig = config.findActionConfigs()[0];
        assertEquals("/slash", actionConfig.getPath());
    }

    public void testRegisterNoRegisteredComponent() {
        ServletContext context = getServletContext();
        ModuleConfig config = new ModuleConfigImpl("");
        List classes = new ArrayList();
        classes.add(TestNoRegisteredComponentAction.class);

        AutoActionRegister.register(context, config, classes);
        assertEquals(1, config.findActionConfigs().length);

        ActionConfig actionConfig = config.findActionConfigs()[0];
        assertEquals("/testNoRegisteredComponent", actionConfig.getPath());
    }

    public void testNotRegister() {
        ServletContext context = getServletContext();
        ModuleConfig config = new ModuleConfigImpl("");
        List classes = new ArrayList();
        classes.add(TestIgnoreTarget.class);

        AutoActionRegister.register(context, config, classes);
        assertEquals(0, config.findActionConfigs().length);
    }

}
