package org.seasar.struts.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.FormTag;

public abstract class TagUtil {

    public static String getActionMappingName(PageContext pageContext) throws JspException {
        FormTag form = (FormTag) pageContext.getAttribute(Constants.FORM_KEY,
                PageContext.REQUEST_SCOPE);
        if (form == null) {
            throw new JspException("Tag is not defined in FormTag.");
        }

        return TagUtils.getInstance().getActionMappingName(form.getAction());
    }

}
