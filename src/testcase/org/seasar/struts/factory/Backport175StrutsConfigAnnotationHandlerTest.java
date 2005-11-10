package org.seasar.struts.factory;

import java.lang.reflect.Field;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.TestAction;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;
import org.seasar.struts.form.TestForm;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class Backport175StrutsConfigAnnotationHandlerTest extends S2TestCase {
    
    private StrutsConfigAnnotationHandler annHandler;
    
    public void setUp() {
        annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
    }
    
    public void testCreateStrutsActionConfig() {
        StrutsActionConfig config = annHandler.createStrutsActionConfig(TestAction.class);
        assertNotNull(config);
        assertEquals("testpath", config.path());
        assertEquals("testname", config.name());
        assertEquals("testinput", config.input());
        assertEquals("testparameter", config.parameter());
        assertEquals("testattribute", config.attribute());
        assertEquals("testforward", config.forward());
        assertEquals("testinclude", config.include());
        assertEquals("testprefix", config.prefix());
        assertEquals("testsuffix", config.suffix());
        assertEquals("testroles", config.roles());
        assertEquals("request", config.scope());
        assertEquals(true, config.validate());
        assertEquals(false, config.unknown());
    }
    
    public void testCreateStrutsActionForwardConfig() throws Exception {
        Field field = TestAction.class.getField("SUCCESS");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNotNull(config);
        assertEquals("/test.jsp", config.path());
        assertEquals(false, config.redirect());
    }
    
    public void testCreateActionFormConfig() {
        StrutsActionFormConfig config = annHandler.createStrutsActionFormConfig(TestForm.class);
        assertNotNull(config);
        assertEquals("testFormName", config.name());
        assertEquals(false, config.restricted());
    }

}
