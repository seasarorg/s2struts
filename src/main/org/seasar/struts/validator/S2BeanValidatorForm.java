package org.seasar.struts.validator;

import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.validator.BeanValidatorForm;

/**
 * @author Katsuhiko Nagashima
 */
public class S2BeanValidatorForm extends BeanValidatorForm {

    public S2BeanValidatorForm(BeanValidatorForm form) {
        super(form.getDynaBean());
        if (form.getMultipartRequestHandler() != null) {
            setMultipartRequestHandler(form.getMultipartRequestHandler());
            setServlet(form.getMultipartRequestHandler().getServlet());
        }
    }
    
    public void initBean(Object bean) {
        dynaBean = new WrapDynaBean(bean);
    }
    

}
