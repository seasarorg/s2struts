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

import org.apache.struts.taglib.TagUtils;
import org.seasar.struts.Constants;

/**
 * {@link org.apache.struts.taglib.html.CheckboxTag}を拡張したS2Struts用のタグです。
 * <p>
 * チェックの有無をhiddenのタグに埋め込みます。
 * </p>
 * 
 * @author Satoshi Kimura
 */
public class CheckboxTag extends org.apache.struts.taglib.html.CheckboxTag {
    private static final long serialVersionUID = 5387589565117383287L;

    /**
     * インスタンスを生成します。
     */
    public CheckboxTag() {
        super();
    }

    public int doStartTag() throws JspException {
        int ret = super.doStartTag();

        StringBuffer hidden = new StringBuffer();
        hidden.append("<input");
        prepareAttribute(hidden, "type", "hidden");
        prepareAttribute(hidden, "name", Constants.CHECKBOX_NAME + prepareName());
        prepareAttribute(hidden, "value", Boolean.TRUE);
        hidden.append(getElementClose());

        TagUtils.getInstance().write(super.pageContext, hidden.toString());
        return ret;
    }
}
