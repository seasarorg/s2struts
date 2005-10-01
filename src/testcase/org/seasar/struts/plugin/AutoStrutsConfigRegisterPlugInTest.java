package org.seasar.struts.plugin;

import java.util.Locale;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ActionConfigMatcher;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.validator.ValidatorPlugIn;
import org.seasar.struts.unit.S2StrutsTestCase;

/**
 * @author Satoshi Kimura
 */
public class AutoStrutsConfigRegisterPlugInTest extends S2StrutsTestCase {

    public AutoStrutsConfigRegisterPlugInTest(String name) {
        super(name);
    }

    public void setUp() {
        include("app.dicon");
        include("HttpMockObject.dicon");
        setDocBase("");
    }

    public void testInit() throws Exception {
        ActionServlet servlet = (ActionServlet) getHttpServlet("action");

        ModuleConfig moduleConfig = new TestModuleConfig();
        AutoStrutsConfigRegisterPlugIn plugin = new AutoStrutsConfigRegisterPlugIn();
        plugin.setEnableJar(false);
        plugin.setActionClassPattern("org.seasar.struts.plugin.action.*Action");
        plugin.setFormClassPattern("org.seasar.struts.plugin.form.*Form");
        plugin.init(servlet, moduleConfig);

        FormBeanConfig[] formBeanConfigs = moduleConfig.findFormBeanConfigs();
        assertEquals(3, formBeanConfigs.length);
        
        FormBeanConfig formBeanConfig = moduleConfig.findFormBeanConfig("testFormName");
        assertEquals(false, formBeanConfig.getDynamic());
        assertEquals("testFormName", formBeanConfig.getName());
        assertEquals("org.seasar.struts.plugin.form.TestForm", formBeanConfig.getType());

        formBeanConfig = moduleConfig.findFormBeanConfig("zeroConfigForm");
        assertEquals(false, formBeanConfig.getDynamic());
        assertEquals("zeroConfigForm", formBeanConfig.getName());
        assertEquals("org.seasar.struts.plugin.form.ZeroConfigForm", formBeanConfig.getType());

        ActionConfig[] actionConfigs = moduleConfig.findActionConfigs();
        assertEquals(3, actionConfigs.length);
        ActionConfig testActionConfig = moduleConfig.findActionConfig("testpath");
        assertEquals("testattribute", testActionConfig.getAttribute());
        assertEquals(true, testActionConfig.getValidate());
        assertEquals(false, testActionConfig.getUnknown());
        assertEquals("testforward", testActionConfig.getForward());
        assertEquals("testinclude", testActionConfig.getInclude());
        assertEquals("testinput", testActionConfig.getInput());
        assertEquals("testname", testActionConfig.getName());
        assertEquals("testparameter", testActionConfig.getParameter());
        assertEquals("testpath", testActionConfig.getPath());
        assertEquals("testprefix", testActionConfig.getPrefix());
        assertEquals("testroles", testActionConfig.getRoles());
        assertEquals("testsuffix", testActionConfig.getSuffix());
        assertEquals("org.seasar.struts.plugin.action.TestAction", testActionConfig
                .getType());
        assertEquals("request", testActionConfig.getScope());

        ForwardConfig[] forwardConfigs = testActionConfig.findForwardConfigs();
        assertEquals(2, forwardConfigs.length);
        assertEquals(false, forwardConfigs[0].getRedirect());
        assertEquals(true, forwardConfigs[0].getName().matches("success|fail"));
        assertEquals(true, forwardConfigs[0].getPath().matches(
                "/test.jsp|/fail.jsp"));
        assertEquals(false, forwardConfigs[1].getRedirect());
        assertEquals(true, forwardConfigs[1].getName().matches("success|fail"));
        assertEquals(true, forwardConfigs[1].getPath().matches(
                "/test.jsp|/fail.jsp"));
        
        ActionConfig zeroConfigActionConfig = moduleConfig.findActionConfig("/zeroConfig");
        assertEquals("zeroConfigForm", zeroConfigActionConfig.getName());
        assertEquals("/zeroConfig", zeroConfigActionConfig.getPath());
        assertEquals(true, zeroConfigActionConfig.getValidate());
        assertEquals("org.seasar.struts.plugin.action.ZeroConfigAction", zeroConfigActionConfig
                .getType());
        assertEquals("request", zeroConfigActionConfig.getScope());

        forwardConfigs = zeroConfigActionConfig.findForwardConfigs();
        assertEquals(1, forwardConfigs.length);
        assertEquals(false, forwardConfigs[0].getRedirect());
        assertEquals("success", forwardConfigs[0].getName());
        assertEquals("/zeroConfig.jsp", forwardConfigs[0].getPath());

        ActionConfig zeroConfigActionConfig2 = moduleConfig.findActionConfig("/zeroConfig2");
        forwardConfigs = zeroConfigActionConfig2.findForwardConfigs();
        assertEquals("success", forwardConfigs[0].getName());
        assertEquals("/test/zeroConfig2.html", forwardConfigs[0].getPath());

    }

