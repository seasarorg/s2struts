package org.seasar.struts.form;

import java.util.Date;

import org.apache.struts.upload.FormFile;
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
import org.seasar.struts.validator.annotation.tiger.Messages;
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
public class ValidatorAnnotationForm {

    public ValidatorAnnotationForm() {
    }

    @Required
    @Args(keys = "Arg", resource = false)
    public void setArg(String arg) {
    }

    public String getArg() {
        return null;
    }

    @Required
    @Args(keys = "Arg2", bundle = "myapp", resource = false)
    public void setArg2(String arg) {
    }

    public String getArg2() {
        return null;
    }

    @Required
    @Args( { @Arg(key = "Arg3.1"), @Arg(key = "Arg3.2", bundle = "myapp", resource = false),
            @Arg(key = "Arg3.1-other", name = "other", bundle = "myapp", resource = false, position = 0) })
    public void setArg3(String arg3) {
    }

    public String getArg3() {
        return null;
    }

    @Required
    @Args(keys = "Arg0, Arg1, Arg2", resource = false)
    public void setArgs(String args) {
    }

    public String getArgs() {
        return null;
    }

    @Required
    @Args(keys = "Arg")
    public void setArgDefaultResource(String argDefaultResource) {
    }

    public String getArgDefaultResource() {
        return null;
    }

    @Required
    @Message(name = "required", key = "myrequired")
    @Args(keys = "Message", resource = false)
    public void setMessage(String message) {
    }

    public String getMessage() {
        return null;
    }

    @Required
    @Message(name = "required", key = "my2required", bundle = "myapp", resource = false)
    @Args(keys = "Message2", resource = false)
    public void setMessage2(String message) {
    }

    public String getMessage2() {
        return null;
    }

    @Required
    @IntegerType
    @Messages( { @Message(name = "required", key = "myrequired"), @Message(name = "integer", key = "myinteger") })
    public void setMessages(String messages) {
    }

    public String getMessages() {
        return null;
    }

    @Required
    @Args(keys = "Required", resource = false)
    public void setRequired(String required) {
    }

    public String getRequired() {
        return null;
    }

    @IntegerType
    @Args(keys = "Integer", resource = false)
    public void setInteger(String integer) {
    }

    public String getInteger() {
        return null;
    }

    @DateType(pattern = "yyyyMMdd")
    @Args(keys = "Date", resource = false)
    public void setDate(String date) {
    }

    public String getDate() {
        return null;
    }

    @DateType(pattern = "yyyyMMdd", strict = true)
    @Args(keys = "StrictDate", resource = false)
    public void setStrictDate(String date) {
    }

    public String getStrictDate() {
        return null;
    }

    @CreditCardType
    @Args(keys = "CreditCard", resource = false)
    public void setCreditCard(String creditCard) {
    }

    public String getCreditCard() {
        return null;
    }

    @Minlength(3)
    @Maxlength(5)
    @Args(keys = "Length", resource = false)
    public void setLength(String length) {
    }

    public String getLength() {
        return null;
    }

    @Minbytelength(value = 3, charset = "ISO8859_1")
    @Maxbytelength(value = 5, charset = "ISO8859_1")
    @Args(keys = "ByteLength", resource = false)
    public void setByteLength(String byteLength) {
    }

    public String getByteLength() {
        return null;
    }

    @Minbytelength(value = 3)
    @Maxbytelength(value = 5)
    @Args(keys = "DefaultByteLength", resource = false)
    public void setDefaultByteLength(String byteLength) {
    }

    public String getDefaultByteLength() {
        return null;
    }

    @FloatRange(min = 5.0F, max = 10.1F)
    @Args(keys = "Range", resource = false)
    public void setRange(String range) {
    }

    public String getRange() {
        return null;
    }

    @LongRange(min = 5, max = 10)
    @Args(keys = "LongRange", resource = false)
    public void setLongRange(String longRange) {
    }

    public String getLongRange() {
        return null;
    }

    @Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", messageKey = "comma")
    @Args(keys = "Mask", resource = false)
    public void setMask(String mask) {
    }

    public String getMask() {
        return null;
    }

    @Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)")
    @Args(keys = "Mask2", resource = false)
    public void setMask2(String mask2) {
    }

    public String getMask2() {
        return null;
    }

    @Mask(pattern = "(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", messageKey = "comma", resource = false)
    @Args(keys = "Mask3", resource = false)
    public void setMask3(String mask3) {
    }

    public String getMask3() {
        return null;
    }

    @Required
    @Minlength(10)
    @Maxlength(15)
    @Mask(pattern = "com$", messageKey = "mustendcom")
    @EmailType
    @Args(keys = "mixValue", resource = false)
    public void setMix(String mix) {
    }

    public String getMix() {
        return null;
    }

    @Args(keys = "Integer", resource = false)
    public void setAutoInteger(int integer) {
    }

    public int getAutoInteger() {
        return 1;
    }

    @Args(keys = "Date", resource = false)
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

    @NoValidate
    public void setNoValidateDate(Date data) {
    }

    public Date getNoValidateDate() {
        return null;
    }

    @ValidatorField(validators = {
            @Validator(name = "intRange", vars = { @Variable(name = "min", value = "10"),
                    @Variable(name = "max", value = "100") }), @Validator(name = "integer"),
            @Validator(name = "maxlength", vars = { @Variable(name = "maxlength", value = "3") }) })
    @Required
    @Args(keys = "form.message1")
    public void setFullValidatorField(String message) {
    }

    public String getFullValidatorField() {
        return null;
    }

    @ValidatorField(validators = {
            @Validator(name = "intRange", vars = { @Variable(name = "min", value = "10"),
                    @Variable(name = "max", value = "100") }), @Validator(name = "integer"),
            @Validator(name = "maxlength", value = "3") })
    @Required
    @Args(keys = "form.message2")
    public void setSimpleValidatorField(String message) {
    }

    public String getSimpleValidatorField() {
        return null;
    }

    @Required
    @Args(keys = "Array", resource = false)
    public void setArray(String[] array) {
    }

    public String[] getArray() {
        return null;
    }

    @Args(keys = "AutoArray", resource = false)
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

    @NoValidate
    public void setSelf(ValidatorAnnotationForm self) {
    }

    public ValidatorAnnotationForm getSelf() {
        return null;
    }

    @Required
    @Args(keys = "File", resource = false)
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