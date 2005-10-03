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

        String path = S2StrutsContextUtil.getPath();
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
