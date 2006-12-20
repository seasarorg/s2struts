package org.seasar.struts.lessconfig.factory;

import java.util.Date;

import org.apache.struts.upload.FormFile;

/**
 * @author Katsuhiko Nagashima
 */
public class TestValidatorAnnotationForm {

    public static final String FORM = "testFormName";

    public TestValidatorAnnotationForm() {
    }

    public static final String arg_VALIDATOR = "required";

    public static final String arg_VALIDATOR_ARGS = "Arg, resource=false";

    public void setArg(String arg) {
    }

    public static final String args_VALIDATOR = "required";

    public static final String args_VALIDATOR_ARGS = "Arg0\n Arg1\n Arg2, resource=false";

    public void setArgs(String args) {
    }

    public static final String argDefaultResource_VALIDATOR = "required";

    public static final String argDefaultResource_VALIDATOR_ARGS = "Arg";

    public void setArgDefaultResource(String argDefaultResource) {
    }

    public static final String message_VALIDATOR = "required";

    public static final String message_VALIDATOR_MESSAGE = "name=required, key=myrequired";

    public static final String message_VALIDATOR_ARGS = "Message, resource=false";

    public void setMessage(String message) {
    }

    public static final String message2_VALIDATOR = "required";

    public static final String message2_VALIDATOR_MESSAGE = "name=required, key=my2required, bundle=myapp, resource=false";

    public static final String message2_VALIDATOR_ARGS = "Message2, resource=false";

    public void setMessage2(String message) {
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

    public static final String byteLength_VALIDATOR_0 = "minbytelength, value=3, charset=ISO8859_1";

    public static final String byteLength_VALIDATOR_1 = "maxbytelength, value=5, charset=ISO8859_1";

    public static final String byteLength_VALIDATOR_ARGS = "Bytelength, resource=false";

    public void setByteLength(String byteLength) {
    }

    public static final String range_VALIDATOR = "floatRange, min=5.0, max=10.1";

    public static final String range_VALIDATOR_ARGS = "Range, resource=false";

    public void setRange(String range) {
    }

    public static final String longRange_VALIDATOR = "longRange, min=5, max=10";

    public static final String longRange_VALIDATOR_ARGS = "LongRange, resource=false";

    public void setLongRange(String longRange) {
    }

    public static final String mask_VALIDATOR = "mask, pattern='(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)', messageKey=comma";

    public void setMask(String mask) {
    }

    public static final String mask2_VALIDATOR = "mask, pattern='(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)'";

    public static final String mask2_VALIDATOR_ARGS = "Mask2, resource=false";

    public void setMask2(String mask2) {
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

    public void setArray(String[] arrays) {
    }

    public static final String autoArray_VALIDATOR_ARGS = "AutoArray, resource=false";

    public void setAutoArray(int[] autoArray) {
    }

    public void setChild(TestValidatorAnnotationChildForm child) {
    }

    public void setChildren(TestValidatorAnnotationChildForm[] children) {
    }

    public static final String self_VALIDATOR = "noValidate";

    public void setSelf(TestValidatorAnnotationForm self) {
    }

    public static final String file_VALIDATOR = "required";

    public static final String file_VALIDATOR_ARGS = "File, resource=false";

    public void setFile(FormFile file) {
    }

    public String notConstantValidator_VALIDATOR = "required";

    public static final String notConstantValidator_VALIDATOR_ARGS = "NotConstantValidator, resource=false";

    public void setNotConstantValidator(String notConstantValidator) {
    }

    public static final String notConstantValidatorArg_VALIDATOR = "required";

    public String notConstantValidatorArg_VALIDATOR_ARGS = "not.constant.validator.arg, resource=true";

    public void setNotConstantValidatorArg(String notConstantValidatorArg) {
    }

}