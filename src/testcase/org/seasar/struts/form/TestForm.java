package org.seasar.struts.form;

import java.util.Date;

import org.seasar.struts.annotation.StrutsActionForm;
import org.seasar.struts.validator.annotation.Args;
import org.seasar.struts.validator.annotation.CreditCardType;
import org.seasar.struts.validator.annotation.DateType;
import org.seasar.struts.validator.annotation.EmailType;
import org.seasar.struts.validator.annotation.IntegerType;
import org.seasar.struts.validator.annotation.Mask;
import org.seasar.struts.validator.annotation.Maxlength;
import org.seasar.struts.validator.annotation.Minlength;
import org.seasar.struts.validator.annotation.NoValidate;
import org.seasar.struts.validator.annotation.Range;
import org.seasar.struts.validator.annotation.Required;
import org.seasar.struts.validator.annotation.Validator;
import org.seasar.struts.validator.annotation.ValidatorField;
import org.seasar.struts.validator.annotation.Variable;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsActionForm(name = "testFormName")
public class TestForm {

    public TestForm() {
    }

    @Required
    @Args(keys = "Arg", resource = false)
    public void setArg(String arg) {
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

    @Minlength(value = 3, type = "minbytelength", charset = "ISO8859_1")
    @Maxlength(value = 5, type = "maxbytelength", charset = "ISO8859_1")
    @Args(keys = "ByteLength", resource = false)
    public void setByteLength(String byteLength) {
    }

    @Range(min = 5.0, max = 10.1, type = "floatRange")
    @Args(keys = "Range", resource = false)
    public void setRange(String range) {
    }

    @Required
    @Minlength(10)
    @Maxlength(15)
    @Mask(pattern = "com$", messageKey = "mustendcom")
    @EmailType
    @Args(keys = "mixValue", resource = false)
    public void setMix(String mix) {
    }

    @Args(keys = "Date", resource = false)
    public void setAutoDate(Date date) {
    }

    @NoValidate
    public void setNoValidateDate(Date data) {
    }

    @ValidatorField(validators = {
            @Validator(name = "intRange", vars = {
                    @Variable(name = "min", value = "10"),
                    @Variable(name = "max", value = "100") }),
            @Validator(name = "integer"),
            @Validator(name = "maxlength", vars = { @Variable(name = "maxlength", value = "3") }) })
    @Required
    @Args(keys = "form.message1")
    public void setFullValidatorField(String message) {
    }

    @ValidatorField(validators = {
            @Validator(name = "intRange", vars = {
                    @Variable(name = "min", value = "10"),
                    @Variable(name = "max", value = "100") }),
            @Validator(name = "integer"),
            @Validator(name = "maxlength", value = "3") })
    @Required
    @Args(keys = "form.message2")
    public void setSimpleValidatorField(String message) {
    }

}