package org.seasar.struts.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.struts.validator.S2BeanValidatorForm;

/**
 * @author Katsuhiko Nagashima
 */
public class BeanValidatorFormUtil {
    
    private BeanValidatorFormUtil() {
    }
    
    public static Object toBean(Object bean) {
        if (bean instanceof BeanValidatorForm) {
            BeanValidatorForm beanValidatorForm = (BeanValidatorForm) bean;
            WrapDynaBean dynaBean = (WrapDynaBean) beanValidatorForm.getDynaBean();
            return dynaBean.getInstance();
        }
        return bean;
    }

    public static boolean isBeanValidatorForm(HttpServletRequest request, String propertyName) {
        Object value = RequestUtil.getValue(request, propertyName);
        if (value == null) {
            return false;
        }
        return (value instanceof BeanValidatorForm);
    }

    public static Object toBeanValidatorForm(HttpServletRequest request, String propertyName, Object value) {
        Object old = RequestUtil.getValue(request, propertyName);
        return toBeanValidatorForm(old, value);
    }
    
    public static Object toBeanValidatorForm(Object oldForm, Object newForm) {
        if (!(oldForm instanceof BeanValidatorForm)) {
            return null;
        }
        BeanValidatorForm beanForm = (BeanValidatorForm) oldForm;
        
        S2BeanValidatorForm result;
        if (beanForm instanceof S2BeanValidatorForm) {
            result = (S2BeanValidatorForm) oldForm;
        } else {
            result = new S2BeanValidatorForm(beanForm);
        }
        result.initBean(newForm);
        return result;
    }
    
}
