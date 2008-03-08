package org.seasar.struts.factory;

import java.lang.reflect.Method;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.struts.action.ActionAnnotationActionImpl;
import org.seasar.struts.config.ActionPropertyConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ConstantActionAnnotationHandlerTest extends S2TestCase {

    private ActionAnnotationHandler annHandler;

    public void setUp() {
        annHandler = ActionAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void testSessionScopeProperty() throws Exception {
        BeanDesc beanDesc = BeanDescFactory
                .getBeanDesc(ActionAnnotationActionImpl.class);
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc("bar");
        ActionPropertyConfig config = annHandler.createActionPropertyConfig(
                beanDesc, propertyDesc);
        assertNotNull(config);
        assertEquals(true, config.isSessionScope());
    }

    public void testRequestScopeProperty() throws Exception {
        BeanDesc beanDesc = BeanDescFactory
                .getBeanDesc(ActionAnnotationActionImpl.class);
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc("foo");
        ActionPropertyConfig config = annHandler.createActionPropertyConfig(
                beanDesc, propertyDesc);
        assertNotNull(config);
        assertEquals(false, config.isSessionScope());
    }

    public void testGetPath() throws Exception {
        Method method = Hoge.class.getMethod("execute", new Class[] {});
        String path = annHandler.getPath(method);
        assertEquals("/hoge", path);
    }

    public static class Hoge {
        public static final String execute_BINDING_METHOD = "path=/hoge";

        public String execute() {
            return null;
        }
    }

}
