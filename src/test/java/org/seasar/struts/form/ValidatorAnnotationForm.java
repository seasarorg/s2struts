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
    
    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Arg2", bundle="myapp", resource=false)
     */
    public void setArg2(String arg) {
    }
    
    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Arg0, Arg1, Arg2", resource=false)
     */
    public void setArgs(String args) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Arg")
     */
    public void setArgDefaultResource(String argDefaultResource) {
    }
    
    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Message(name = "required", key = "myrequired")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys = "Message", resource = false)
     */
    public void setMessage(String message) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Message(name = "required", key = "my2required", bundle = "myapp", resource = false)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys = "Message2", resource = false)
     */
    public void setMessage2(String message) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Required", resource=false)
     */
    public void setRequired(String required) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.IntegerType
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Integer", resource=false)
     */
    public void setInteger(String integer) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.DateType(pattern="yyyyMMdd")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Date", resource=false)
     */
    public void setDate(String date) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.CreditCardType
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="CreditCard", resource=false)
     */
    public void setCreditCard(String creditCard) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Minlength(3)
     * @org.seasar.struts.validator.annotation.backport175.Maxlength(5)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Length", resource=false)
     */
    public void setLength(String length) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Minbytelength(value=3, charset="ISO8859_1")
     * @org.seasar.struts.validator.annotation.backport175.Maxbytelength(value=5, charset="ISO8859_1")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="ByteLength", resource=false)
     */
    public void setByteLength(String byteLength) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.FloatRange(min = 5.0F, max = 10.1F)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Range", resource=false)
     */
    public void setRange(String range) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.LongRange(min = 5L, max = 10L)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="LongRange", resource=false)
     */
    public void setLongRange(String longRange) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", messageKey = "comma")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Mask", resource=false)
     */
    public void setMask(String mask) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)")
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Mask2", resource=false)
     */
    public void setMask2(String mask2) {
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

    /**
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Integer", resource=false)
     */
    public void setAutoInteger(int integer) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Date", resource=false)
     */
    public void setAutoDate(Date date) {
    }
    
    public void setNoValidate(String noValidate) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.NoValidate
     */
    public void setNoValidateDate(Date data) {
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

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Array", resource=false)
     */
    public void setArray(String[] array) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="AutoArray", resource=false)
     */
    public void setAutoArray(int[] autoArray) {
    }

    public void setChild(ValidatorAnnotationChildForm child) {
    }
    
    public void setChildren(ValidatorAnnotationChildForm[] children) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.NoValidate
     */
    public void setSelf(ValidatorAnnotationForm self) {
    }
    
    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="File", resource=false)
     */
    public void setFile(FormFile file) {
    }
    
    public static final String constant_VALIDATOR = "required";
    public static final String constant_VALIDATOR_ARGS = "Constant, resource=false";
    
    public void setConstant(String constant) {
    }
    
}