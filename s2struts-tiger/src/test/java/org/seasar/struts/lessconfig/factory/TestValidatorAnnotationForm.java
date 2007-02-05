package org.seasar.struts.lessconfig.factory;

import java.util.Date;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.tiger.BoolType;
import org.seasar.struts.annotation.tiger.StrutsActionForm;
import org.seasar.struts.validator.annotation.tiger.Arg;
import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.CreditCardType;
import org.seasar.struts.validator.annotation.tiger.DateType;
import org.seasar.struts.validator.annotation.tiger.EmailType;
import org.seasar.struts.validator.annotation.tiger.FloatRange;
import org.seasar.struts.validator.annotation.tiger.IntegerType;
import org.seasar.struts.validator.annotation.tiger.LongRange;
import org.seasar.struts.validator.annotation.tiger.Mask;
import org.seasar.struts.validator.annotation.tiger.Maxbytelength;
import org.seasar.struts.validator.annotation.tiger.Maxlength;
import org.seasar.struts.validator.annotation.tiger.Message;
import org.seasar.struts.validator.annotation.tiger.Minbytelength;
import org.seasar.struts.validator.annotation.tiger.Minlength;
import org.seasar.struts.validator.annotation.tiger.NoValidate;
import org.seasar.struts.validator.annotation.tiger.Required;
import org.seasar.struts.validator.annotation.tiger.Validator;
import org.seasar.struts.validator.annotation.tiger.ValidatorField;
import org.seasar.struts.validator.annotation.tiger.Variable;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsActionForm(name = "testFormName")
public class TestValidatorAnnotationForm {

    public TestValidatorAnnotationForm() {
    }

    @Required
    @Args(keys = "Arg", resource = BoolType.FALSE)
    public void setArg(String arg) {
    }

    @Required
    @Args(keys = "Arg2", bundle = "myapp", resource = BoolType.FALSE)
    public void setArg2(String arg) {
    }

    @Required
    @Args(args = { @Arg(key = "Arg3.1"), @Arg(key = "Arg3.2", bundle = "myapp", resource = BoolType.FALSE),
            @Arg(key = "Arg3.1-other", name = "other", bundle = "myapp", resource = BoolType.FALSE, position = 0) })
    public void setArg3(String arg3) {
    }

    @Required
    @Args(keys = "Arg0, Arg1, Arg2", resource = BoolType.FALSE)
    public void setArgs(String args) {
    }

    @Required
    @Args(keys = "Arg")
    public void setArgDefaultResource(String argDefaultResource) {
    }

    @Required
    @Message(name = "required", key = "myrequired")
    @Args(keys = "Message", resource = BoolType.FALSE)
    public void setMessage(String message) {
    }

    @Required
    @Message(name = "required", key = "my2required", bundle = "myapp", resource = BoolType.FALSE)
    @Args(keys = "Message2", resource = BoolType.FALSE)
    public void setMessage2(String message) {
    }

    @Required
    @Args(keys = "Required", resource = BoolType.FALSE)
    public void setRequired(String required) {
    }

    @IntegerType
    @Args(keys = "Integer", resource = BoolType.FALSE)
    public void setInteger(String integer) {
    }

    @DateType(pattern = "yyyyMMdd")
    @Args(keys = "Date", resource = BoolType.FALSE)
    public void setDate(String date) {
    }

    @DateType(pattern = "yyyyMMdd", strict = BoolType.TRUE)
    @Args(keys = "StrictDate", resource = BoolType.FALSE)
    public void setStrictDate(String date) {
    }

    @CreditCardType
    @Args(keys = "CreditCard", resource = BoolType.FALSE)
    public void setCreditCard(String creditCard) {
    }

    @Minlength(3)
    @Maxlength(5)
    @Args(keys = "Length", resource = BoolType.FALSE)
    public void setLength(String length) {
    }

    @Minbytelength(value = 3, charset = "ISO8859_1")
    @Maxbytelength(value = 5, charset = "ISO8859_1")
    @Args(keys = "ByteLength", resource = BoolType.FALSE)
    public void setByteLength(String byteLength) {
    }

    @FloatRange(min = 5.0F, max = 10.1F)
    @Args(keys = "Range", resource = BoolType.FALSE)
    public void setRange(String range) {
    }

    @LongRange(min = 5, max = 10)
    @Args(keys = "LongRange", resource = BoolType.FALSE)
    public void setLongRange(String longRange) {
    }

    @Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", messageKey = "comma")
    @Args(keys = "Mask", resource = BoolType.FALSE)
    public void setMask(String mask) {
    }

    @Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)")
    @Args(keys = "Mask2", resource = BoolType.FALSE)
    public void setMask2(String mask2) {
    }

    @Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", messageKey = "comma", resource = BoolType.FALSE)
    @Args(keys = "Mask3", resource = BoolType.FALSE)
    public void setMask3(String mask3) {
    }

    @Required
    @Minlength(10)
    @Maxlength(15)
    @Mask(pattern = "com$", messageKey = "mustendcom")
    @EmailType
    @Args(keys = "mixValue", resource = BoolType.FALSE)
    public void setMix(String mix) {
    }

    @Args(keys = "Integer", resource = BoolType.FALSE)
    public void setAutoInteger(int integer) {
    }

    @Args(keys = "Date", resource = BoolType.FALSE)
    public void setAutoDate(Date date) {
    }

    public void setNoValidate(String noValidate) {
    }

    @NoValidate
    public void setNoValidateDate(Date data) {
    }

    @ValidatorField(validators = {
            @Validator(name = "intRange", vars = { @Variable(name = "min", value = "10"),
                    @Variable(name = "max", value = "100") }), @Validator(name = "integer"),
            @Validator(name = "maxlength", vars = { @Variable(name = "maxlength", value = "3") }) })
    @Required
    @Args(keys = "form.message1")
    public void setFullValidatorField(String message) {
    }

    @ValidatorField(validators = {
            @Validator(name = "intRange", vars = { @Variable(name = "min", value = "10"),
                    @Variable(name = "max", value = "100") }), @Validator(name = "integer"),
            @Validator(name = "maxlength", value = "3") })
    @Required
    @Args(keys = "form.message2")
    public void setSimpleValidatorField(String message) {
    }

    @Required
    @Args(keys = "Array", resource = BoolType.FALSE)
    public void setArray(String[] array) {
    }

    @Args(keys = "AutoArray", resource = BoolType.FALSE)
    public void setAutoArray(int[] autoArray) {
    }

    public void setChild(TestValidatorAnnotationChildForm child) {
    }

    public void setChildren(TestValidatorAnnotationChildForm[] children) {
    }

    @NoValidate
    public void setSelf(TestValidatorAnnotationForm self) {
    }

    @Required
    @Args(keys = "File", resource = BoolType.FALSE)
    public void setFile(FormFile file) {
    }

    public static final String constant_VALIDATOR = "required";

    public static final String constant_VALIDATOR_ARGS = "Constant, resource=false";

    public void setConstant(String constant) {
    }

}