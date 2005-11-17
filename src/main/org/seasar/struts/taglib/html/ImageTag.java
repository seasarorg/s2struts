/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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

import org.seasar.framework.util.Base64Util;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 */
public class ImageTag extends org.apache.struts.taglib.html.ImageTag {
    protected String action;

    public int doEndTag() throws JspException {
        setMethodBindingExpression();

        try {
            return super.doEndTag();
        } finally {
            release();
        }
    }

    public void release() {
        super.release();
        this.action = null;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    protected void setMethodBindingExpression() {
        if (StringUtil.isEmpty(super.property)) {
            super.property = Base64Util.encode(this.action.getBytes());
        }
        S2StrutsContextUtil.setMethodBindingExpression(super.property, null, this.action);
    }

}
