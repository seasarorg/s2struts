package org.seasar.struts.taglib.html;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.seasar.framework.util.IntegerConversionUtil;

/**
 * @author Satoshi Kimura
 */
public class ButtonTag extends org.apache.struts.taglib.html.ButtonTag {
    protected String type;
    protected String indexId;
    /**
     * @return Returns the indexId.
     */
    public String getIndexId() {
        return this.indexId;
    }
    /**
     * @param indexId The indexName to set.
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int doStartTag() throws JspException {

        // Generate an HTML element
        StringBuffer results = new StringBuffer();
        results.append("<button");
        if (this.type != null) {
            results.append(" type=\"");
            results.append(this.type);
            results.append("\"");
        }
        if (super.property != null) {
            results.append(" name=\"");
            results.append(super.property);
            if (this.indexed)
                prepareIndex(results, null);
            results.append("\"");
        }
        if (super.accesskey != null) {
            results.append(" accesskey=\"");
            results.append(super.accesskey);
            results.append("\"");
        }
        if (super.tabindex != null) {
            results.append(" tabindex=\"");
            results.append(super.tabindex);
            results.append("\"");
        }
        if (this.indexId != null) {
            results.append(" value=\"");
            Object indexValue = super.pageContext.getAttribute(this.indexId);
            results.append(IntegerConversionUtil.toPrimitiveInt(indexValue));
            results.append("\"");
        } else {
            results.append(" value=\"");
            results.append(super.value);
            results.append("\"");
        }
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        results.append(getElementClose());

        // Render this element to our writer
        TagUtils.getInstance().write(super.pageContext, results.toString());

        // Evaluate the remainder of this page
        return (EVAL_BODY_INCLUDE);

    }
    public int doEndTag() throws JspException {
        TagUtils.getInstance().write(super.pageContext, "</button>");
        return (EVAL_PAGE);

    }

    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

    //private IterateTag getIterateTag(Tag tag) {
    //    Tag parentTag = tag.getParent();
    //    if (parentTag instanceof IterateTag) {
    //        return (IterateTag) parentTag;
    //    } else {
    //        if (parentTag == null) {
    //            return null;
    //        } else {
    //            return getIterateTag(parentTag);
    //        }
    //    }
    //}
}
