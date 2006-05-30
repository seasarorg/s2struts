/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.pojo.factory;

import java.lang.reflect.Method;

import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.Constants;
import org.seasar.struts.annotation.backport175.Export;
import org.seasar.struts.annotation.backport175.ExportToSession;
import org.seasar.struts.pojo.config.ActionPropertyConfig;
import org.seasar.struts.pojo.config.ActionPropertyConfigImpl;

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
