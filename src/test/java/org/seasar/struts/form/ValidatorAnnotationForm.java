package org.seasar.struts.form;

import java.util.Date;

import org.apache.struts.upload.FormFile;

/**
 * @author Katsuhiko Nagashima
 */
public class ValidatorAnnotationForm {
    
    public static final String FORM = "testFormName";

    public ValidatorAnnotationForm() {
    }

    public static final String arg_VALIDATOR = "required";
    public static final String arg_VALIDATOR_ARGS = "Arg, resource=false";
    
    public void setArg(String arg) {
    }

    public static final String args_VALIDATOR = "required";
    public static final String args_VALIDATOR_ARGS = "Arg0\n Arg1\n Arg2, resource=false";
    
    public void setArgs(String args) {
    }

    public static final String required_VALIDATOR = "required";
    public static final String required_VALIDATOR_ARGS = "Required, resource=false";

    public void setRequired(String required) {
    }

    public static final String integer_VALIDATOR = "integer";
    public static final String integer_VALIDATOR_ARGS = "Integer, resource=false";
    
    public void setInteger(String integer) {
    }

    public static final String date_VALIDATOR = "date, pattern=yyyyMMdd";
    public static final String date_VALIDATOR_ARGS = "Date, resource=false";
    
    public void setDate(String date) {
    }

    public static final String creditCard_VALIDATOR = "creditCard";
    public static final String creditCard_VALIDATOR_ARGS = "CreditCard, resource=false";
    
    public void setCreditCard(String creditCard) {
    }

    public static final String length_VALIDATOR_0 = "minlength, value=3";
    public static final String length_VALIDATOR_1 = "maxlength, value=5";
    public static final String length_VALIDATOR_ARGS = "Length, resource=false";
    
    public void setLength(String length) {
    }

    public static final String bytelength_VALIDATOR_0 = "minbytelength, value=3, charset=ISO8859_1";
    public static final String bytelength_VALIDATOR_1 = "maxbytelength, value=5, charset=ISO8859_1";
    public static final String bytelength_VALIDATOR_ARGS = "Bytelength, resource=false";
    
    public void setByteLength(String byteLength) {
    }

    public static final String range_VALIDATOR = "floatRange, min=5.0, max=10.1";
    public static final String range_VALIDATOR_ARGS = "Range, resource=false";

    public void setRange(String range) {
    }

    public static final String mix_VALIDATOR = "required";
    public static final String mix_VALIDATOR_0 = "minlength, value=10";
    public static final String mix_VALIDATOR_1 = "maxlength, value=15";
    public static final String mix_VALIDATOR_2 = "mask, pattern=com$, messageKey=mustendcom";
    public static final String mix_VALIDATOR_3 = "email";
    public static final String mix_VALIDATOR_ARGS = "Mix, resource=false";

    public void setMix(String mix) {
    }
    
    public static final String autoInteger_VALIDATOR_ARGS = "Integer, resource=false";
    
    public void setAutoInteger(int integer) {
    }

    public static final String autoDate_VALIDATOR_ARGS = "Date, resource=false";
    
    public void setAutoDate(Date date) {
    }
    
    public void setNoValidate(String noValidate) {
    }

    public static final String noValidateDate_VALIDATOR = "noValidate";
    
    public void setNoValidateDate(Date data) {
    }

    public static final String array_VALIDATOR = "required";
    public static final String array_VALIDATOR_ARGS = "Array, resource=false";
    
    public void setArray(String[] arrasy) {
    }

    public static final String autoArray_VALIDATOR_ARGS = "AutoArray, resource=false";
    
    public void setAutoArray(int[] autoArray) {
    }
    
    public void setChild(ValidatorAnnotationChildForm child) {
    }
    
    public void setChildren(ValidatorAnnotationChildForm[] children) {
    }
    
    public static final String self_VALIDATOR = "noValidate";

    public void setSelf(ValidatorAnnotationForm self) {
    }
    
    public static final String file_VALIDATOR = "required";
    public static final String file_VALIDATOR_ARGS = "File, resource=false";
    
    public void setFile(FormFile file) {
    }
    
}