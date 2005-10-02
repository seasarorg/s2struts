package org.seasar.struts.examples.validate;

import java.io.Serializable;

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

    /**
     * @org.seasar.struts.validator.annotation.Byte
     * @org.seasar.struts.validator.annotation.Args(keys="Byte",resource=false)
     */
    public void setByte(String b) {
        byte_ = b;
    }

    public String getCreditCard() {
        return creditCard_;
    }

    /**
     * @org.seasar.struts.validator.annotation.CreditCard
     * @org.seasar.struts.validator.annotation.Args(keys="CreditCard",resource=false)
     */
    public void setCreditCard(String creditCard) {
        creditCard_ = creditCard;
    }

    public String getDate() {
        return date_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Date
     * @org.seasar.struts.validator.annotation.Args(keys="Date",resource=false)
     */
    public void setDate(String date) {
        date_ = date;
    }

    public String getDouble() {
        return double_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Double
     * @org.seasar.struts.validator.annotation.Args(keys="Double",resource=false)
     */
    public void setDouble(String d) {
        double_ = d;
    }

    public String getEmail() {
        return email_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Email
     * @org.seasar.struts.validator.annotation.Args(keys="Email",resource=false)
     */
    public void setEmail(String email) {
        email_ = email;
    }

    public String getFloat() {
        return float_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Float
     * @org.seasar.struts.validator.annotation.Args(keys="Float",resource=false)
     */
    public void setFloat(String f) {
        float_ = f;
    }

    public String getInteger() {
        return integer_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Integer
     * @org.seasar.struts.validator.annotation.Args(keys="Integer",resource=false)
     */
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
    public void setLength(String length) {
        length_ = length;
    }

    public String getLength2() {
        return length2_;
    }
    
    /**
     * @org.seasar.struts.validator.annotation.Minlength(value=3,type="minbytelength")
     * @org.seasar.struts.validator.annotation.Maxlength(value=5,type="maxbytelength")
     * @org.seasar.struts.validator.annotation.Args(keys="Length2",resource=false)
     */
    public void setLength2(String length2) {
        length2_ = length2;
    }

    public String getLong() {
        return long_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Long
     * @org.seasar.struts.validator.annotation.Args(keys="Long",resource=false)
     */
    public void setLong(String l) {
        long_ = l;
    }

    public String getRange() {
        return range_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Range(min=5.0,max=10.1,type="floatRange")
     * @org.seasar.struts.validator.annotation.Args(keys="Range",resource=false)
     */
    public void setRange(String range) {
        range_ = range;
    }

    public String getRequired() {
        return required_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="validate.required")
     */
    public void setRequired(String required) {
        required_ = required;
    }

    public short getShort() {
        return short_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Args(keys="Short",resource=false)
     */
    public void setShort(short s) {
        short_ = s;
    }

    public String getUrl() {
        return url_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Url
     * @org.seasar.struts.validator.annotation.Args(keys="URL",resource=false)
     */
    public void setUrl(String url) {
        url_ = url;
    }

    public String getMix() {
        return mix_;
    }

    /**
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Minlength(value=10)
     * @org.seasar.struts.validator.annotation.Maxlength(value=15)
     * @org.seasar.struts.validator.annotation.Mask(pattern="com$",messageKey="mustendcom")
     * @org.seasar.struts.validator.annotation.Email
     * @org.seasar.struts.validator.annotation.Args(keys="mixValue",resource=false)
     */
    public void setMix(String mix) {
        mix_ = mix;
    }
}
