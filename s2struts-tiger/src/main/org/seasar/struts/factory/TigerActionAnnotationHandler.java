package org.seasar.struts.factory;

import java.lang.reflect.Method;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.Constants;
import org.seasar.struts.annotation.tiger.Export;
import org.seasar.struts.annotation.tiger.ExportToSession;
import org.seasar.struts.config.ActionPropertyConfig;
import org.seasar.struts.config.ActionPropertyConfigImpl;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class TigerActionAnnotationHandler extends ConstantActionAnnotationHandler {

    public ActionPropertyConfig createActionPropertyConfig(BeanDesc beanDesc, PropertyDesc propertyDesc) {
        Method readMehod = propertyDesc.getReadMethod();
        ExportToSession toSession = readMehod.getAnnotation(ExportToSession.class);
        if (toSession != null) {
            return new ActionPropertyConfigImpl(Constants.SESSION);
        }
        Export export = readMehod.getAnnotation(Export.class);
        if (export != null) {
            return new ActionPropertyConfigImpl(export.value().getScopeMode());
        }
        return super.createActionPropertyConfig(beanDesc, propertyDesc);
    }

}
