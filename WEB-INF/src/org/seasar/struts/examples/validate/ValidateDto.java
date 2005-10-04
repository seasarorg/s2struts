package org.seasar.struts.examples.validate;

import java.io.Serializable;

import org.seasar.struts.validator.annotation.Args;
import org.seasar.struts.validator.annotation.ByteType;
import org.seasar.struts.validator.annotation.CreditCard;
import org.seasar.struts.validator.annotation.DateType;
import org.seasar.struts.validator.annotation.DoubleType;
import org.seasar.struts.validator.annotation.Email;
import org.seasar.struts.validator.annotation.FloatType;
import org.seasar.struts.validator.annotation.IntegerType;
import org.seasar.struts.validator.annotation.LongType;
import org.seasar.struts.validator.annotation.Mask;
import org.seasar.struts.validator.annotation.Maxlength;
import org.seasar.struts.validator.annotation.Minlength;
import org.seasar.struts.validator.annotation.Range;
import org.seasar.struts.validator.annotation.Required;
import org.seasar.struts.validator.annotation.Url;

/**
 * @author Satoshi Kimura
 */
public class ValidateDto implements Serializable {
    private String required_;

    private String byte_ = "1111";

    private short short_ = Short.MAX_VALUE;

    private String date_ = "00/00/00";

    private String double_ = "b";

    private String email_ = "a@a.a";

    private String float_ = "a";

    private String integer_ = "1.1";
    
    private String length2_ = "‚ ";

    private String long_ = "2.2";

    private String length_ = "c";

    private String range_ = "20";

    private String url_ = "http:/www.seasar.org/index.html";

    private String creditCard_;

    private String mix_;

    public String getByte() {
        return byte_;
    }

    @ByteType
    @Args(keys="Byte",resource=false)
    public void setByte(String b) {
        byte_ = b;
    }

    public String getCreditCard() {
        return creditCard_;
    }

    @CreditCard
    @Args(keys="CreditCard",resource=false)
    public void setCreditCard(String creditCard) {
        creditCard_ = creditCard;
    }

    public String getDate() {
        return date_;
    }

    @DateType
    @Args(keys="Date",resource=false)
    public void setDate(String date) {
        date_ = date;
    }

    public String getDouble() {
        return double_;
    }

    @DoubleType
    @Args(keys="Double",resource=false)
    public void setDouble(String d) {
        double_ = d;
    }

    public String getEmail() {
        return email_;
    }

    @Email
    @Args(keys="Email",resource=false)
    public void setEmail(String email) {
        email_ = email;
    }

    public String getFloat() {
        return float_;
    }

    @FloatType
    @Args(keys="Float",resource=false)
    public void setFloat(String f) {
        float_ = f;
    }

    public String getInteger() {
        return integer_;
    }

    @IntegerType
    @Args(keys="Integer",resource=false)
    public void setInteger(String integer) {
        integer_ = integer;
    }

    public String getLength() {
        return length_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Minlength(value=3)
     * @org.seasar.struts.validator.annotation.Maxlength(value=5)
     * @org.seasar.struts.validator.annotation.Args(keys="Length",resource=false)
     */
    @Minlength(3)
    @Maxlength(5)
    @Args(keys="Length",resource=false)
    public void setLength(String length) {
        length_ = length;
    }

    public String getLength2() {
        return length2_;
    }
    
    @Minlength(value=3,type="minbytelength",charset="ISO8859_1")
    @Maxlength(value=5,type="maxbytelength",charset="ISO8859_1")
    @Args(keys="Length2",resource=false)
    public void setLength2(String length2) {
        length2_ = length2;
    }

    public String getLong() {
        return long_;
    }

    @LongType
    @Args(keys="Long",resource=false)
    public void setLong(String l) {
        long_ = l;
    }

    public String getRange() {
        return range_;
    }

    @Range(min=5.0,max=10.1,type="floatRange")
    @Args(keys="Range",resource=false)
    public void setRange(String range) {
        range_ = range;
    }

    public String getRequired() {
        return required_;
    }

    @Required
    @Args(keys="validate.required")
    public void setRequired(String required) {
        required_ = required;
    }

    public short getShort() {
        return short_;
    }

    @Args(keys="Short",resource=false)
    public void setShort(short s) {
        short_ = s;
    }

    public String getUrl() {
        return url_;
    }

    @Url
    @Args(keys="URL",resource=false)
    public void setUrl(String url) {
        url_ = url;
    }

    public String getMix() {
        return mix_;
    }

    @Required
    @Minlength(10)
    @Maxlength(15)
    @Mask(pattern="com$",messageKey="mustendcom")
    @Email
    @Args(keys="mixValue",resource=false)
    public void setMix(String mix) {
        mix_ = mix;
    }
}