    public void testInitFullAnnotationValidationOf() throws Exception {
        ActionServlet servlet = (ActionServlet) getHttpServlet("action");

        ModuleConfig moduleConfig = new TestModuleConfig();
        AutoStrutsConfigRegisterPlugIn plugin = new AutoStrutsConfigRegisterPlugIn();
        plugin.setEnableJar(false);
        plugin.setActionClassPattern("org.seasar.struts.plugin.action.*Action");
        plugin.setFormClassPattern("org.seasar.struts.plugin.form.*Form");
        plugin.init(servlet, moduleConfig);

        ValidatorResources resources = (ValidatorResources) servlet
                .getServletContext()
                .getAttribute(ValidatorPlugIn.VALIDATOR_KEY);

        Form form = resources.getForm(Locale.getDefault(), "testFormName");
        assertEquals("testFormName", form.getName());

        Field field = form.getField("message1");
        // Dependsの順番について考慮する必要がある
        assertEquals("intRange,integer,maxlength,required", field.getDepends());

        assertEquals("3", field.getVarValue("maxlength"));
        assertEquals("10", field.getVarValue("min"));
        assertEquals("100", field.getVarValue("max"));

        assertEquals("form.message1", field.getArg(0).getKey());
        assertEquals(true, field.getArg(0).isResource());
        assertEquals("3", field.getArg("maxlength", 1).getKey());
        assertEquals("10", field.getArg("intRange", 1).getKey());
        assertEquals("100", field.getArg("intRange", 2).getKey());
    }

    public void testInitSimpleAnnotationValidation() throws Exception {
        ActionServlet servlet = (ActionServlet) getHttpServlet("action");

        ModuleConfig moduleConfig = new TestModuleConfig();
        AutoStrutsConfigRegisterPlugIn plugin = new AutoStrutsConfigRegisterPlugIn();
        plugin.setEnableJar(false);
        plugin.setActionClassPattern("org.seasar.struts.plugin.action.*Action");
        plugin.setFormClassPattern("org.seasar.struts.plugin.form.*Form");
        plugin.init(servlet, moduleConfig);

        ValidatorResources resources = (ValidatorResources) servlet
                .getServletContext()
                .getAttribute(ValidatorPlugIn.VALIDATOR_KEY);

        Form form = resources.getForm(Locale.getDefault(), "testFormName");
        assertEquals("testFormName", form.getName());

        Field field = form.getField("message2");
        // Dependsの順番について考慮する必要がある
        assertEquals("intRange,integer,maxlength,required", field.getDepends());

        assertEquals("3", field.getVarValue("maxlength"));
        assertEquals("10", field.getVarValue("min"));
        assertEquals("100", field.getVarValue("max"));

        assertEquals("form.message2", field.getArg(0).getKey());
        assertEquals(true, field.getArg(0).isResource());
        assertEquals("3", field.getArg("maxlength", 1).getKey());
        assertEquals("10", field.getArg("intRange", 1).getKey());
        assertEquals("100", field.getArg("intRange", 2).getKey());
    }

    public void testInitXmlValidation() throws Exception {
        // accord validation.xml priority over "ANNOTATION"
        
        ActionServlet servlet = (ActionServlet) getHttpServlet("action");

        ModuleConfig moduleConfig = new TestModuleConfig();
        AutoStrutsConfigRegisterPlugIn plugin = new AutoStrutsConfigRegisterPlugIn();
        plugin.setEnableJar(false);
        plugin.setActionClassPattern("org.seasar.struts.plugin.action.*Action");
        plugin.setFormClassPattern("org.seasar.struts.plugin.form.*Form");
        plugin.init(servlet, moduleConfig);

        ValidatorResources resources = (ValidatorResources) servlet
                .getServletContext()
                .getAttribute(ValidatorPlugIn.VALIDATOR_KEY);

        Form form = resources.getForm(Locale.getDefault(), "testXmlValidatorFormName");
        assertEquals("testXmlValidatorFormName", form.getName());

        Field field = form.getField("message1");
        assertEquals("required,maxlength", field.getDepends());

        assertEquals("5", field.getVarValue("maxlength"));

        assertEquals("form.xml.message1", field.getArg(0).getKey());
        assertEquals(true, field.getArg(0).isResource());
        assertEquals("5", field.getArg("maxlength", 1).getKey());
    }

    private static class TestModuleConfig extends ModuleConfigImpl {
        public TestModuleConfig() {
            super("");
            matcher = new ActionConfigMatcher(new ActionConfig[0]);
        }
    }

}