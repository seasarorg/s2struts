package org.seasar.struts.lessconfig.factory;

import java.lang.reflect.Field;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.config.StrutsActionConfig;
import org.seasar.struts.lessconfig.config.StrutsActionFormConfig;
import org.seasar.struts.lessconfig.config.StrutsActionForwardConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TigerStrutsConfigAnnotationHandlerTest extends S2TestCase {

    private StrutsConfigAnnotationHandler annHandler;

    public void setUp() {
        annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void testCreateStrutsActionConfig() {
        StrutsActionConfig config = annHandler
                .createStrutsActionConfig(TestStrutsConfigAnnotationAction.class);
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
        assertEquals(Boolean.TRUE, config.validate());
        assertEquals(Boolean.FALSE, config.unknown());
        assertEquals(Boolean.TRUE, config.cancellable());
    }

    public void testNotCreateStrutsActionConfig() {
        StrutsActionConfig config = annHandler
                .createStrutsActionConfig(TestStrutsConfigAnnotationActionImpl.class);
        assertNull(config);
    }

    public void testCreateStrutsActionForwardConfig() throws Exception {
        Field field = TestStrutsConfigAnnotationAction.class.getField("SUCCESS");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNotNull(config);
        assertEquals("/test.jsp", config.path());
        assertEquals(Boolean.FALSE, config.redirect());
    }

    public void testNotCreateStrutsActionForwardConfig() throws Exception {
        Field field = TestStrutsConfigAnnotationAction.class.getField("CONST");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNull(config);
    }

    public void testCreateActionFormConfig() {
        StrutsActionFormConfig config = annHandler
                .createStrutsActionFormConfig(TestStrutsConfigAnnotationForm.class);
        assertNotNull(config);
        assertEquals("testFormName", config.name());
        assertEquals(Boolean.FALSE, config.restricted());
    }

    public void testNotCreateActionFormConfig() {
        StrutsActionFormConfig config = annHandler
                .createStrutsActionFormConfig(TestNoStrutsConfigAnnotationForm.class);
        assertNull(config);
    }

}
