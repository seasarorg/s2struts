package org.seasar.struts.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * @author Satoshi Kimura
 */
public abstract class BaseTag implements Tag {
    protected PageContext pageContext;
    private Tag parentTag;

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public void setParent(Tag parentTag) {
        this.parentTag = parentTag;
    }

    public Tag getParent() {
        return parentTag;
    }

    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public void release() {
    }
}
