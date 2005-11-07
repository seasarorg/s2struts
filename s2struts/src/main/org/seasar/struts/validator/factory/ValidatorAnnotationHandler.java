package org.seasar.struts.validator.factory;

import org.apache.commons.validator.Form;

/**
 * @author Katsuhiko Nagashima
 */
public interface ValidatorAnnotationHandler {

    public Form createForm(String formName, Class formClass);

}
