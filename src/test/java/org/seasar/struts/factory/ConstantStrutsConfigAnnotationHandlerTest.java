package org.seasar.struts.factory;

import java.lang.reflect.Field;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.StrutsConfigAnnotationAction;
import org.seasar.struts.action.StrutsConfigAnnotationActionImpl;
import org.seasar.struts.form.NoStrutsConfigAnnotationForm;
import org.seasar.struts.form.StrutsConfigAnnotationForm;
import org.seasar.struts.zeroconfig.config.StrutsActionConfig;
import org.seasar.struts.zeroconfig.config.StrutsActionFormConfig;
import org.seasar.struts.zeroconfig.config.StrutsActionForwardConfig;
import org.seasar.struts.zeroconfig.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.zeroconfig.factory.StrutsConfigAnnotationHandlerFactory;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class ConstantStrutsConfigAnnotationHandlerTest extends S2TestCase {
    
    private StrutsConfigAnnotationHandler annHandler;
    
    public void setUp() {
        annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
    }
    
    public void testCreateStrutsActionConfig() {
        StrutsActionConfig config = annHandler.createStrutsActionConfig(StrutsConfigAnnotationAction.class);
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
        assertEquals(true, config.cancellable());
    }
    
    public void testNotCreateStrutsActionConfig() {
        StrutsActionConfig config = annHandler.createStrutsActionConfig(StrutsConfigAnnotationActionImpl.class);
        assertNull(config);
    }
    
    public void testCreateStrutsActionForwardConfig() throws Exception {
        Field field = StrutsConfigAnnotationAction.class.getField("SUCCESS");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNotNull(config);
        assertEquals("/test.jsp", config.path());
        assertEquals(false, config.redirect());
    }
    
    public void testNotCreateStrutsActionForwardConfig() throws Exception {
        Field field = StrutsConfigAnnotationAction.class.getField("CONST");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNull(config);
    }
    
    public void testCreateActionFormConfig() {
        StrutsActionFormConfig config = annHandler.createStrutsActionFormConfig(StrutsConfigAnnotationForm.class);
        assertNotNull(config);
        assertEquals("testFormName", config.name());
        assertEquals(false, config.restricted());
    }

    public void testNotCreateActionFormConfig() {
        StrutsActionFormConfig config = annHandler.createStrutsActionFormConfig(NoStrutsConfigAnnotationForm.class);
        assertNull(config);
    }

}
