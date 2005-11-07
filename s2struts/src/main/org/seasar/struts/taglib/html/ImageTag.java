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
