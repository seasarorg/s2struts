package org.seasar.struts.taglib;

import javax.servlet.jsp.JspException;

import org.seasar.struts.util.InvokeUtil;

/**
 * @author Satoshi Kimura
 */
public class InitializeTag extends BaseTag {
    private String action;

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public int doStartTag() throws JspException {
        InvokeUtil.invoke(this.action);
        return SKIP_BODY;
    }

}
