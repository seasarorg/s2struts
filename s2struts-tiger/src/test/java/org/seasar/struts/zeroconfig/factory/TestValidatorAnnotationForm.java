package org.seasar.struts.zeroconfig.factory;

import java.util.Date;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.tiger.StrutsActionForm;
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
    @Args(keys = "Arg", resource = false)
    public void setArg(String arg) {
    }

    @Required
    @Args(keys = "Arg0, Arg1, Arg2", resource = false)
    public void setArgs(String args) {
    }

    @Required
    @Args(keys = "Required", resource = false)
    public void setRequired(String required) {
    }

    @IntegerType
    @Args(keys = "Integer", resource = false)
    public void setInteger(String integer) {
    }

    @DateType(pattern = "yyyyMMdd")
    @Args(keys = "Date", resource = false)
    public void setDate(String date) {
    }

    @CreditCardType
    @Args(keys = "CreditCard", resource = false)
    public void setCreditCard(String creditCard) {
    }

    @Minlength(3)
    @Maxlength(5)
    @Args(keys = "Length", resource = false)
    public void setLength(String length) {
    }

    @Minbytelength(value = 3, charset = "ISO8859_1")
    @Maxbytelength(value = 5, charset = "ISO8859_1")
    @Args(keys = "ByteLength", resource = false)
    public void setByteLength(String byteLength) {
    }

    @FloatRange(min = 5.0F, max = 10.1F)
    @Args(keys = "Range", resource = false)
    public void setRange(String range) {
    }

    @LongRange(min = 5, max = 10)
    @Args(keys = "LongRange", resource = false)
    public void setLongRange(String longRange) {
    }

    @Required
    @Minlength(10)
    @Maxlength(15)
    @Mask(pattern = "com$", messageKey = "mustendcom")
    @EmailType
    @Args(keys = "mixValue", resource = false)
    public void setMix(String mix) {
    }

    @Args(keys = "Integer", resource = false)
    public void setAutoInteger(int integer) {
    }

    @Args(keys = "Date", resource = false)
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
    @Args(keys = "Array", resource = false)
    public void setArray(String[] array) {
    }

    @Args(keys = "AutoArray", resource = false)
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
    @Args(keys = "File", resource = false)
    public void setFile(FormFile file) {
    }

    public static final String constant_VALIDATOR = "required";

    public static final String constant_VALIDATOR_ARGS = "Constant, resource=false";

    public void setConstant(String constant) {
    }

}