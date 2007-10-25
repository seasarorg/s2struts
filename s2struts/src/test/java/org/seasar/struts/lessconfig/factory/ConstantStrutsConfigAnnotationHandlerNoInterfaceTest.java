package org.seasar.struts.lessconfig.factory;

import java.lang.reflect.Field;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.config.NullStrutsActionConfig;
import org.seasar.struts.lessconfig.config.StrutsActionConfig;
import org.seasar.struts.lessconfig.config.StrutsActionForwardConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ConstantStrutsConfigAnnotationHandlerNoInterfaceTest extends S2TestCase {

    private StrutsConfigAnnotationHandler annHandler;

    public void setUp() {
        annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void testCreateStrutsActionConfig() {
        StrutsActionConfig config = annHandler
                .createStrutsActionConfig(TestStrutsConfigAnnotationImplementAction.class);
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
        StrutsActionConfig config = annHandler.createStrutsActionConfig(TestStrutsConfigAnnotationActionImpl.class);
        assertEquals(NullStrutsActionConfig.INSTANCE, config);
    }

    public void testCreateStrutsActionForwardConfig() throws Exception {
        Field field = TestStrutsConfigAnnotationImplementAction.class.getField("SUCCESS");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNotNull(config);
        assertEquals("/test.jsp", config.path());
        assertEquals(Boolean.FALSE, config.redirect());
    }

    public void testNotCreateStrutsActionForwardConfig() throws Exception {
        Field field = TestStrutsConfigAnnotationImplementAction.class.getField("CONST");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNull(config);
    }

}
