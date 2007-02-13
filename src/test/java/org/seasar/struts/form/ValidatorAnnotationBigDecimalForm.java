package org.seasar.struts.form;

import java.math.BigDecimal;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ValidatorAnnotationBigDecimalForm {

    public static final String bigDecimal_VALIDATOR = "required";

    public static final String bigDecimal_VALIDATOR_ARGS = "bigDecimal, resource=false";

    public void setBigDecimal(BigDecimal value) {
    }

    public BigDecimal getBigDecimal() {
        return null;
    }

    public void setNoValidateBigDecimal(BigDecimal value) {
    }

    public BigDecimal getNoValidateBigDecimal() {
        return null;
    }

}
