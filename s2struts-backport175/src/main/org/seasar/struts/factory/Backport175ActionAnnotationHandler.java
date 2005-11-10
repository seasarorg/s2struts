package org.seasar.struts.factory;

import java.lang.reflect.Method;

import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.Constants;
import org.seasar.struts.annotation.backport175.Export;
import org.seasar.struts.annotation.backport175.ExportToSession;
import org.seasar.struts.config.ActionPropertyConfig;
import org.seasar.struts.config.ActionPropertyConfigImpl;

/**
 * @author Katsuhiko Nagashima
 */
public class Backport175ActionAnnotationHandler extends ConstantActionAnnotationHandler {

    public ActionPropertyConfig createActionPropertyConfig(BeanDesc beanDesc, PropertyDesc propertyDesc) {
        Method readMehod = propertyDesc.getReadMethod();
        ExportToSession toSession = (ExportToSession) Annotations.getAnnotation(ExportToSession.class, readMehod);
        if (toSession != null) {
            return new ActionPropertyConfigImpl(Constants.SESSION);
        }
        Export export = (Export) Annotations.getAnnotation(Export.class, readMehod);
        if (export != null) {
            return new ActionPropertyConfigImpl(export.value());
        }
        return super.createActionPropertyConfig(beanDesc, propertyDesc);
    }
    
}
