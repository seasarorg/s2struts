package org.seasar.struts.form;

import java.util.Date;

import org.apache.struts.upload.FormFile;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.backport175.StrutsActionForm(name="testFormName")
 */
public class ValidatorAnnotationForm {

    public ValidatorAnnotationForm() {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Arg", resource=false)
     */
    public void setArg(String arg) {
    }
    
    public String getArg() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Arg2", bundle="myapp", resource=false)
     */
    public void setArg2(String arg) {
    }

    public String getArg2() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(
     * { @org.seasar.struts.validator.annotation.backport175.Arg(key = "Arg3.1")
     * , @org.seasar.struts.validator.annotation.backport175.Arg(key = "Arg3.2", bundle = "myapp", resource = false)
     * , @org.seasar.struts.validator.annotation.backport175.Arg(key = "Arg3.1-other", name = "other", bundle = "myapp", resource = false, position = 0)
     * })
     */
    public void setArg3(String arg3) {
    }

    public String getArg3() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Arg0, Arg1, Arg2", resource=false)
     */
    public void setArgs(String args) {
    }

    public String getArgs() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Arg")
     */
    public void setArgDefaultResource(String argDefaultResource) {
    }
    
    public String getArgDefaultResource() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Message(name = "required", key = "myrequired")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys = "Message", resource = false)
     */
    public void setMessage(String message) {
    }

    public String getMessage() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Message(name = "required", key = "my2required", bundle = "myapp", resource = false)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys = "Message2", resource = false)
     */
    public void setMessage2(String message) {
    }

    public String getMessage2() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.IntegerType
     * @org.seasar.struts.validator.annotation.backport175.Messages(
     * { @org.seasar.struts.validator.annotation.backport175.Message(name = "required", key = "myrequired")
     * , @org.seasar.struts.validator.annotation.backport175.Message(name = "integer", key="myinteger")
     * })
     */
    public void setMessages(String messages) {
    }

    public String getMessages() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Required", resource=false)
     */
    public void setRequired(String required) {
    }

    public String getRequired() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.IntegerType
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Integer", resource=false)
     */
    public void setInteger(String integer) {
    }

    public String getInteger() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.DateType(pattern="yyyyMMdd")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Date", resource=false)
     */
    public void setDate(String date) {
    }

    public String getDate() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.DateType(pattern="yyyyMMdd", strict=true)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="StrictDate", resource=false)
     */
    public void setStrictDate(String date) {
    }

    public String getStrictDate() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.CreditCardType
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="CreditCard", resource=false)
     */
    public void setCreditCard(String creditCard) {
    }

    public String getCreditCard() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Minlength(3)
     * @org.seasar.struts.validator.annotation.backport175.Maxlength(5)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Length", resource=false)
     */
    public void setLength(String length) {
    }

    public String getLength() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Minbytelength(value=3, charset="ISO8859_1")
     * @org.seasar.struts.validator.annotation.backport175.Maxbytelength(value=5, charset="ISO8859_1")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="ByteLength", resource=false)
     */
    public void setByteLength(String byteLength) {
    }

    public String getByteLength() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.FloatRange(min = 5.0F, max = 10.1F)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Range", resource=false)
     */
    public void setRange(String range) {
    }

    public String getRange() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.LongRange(min = 5L, max = 10L)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="LongRange", resource=false)
     */
    public void setLongRange(String longRange) {
    }

    public String getLongRange() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", messageKey = "comma")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Mask", resource=false)
     */
    public void setMask(String mask) {
    }

    public String getMask() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Mask2", resource=false)
     */
    public void setMask2(String mask2) {
    }
    
    public String getMask2() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", messageKey = "comma", resource = false)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Mask", resource=false)
     */
    public void setMask3(String mask3) {
    }

    public String getMask3() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Minlength(10)
     * @org.seasar.struts.validator.annotation.backport175.Maxlength(15)
     * @org.seasar.struts.validator.annotation.backport175.Mask(pattern="com$", messageKey="mustendcom")
     * @org.seasar.struts.validator.annotation.backport175.EmailType
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="mixValue", resource=false)
     */
    public void setMix(String mix) {
    }

    public String getMix() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Integer", resource=false)
     */
    public void setAutoInteger(int integer) {
    }

    public int getAutoInteger() {
        return 1;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Date", resource=false)
     */
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

    /**
     * @org.seasar.struts.validator.annotation.backport175.NoValidate
     */
    public void setNoValidateDate(Date data) {
    }

    public Date getNoValidateDate() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.ValidatorField(validators=
     * { @org.seasar.struts.validator.annotation.backport175.Validator(name="intRange", vars=
     *   { @org.seasar.struts.validator.annotation.backport175.Variable(name="min", value="10")
     *   , @org.seasar.struts.validator.annotation.backport175.Variable(name="max", value="100")
     *   })
     * , @org.seasar.struts.validator.annotation.backport175.Validator(name="integer")
     * , @org.seasar.struts.validator.annotation.backport175.Validator(name="maxlength", vars=
     *   { @org.seasar.struts.validator.annotation.backport175.Variable(name="maxlength", value="3")
     *   })
     * })
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="form.message1")
     */
    public void setFullValidatorField(String message) {
    }

    public String getFullValidatorField() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.ValidatorField(validators=
     * { @org.seasar.struts.validator.annotation.backport175.Validator(name="intRange", vars=
     *   { @org.seasar.struts.validator.annotation.backport175.Variable(name="min", value="10")
     *   , @org.seasar.struts.validator.annotation.backport175.Variable(name="max", value="100")
     *   })
     * , @org.seasar.struts.validator.annotation.backport175.Validator(name="integer")
     * , @org.seasar.struts.validator.annotation.backport175.Validator(name="maxlength", value="3")
     * })
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="form.message2")
     */
    public void setSimpleValidatorField(String message) {
    }

    public String getSimpleValidatorField() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Array", resource=false)
     */
    public void setArray(String[] array) {
    }

    public String[] getArray() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="AutoArray", resource=false)
     */
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

    /**
     * @org.seasar.struts.validator.annotation.backport175.NoValidate
     */
    public void setSelf(ValidatorAnnotationForm self) {
    }
    
    public ValidatorAnnotationForm getSelf() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="File", resource=false)
     */
    public void setFile(FormFile file) {
    }
    
    public FormFile getFile() {
        return null;
    }

    public static final String constant_VALIDATOR = "required";

    public static final String constant_VALIDATOR_ARGS = "Constant, resource=false";

    public void setConstant(String constant) {
    }

    public String getConstant() {
        return null;
    }

}