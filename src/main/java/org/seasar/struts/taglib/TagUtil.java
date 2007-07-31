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
package org.seasar.struts.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.FormTag;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public abstract class TagUtil {

    public static String getActionMappingName(PageContext pageContext)
            throws JspException {
        FormTag form = (FormTag) pageContext.getAttribute(Constants.FORM_KEY,
                PageContext.REQUEST_SCOPE);
        if (form == null) {
            throw new JspException("Tag is not defined in FormTag.");
        }

        return TagUtils.getInstance().getActionMappingName(form.getAction());
    }

}
