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
package org.seasar.struts.taglib.html;

import javax.servlet.jsp.JspException;

import org.seasar.struts.taglib.TagUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * {@link org.apache.struts.taglib.html.CancelTag}を拡張したS2Struts用のタグです。
 * <p>
 * {@link org.apache.struts.taglib.html.CancelTag}と異なり、リクエストパラメータを使用することなくCancel機能を実現します。
 * </p>
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class CancelTag extends org.apache.struts.taglib.html.CancelTag {

    private static final long serialVersionUID = -5828952087103949643L;

    private static final String DEFAULT_PROPERTY = "org.seasar.struts.taglib.html.CancelTag.CANCEL";

    /**
     * インスタンスを生成します。
     */
    public CancelTag() {
        super();
        property = DEFAULT_PROPERTY;

    }

    public int doEndTag() throws JspException {
        setCancelAction();
        return super.doEndTag();
    }

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        property = DEFAULT_PROPERTY;

    }

    protected void setCancelAction() throws JspException {
        String val = super.value;
        if (val == null) {
            val = super.text;
        }
        if (val == null) {
            val = getDefaultValue();
        }
        String mappingName = TagUtil.getActionMappingName(this.pageContext);
        S2StrutsContextUtil.setCancelAction(mappingName, super.property, val);
    }

}
