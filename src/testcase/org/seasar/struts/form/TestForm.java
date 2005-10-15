package org.seasar.struts.form;

import java.util.Date;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsActionForm(name="testFormName")
 */
public class TestForm {

    public TestForm() {
    }

    /**
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="Arg", resource=false)
     */
    public void setArg(String arg) {
    }

    /**
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="Required", resource=false)
     */
    public void setRequired(String required) {
    }

    /**
     * @org.seasar.struts.validator.annotation.IntegerType
     * @org.seasar.struts.validator.annotation.Args(keys="Integer", resource=false)
     */
    public void setInteger(String integer) {
    }

    /**
     * @org.seasar.struts.validator.annotation.DateType(pattern="yyyyMMdd")
     * @org.seasar.struts.validator.annotation.Args(keys="Date", resource=false)
     */
    public void setDate(String date) {
    }

    /**
     * @org.seasar.struts.validator.annotation.CreditCardType
     * @org.seasar.struts.validator.annotation.Args(keys="CreditCard", resource=false)
     */
    public void setCreditCard(String creditCard) {
    }

    /**
     * @org.seasar.struts.validator.annotation.Minlength(3)
     * @org.seasar.struts.validator.annotation.Maxlength(5)
     * @org.seasar.struts.validator.annotation.Args(keys="Length", resource=false)
     */
    public void setLength(String length) {
    }

    /**
     * @org.seasar.struts.validator.annotation.Minbytelength(value=3, charset="ISO8859_1")
     * @org.seasar.struts.validator.annotation.Maxbytelength(value=5, charset="ISO8859_1")
     * @org.seasar.struts.validator.annotation.Args(keys="ByteLength", resource=false)
     */
    public void setByteLength(String byteLength) {
    }

    /**
     * @org.seasar.struts.validator.annotation.FloatRange(min=5.0F, max=10.1F)
     * @org.seasar.struts.validator.annotation.Args(keys="Range", resource=false)
     */
    public void setRange(String range) {
    }

    /**
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Minlength(10)
     * @org.seasar.struts.validator.annotation.Maxlength(15)
     * @org.seasar.struts.validator.annotation.Mask(pattern="com$", messageKey="mustendcom")
     * @org.seasar.struts.validator.annotation.EmailType
     * @org.seasar.struts.validator.annotation.Args(keys="mixValue", resource=false)
     */
    public void setMix(String mix) {
    }

    /**
     * @org.seasar.struts.validator.annotation.Args(keys="Integer", resource=false)
     */
    public void setAutoInteger(int integer) {
    }

    /**
     * @org.seasar.struts.validator.annotation.Args(keys="Date", resource=false)
     */
    public void setAutoDate(Date date) {
    }

    /**
     * @org.seasar.struts.validator.annotation.NoValidate
     */
    public void setNoValidateDate(Date data) {
    }

    /**
     * @org.seasar.struts.validator.annotation.ValidatorField(validators=
     * { @org.seasar.struts.validator.annotation.Validator(name="intRange", vars=
     *   { @org.seasar.struts.validator.annotation.Variable(name="min", value="10")
     *   , @org.seasar.struts.validator.annotation.Variable(name="max", value="100")
     *   })
     * , @org.seasar.struts.validator.annotation.Validator(name="integer")
     * , @org.seasar.struts.validator.annotation.Validator(name="maxlength", vars=
     *   { @org.seasar.struts.validator.annotation.Variable(name="maxlength", value="3")
     *   })
     * })
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="form.message1")
     */
    public void setFullValidatorField(String message) {
    }

    /**
     * @org.seasar.struts.validator.annotation.ValidatorField(validators=
     * { @org.seasar.struts.validator.annotation.Validator(name="intRange", vars=
     *   { @org.seasar.struts.validator.annotation.Variable(name="min", value="10")
     *   , @org.seasar.struts.validator.annotation.Variable(name="max", value="100")
     *   })
     * , @org.seasar.struts.validator.annotation.Validator(name="integer")
     * , @org.seasar.struts.validator.annotation.Validator(name="maxlength", value="3")
     * })
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="form.message2")
     */
    public void setSimpleValidatorField(String message) {
    }

}