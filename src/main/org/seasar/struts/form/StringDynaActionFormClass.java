package org.seasar.struts.form;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;

/**
 * @author Satoshi Kimura
 */
public class StringDynaActionFormClass extends DynaActionFormClass {

    public StringDynaActionFormClass() {
        super(null);
    }
    
    protected StringDynaActionFormClass(FormBeanConfig config) {
        super(config);
    }

    protected void introspect(FormBeanConfig formBeanConfig) {
        if (formBeanConfig == null) {
            return;
        }
        try {
            super.introspect(formBeanConfig);
        } catch (Exception e) {
            // ignore
        }
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public DynaProperty getDynaProperty(String propertyName) {
        return new DynaProperty(propertyName, String.class);
    }

    public DynaProperty[] getDynaProperties() {
        throw new UnsupportedOperationException();
    }

    public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
        throw new UnsupportedOperationException();
    }

}
