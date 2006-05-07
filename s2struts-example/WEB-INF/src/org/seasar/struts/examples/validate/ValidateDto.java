/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.examples.validate;

import java.io.Serializable;

import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.ByteType;
import org.seasar.struts.validator.annotation.tiger.CreditCardType;
import org.seasar.struts.validator.annotation.tiger.DateType;
import org.seasar.struts.validator.annotation.tiger.DoubleType;
import org.seasar.struts.validator.annotation.tiger.EmailType;
import org.seasar.struts.validator.annotation.tiger.FloatRange;
import org.seasar.struts.validator.annotation.tiger.FloatType;
import org.seasar.struts.validator.annotation.tiger.IntegerType;
import org.seasar.struts.validator.annotation.tiger.LongType;
import org.seasar.struts.validator.annotation.tiger.Mask;
import org.seasar.struts.validator.annotation.tiger.Maxbytelength;
import org.seasar.struts.validator.annotation.tiger.Maxlength;
import org.seasar.struts.validator.annotation.tiger.Minbytelength;
import org.seasar.struts.validator.annotation.tiger.Minlength;
import org.seasar.struts.validator.annotation.tiger.Required;
import org.seasar.struts.validator.annotation.tiger.UrlType;

/**
 * @author Satoshi Kimura
 */
public class ValidateDto implements Serializable {
	private static final long serialVersionUID = -2918322782862139730L;

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
    @Args(keys = "Byte", resource = false)
    public void setByte(String b) {
        byte_ = b;
    }

    public String getCreditCard() {
        return creditCard_;
    }

    @CreditCardType
    @Args(keys = "CreditCard", resource = false)
    public void setCreditCard(String creditCard) {
        creditCard_ = creditCard;
    }

    public String getDate() {
        return date_;
    }

    @DateType
    @Args(keys = "Date", resource = false)
    public void setDate(String date) {
        date_ = date;
    }

    public String getDouble() {
        return double_;
    }

    @DoubleType
    @Args(keys = "Double", resource = false)
    public void setDouble(String d) {
        double_ = d;
    }

    public String getEmail() {
        return email_;
    }

    @EmailType
    @Args(keys = "Email", resource = false)
    public void setEmail(String email) {
        email_ = email;
    }

    public String getFloat() {
        return float_;
    }

    @FloatType
    @Args(keys = "Float", resource = false)
    public void setFloat(String f) {
        float_ = f;
    }

    public String getInteger() {
        return integer_;
    }

    @IntegerType
    @Args(keys = "Integer", resource = false)
    public void setInteger(String integer) {
        integer_ = integer;
    }

    public String getLength() {
        return length_;
    }

    @Minlength(3)
    @Maxlength(5)
    @Args(keys = "Length", resource = false)
    public void setLength(String length) {
        length_ = length;
    }

    public String getLength2() {
        return length2_;
    }

    @Minbytelength(value = 3, charset = "ISO8859_1")
    @Maxbytelength(value = 5, charset = "ISO8859_1")
    @Args(keys = "Length2", resource = false)
    public void setLength2(String length2) {
        length2_ = length2;
    }

    public String getLong() {
        return long_;
    }

    @LongType
    @Args(keys = "Long", resource = false)
    public void setLong(String l) {
        long_ = l;
    }

    public String getRange() {
        return range_;
    }

    @FloatRange(min = 5.0F, max = 10.1F)
    @Args(keys = "Range", resource = false)
    public void setRange(String range) {
        range_ = range;
    }

    public String getRequired() {
        return required_;
    }

    @Required
    @Args(keys = "validate.required")
    public void setRequired(String required) {
        required_ = required;
    }

    public short getShort() {
        return short_;
    }

    @Args(keys = "Short", resource = false)
    public void setShort(short s) {
        short_ = s;
    }

    public String getUrl() {
        return url_;
    }

    @UrlType
    @Args(keys = "URL", resource = false)
    public void setUrl(String url) {
        url_ = url;
    }

    public String getMix() {
        return mix_;
    }

    @Required
    @Minlength(10)
    @Maxlength(15)
    @Mask(pattern = "com$", messageKey = "mustendcom")
    @EmailType
    @Args(keys = "mixValue", resource = false)
    public void setMix(String mix) {
        mix_ = mix;
    }
}
