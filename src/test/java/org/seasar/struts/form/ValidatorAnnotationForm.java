package org.seasar.struts.form;

import java.util.Date;

/**
 * @author Katsuhiko Nagashima
 */
public class ValidatorAnnotationForm {
    
    public static final String FORM = "testFormName";

    public ValidatorAnnotationForm() {
    }

    public static final String arg_VALIDATOR = "required";
    public static final String arg_VALIDATOR_ARG = "Arg, resource=false";
    
    public void setArg(String arg) {
    }

    public static final String required_VALIDATOR = "required";
    public static final String required_VALIDATOR_ARG = "Required, resource=false";

    public void setRequired(String required) {
    }

    public static final String integer_VALIDATOR = "integer";
    public static final String integer_VALIDATOR_ARG = "Integer, resource=false";
    
    public void setInteger(String integer) {
    }

    public static final String date_VALIDATOR = "date, pattern=yyyyMMdd";
    public static final String date_VALIDATOR_ARG = "Date, resource=false";
    
    public void setDate(String date) {
    }

    public static final String creditCard_VALIDATOR = "creditCard";
    public static final String creditCard_VALIDATOR_ARG = "CreditCard, resource=false";
    
    public void setCreditCard(String creditCard) {
    }

    public static final String length_VALIDATOR_0 = "minlength, value=3";
    public static final String length_VALIDATOR_1 = "maxlength, value=5";
    public static final String length_VALIDATOR_ARG = "Length, resource=false";
    
    public void setLength(String length) {
    }

    public static final String bytelength_VALIDATOR_0 = "minbytelength, value=3, charset=ISO8859_1";
    public static final String bytelength_VALIDATOR_1 = "maxbytelength, value=5, charset=ISO8859_1";
    public static final String bytelength_VALIDATOR_ARG = "Bytelength, resource=false";
    
    public void setByteLength(String byteLength) {
    }

    public static final String range_VALIDATOR = "floatRange, min=5.0, max=10.1";
    public static final String range_VALIDATOR_ARG = "Range, resource=false";

    public void setRange(String range) {
    }

    public static final String mix_VALIDATOR = "required";
    public static final String mix_VALIDATOR_0 = "minlength, value=10";
    public static final String mix_VALIDATOR_1 = "maxlength, value=15";
    public static final String mix_VALIDATOR_2 = "mask, pattern=com$, messageKey=mustendcom";
    public static final String mix_VALIDATOR_3 = "email";
    public static final String mix_VALIDATOR_ARG = "Mix, resource=false";

    public void setMix(String mix) {
    }
    
    public static final String autoInteger_VALIDATOR_ARG = "Integer, resource=false";
    
    public void setAutoInteger(int integer) {
    }

    public static final String autoDate_VALIDATOR_ARG = "Date, resource=false";
    
    public void setAutoDate(Date date) {
    }

    public static final String noValidateDate_VALIDATOR = "noValidate";
    
    public void setNoValidateDate(Date data) {
    }

}