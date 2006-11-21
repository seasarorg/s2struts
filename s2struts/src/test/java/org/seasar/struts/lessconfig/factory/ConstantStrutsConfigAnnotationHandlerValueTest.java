package org.seasar.struts.lessconfig.factory;

import java.lang.reflect.Field;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.config.StrutsActionForwardConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ConstantStrutsConfigAnnotationHandlerValueTest extends S2TestCase {

    private StrutsConfigAnnotationHandler annHandler;

    public void setUp() {
        annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void testValue1() throws Exception {
        Field field = TestStrutsConfigAnnotationValueAction.class.getField("SUCCESS1");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNotNull(config);
        assertEquals("/test.jsp?aaa=bbb", config.path());
        assertEquals(Boolean.TRUE, config.redirect());
    }

    public void testValue2() throws Exception {
        Field field = TestStrutsConfigAnnotationValueAction.class.getField("SUCCESS2");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNotNull(config);
        assertEquals("/test.jsp", config.path());
        assertEquals(Boolean.TRUE, config.redirect());
    }

    public void testValue3() throws Exception {
        Field field = TestStrutsConfigAnnotationValueAction.class.getField("SUCCESS3");
        StrutsActionForwardConfig config = annHandler.createStrutsActionForwardConfig(field);
        assertNotNull(config);
        assertEquals("", config.path());
        assertEquals(Boolean.TRUE, config.redirect());
    }

}
