package org.seasar.struts.validator;

import org.seasar.struts.validator.exception.ValidatorException;

public interface Validator {
    /**
     * 不正な値なら、ValidatorExceptionをthrowします。 <br>
     */
    void validate(Object value) throws ValidatorException;
}
