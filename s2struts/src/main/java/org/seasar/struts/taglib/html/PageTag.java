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
import org.seasar.framework.util.Base64Util;
import org.seasar.struts.Constants;
import org.seasar.struts.taglib.BaseTag;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 */
public class PageTag extends BaseTag {

    public int doStartTag() throws JspException {

        String path = S2StrutsContextUtil.getCurrentInputPath();
        path = new String(Base64Util.encode(path.getBytes()));


        if (path == null) {
            return SKIP_BODY;
        }

        StringBuffer results = new StringBuffer();
        results.append("<input type=\"hidden\" ");
        results.append("name=");
        results.append("\"");
        results.append(Constants.PAGE_NAME_ELEMENT_VALUE);
        results.append("\" ");
        results.append("value=");
        results.append("\"");
        results.append(path);
        results.append("\"");
        results.append("/>");

        TagUtils.getInstance().write(super.pageContext, results.toString());

        return SKIP_BODY;
    }

}
