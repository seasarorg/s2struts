package org.seasar.struts.pojo.factory;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.struts.pojo.config.ActionPropertyConfig;
import org.seasar.struts.pojo.factory.ActionAnnotationHandler;
import org.seasar.struts.pojo.factory.ActionAnnotationHandlerFactory;

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
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(TestActionAnnotationActionImpl.class);
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc("bar");
        ActionPropertyConfig config = annHandler.createActionPropertyConfig(beanDesc, propertyDesc);
        assertNotNull(config);
        assertEquals(true, config.isSessionScope());
    }

    public void testRequestScopeProperty() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(TestActionAnnotationActionImpl.class);
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc("foo");
        ActionPropertyConfig config = annHandler.createActionPropertyConfig(beanDesc, propertyDesc);
        assertNotNull(config);
        assertEquals(false, config.isSessionScope());
    }

}
