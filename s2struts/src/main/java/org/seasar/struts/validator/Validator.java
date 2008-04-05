package org.seasar.struts.validator;

import org.seasar.struts.validator.exception.ValidatorException;

/**
 * カスタムバリデータのためのインタフェースです。
 * 
 * @author taedium
 */
public interface Validator {

    /**
     * 検証を実行します。
     * 
     * 不正な値なら、ValidatorExceptionをthrowします。 <br>
     */
    void validate(Object value) throws ValidatorException;
}
