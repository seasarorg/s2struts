package org.seasar.struts.factory;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.struts.action.ExportSessionPropertyActionImpl;
import org.seasar.struts.config.ActionPropertyConfig;

/**
 * @author Katsuhiko Nagashima
 */
public class Backport175ActionAnnotationHandlerTest extends S2TestCase {

    private ActionAnnotationHandler annHandler;
    
    public void setUp() {
        annHandler = ActionAnnotationHandlerFactory.getAnnotationHandler();
    }
    
    public void testCreateStrutsActionPropertyConfigByExportToSession() throws Exception {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(ExportSessionPropertyActionImpl.class);
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc("qux");
        ActionPropertyConfig config = annHandler.createActionPropertyConfig(beanDesc, propertyDesc);
        assertNotNull(config);
        assertEquals(true, config.isSessionScope());
    }
    
}
