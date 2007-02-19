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

    public String getArg() {
        return null;
    }

    public static final String arg2_VALIDATOR = "required";

    public static final String arg2_VALIDATOR_ARGS = "Arg2, bundle=myapp, resource=false";

    public void setArg2(String arg) {
    }

    public String getArg2() {
        return null;
    }

    public static final String arg3_VALIDATOR = "required";

    public static final String arg3_VALIDATOR_ARG = "Arg3.1";

    public static final String arg3_VALIDATOR_ARG_0 = "Arg3.2, bundle=myapp, resource=false";

    public static final String arg3_VALIDATOR_ARG_1 = "Arg3.1-other, name=other, bundle=myapp, resource=false, position=0";

    public void setArg3(String arg3) {
    }

    public String getArg3() {
        return null;
    }

    public static final String args_VALIDATOR = "required";

    public static final String args_VALIDATOR_ARGS = "Arg0\n Arg1\n Arg2, resource=false";

    public void setArgs(String args) {
    }

    public String getArgs() {
        return null;
    }

    public static final String argDefaultResource_VALIDATOR = "required";

    public static final String argDefaultResource_VALIDATOR_ARGS = "Arg";

    public void setArgDefaultResource(String argDefaultResource) {
    }

    public String getArgDefaultResource() {
        return null;
    }

    public static final String message_VALIDATOR = "required";

    public static final String message_VALIDATOR_MESSAGE = "name=required, key=myrequired";

    public static final String message_VALIDATOR_ARGS = "Message, resource=false";

    public void setMessage(String message) {
    }

    public String getMessage() {
        return null;
    }

    public static final String message2_VALIDATOR = "required";

    public static final String message2_VALIDATOR_MESSAGE = "name=required, key=my2required, bundle=myapp, resource=false";

    public static final String message2_VALIDATOR_ARGS = "Message2, resource=false";

    public void setMessage2(String message) {
    }

    public String getMessage2() {
        return null;
    }

    public static final String messages_VALIDATOR_0 = "required";

    public static final String messages_VALIDATOR_1 = "integer";

    public static final String messages_VALIDATOR_MESSAGE_0 = "name=required, key=myrequired";

    public static final String messages_VALIDATOR_MESSAGE_1 = "name=integer, key=myinteger";

    public static final String messages_VALIDATOR_ARGS = "Messages, resource=false";

    public void setMessages(String messages) {
    }

    public String getMessages() {
        return null;
    }

    public static final String required_VALIDATOR = "required";

    public static final String required_VALIDATOR_ARGS = "Required, resource=false";

    public void setRequired(String required) {
    }

    public String getRequired() {
        return null;
    }

    public static final String integer_VALIDATOR = "integer";

    public static final String integer_VALIDATOR_ARGS = "Integer, resource=false";

    public void setInteger(String integer) {
    }

    public String getInteger() {
        return null;
    }

    public static final String date_VALIDATOR = "date, pattern=yyyyMMdd";

    public static final String date_VALIDATOR_ARGS = "Date, resource=false";

    public void setDate(String date) {
    }

    public String getDate() {
        return null;
    }

    public static final String strictDate_VALIDATOR = "date, pattern=yyyyMMdd, strict=true";

    public static final String strictDate_VALIDATOR_ARGS = "StrictDate, resource=false";

    public void setStrictDate(String date) {
    }

    public String getStrictDate() {
        return null;
    }

    public static final String creditCard_VALIDATOR = "creditCard";

    public static final String creditCard_VALIDATOR_ARGS = "CreditCard, resource=false";

    public void setCreditCard(String creditCard) {
    }

    public String getCreditCard() {
        return null;
    }

    public static final String length_VALIDATOR_0 = "minlength, value=3";

    public static final String length_VALIDATOR_1 = "maxlength, value=5";

    public static final String length_VALIDATOR_ARGS = "Length, resource=false";

    public void setLength(String length) {
    }

    public String getLength() {
        return null;
    }

    public static final String byteLength_VALIDATOR_0 = "minbytelength, value=3, charset=ISO8859_1";

    public static final String byteLength_VALIDATOR_1 = "maxbytelength, value=5, charset=ISO8859_1";

    public static final String byteLength_VALIDATOR_ARGS = "Bytelength, resource=false";

    public void setByteLength(String byteLength) {
    }

    public String getByteLength() {
        return null;
    }

    public static final String range_VALIDATOR = "floatRange, min=5.0, max=10.1";

    public static final String range_VALIDATOR_ARGS = "Range, resource=false";

    public void setRange(String range) {
    }

    public String getRange() {
        return null;
    }

    public static final String longRange_VALIDATOR = "longRange, min=5, max=10";

    public static final String longRange_VALIDATOR_ARGS = "LongRange, resource=false";

    public void setLongRange(String longRange) {
    }

    public String getLongRange() {
        return null;
    }

    public static final String mask_VALIDATOR = "mask, pattern='(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)', messageKey=comma";

    public static final String mask_VALIDATOR_ARGS = "Mask, resource=false";

    public void setMask(String mask) {
    }

    public String getMask() {
        return null;
    }

    public static final String mask2_VALIDATOR = "mask, pattern='(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)'";

    public static final String mask2_VALIDATOR_ARGS = "Mask2, resource=false";

    public void setMask2(String mask2) {
    }

    public String getMask2() {
        return null;
    }

    public static final String mask3_VALIDATOR = "mask, pattern='(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)', messageKey=comma, resource=false";

    public static final String mask3_VALIDATOR_ARGS = "Mask3, resource=false";

    public void setMask3(String mask3) {
    }

    public String getMask3() {
        return null;
    }

    public static final String mix_VALIDATOR = "required";

    public static final String mix_VALIDATOR_0 = "minlength, value=10";

    public static final String mix_VALIDATOR_1 = "maxlength, value=15";

    public static final String mix_VALIDATOR_2 = "mask, pattern=com$, messageKey=mustendcom";

    public static final String mix_VALIDATOR_3 = "email";

    public static final String mix_VALIDATOR_ARGS = "Mix, resource=false";

    public void setMix(String mix) {
    }

    public String getMix() {
        return null;
    }

    public static final String autoInteger_VALIDATOR_ARGS = "Integer, resource=false";

    public void setAutoInteger(int integer) {
    }

    public int getAutoInteger() {
        return 1;
    }

    public static final String autoDate_VALIDATOR_ARGS = "Date, resource=false";

    public void setAutoDate(Date date) {
    }

    public Date getAutoDate() {
        return null;
    }

    public void setNoValidate(String noValidate) {
    }

    public String getNoValidate() {
        return null;
    }

    public static final String noValidateDate_VALIDATOR = "noValidate";

    public void setNoValidateDate(Date data) {
    }

    public Date getNoValidateDate() {
        return null;
    }

    public static final String array_VALIDATOR = "required";

    public static final String array_VALIDATOR_ARGS = "Array, resource=false";

    public void setArray(String[] arrays) {
    }

    public String[] getArray() {
        return null;
    }

    public static final String autoArray_VALIDATOR_ARGS = "AutoArray, resource=false";

    public void setAutoArray(int[] autoArray) {
    }

    public int[] getAutoArray() {
        return null;
    }

    public void setChild(ValidatorAnnotationChildForm child) {
    }

    public ValidatorAnnotationChildForm getChild() {
        return null;
    }

    public void setChildren(ValidatorAnnotationChildForm[] children) {
    }

    public ValidatorAnnotationChildForm[] getChildren() {
        return null;
    }

    public static final String self_VALIDATOR = "noValidate";

    public void setSelf(ValidatorAnnotationForm self) {
    }

    public ValidatorAnnotationForm getSelf() {
        return null;
    }

    public static final String file_VALIDATOR = "required";

    public static final String file_VALIDATOR_ARGS = "File, resource=false";

    public void setFile(FormFile file) {
    }

    public FormFile getFile() {
        return null;
    }

    public String notConstantValidator_VALIDATOR = "required";

    public static final String notConstantValidator_VALIDATOR_ARGS = "NotConstantValidator, resource=false";

    public void setNotConstantValidator(String notConstantValidator) {
    }

    public String getNotConstantValidator() {
        return null;
    }

    public static final String notConstantValidatorArg_VALIDATOR = "required";

    public String notConstantValidatorArg_VALIDATOR_ARGS = "not.constant.validator.arg, resource=true";

    public void setNotConstantValidatorArg(String notConstantValidatorArg) {
    }

    public String getNotConstantValidatorArg() {
        return null;
    }

}