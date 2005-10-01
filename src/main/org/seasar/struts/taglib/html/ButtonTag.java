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
        return indexId;
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
        if (type != null) {
            results.append(" type=\"");
            results.append(type);
            results.append("\"");
        }
        if (property != null) {
            results.append(" name=\"");
            results.append(property);
            if (indexed)
                prepareIndex(results, null);
            results.append("\"");
        }
        if (accesskey != null) {
            results.append(" accesskey=\"");
            results.append(accesskey);
            results.append("\"");
        }
        if (tabindex != null) {
            results.append(" tabindex=\"");
            results.append(tabindex);
            results.append("\"");
        }
        if (indexId != null) {
            results.append(" value=\"");
            Object indexValue = super.pageContext.getAttribute(indexId);
            results.append(IntegerConversionUtil.toPrimitiveInt(indexValue));
            results.append("\"");
        } else {
            results.append(" value=\"");
            results.append(value);
            results.append("\"");
        }
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        results.append(getElementClose());

        // Render this element to our writer
        TagUtils.getInstance().write(pageContext, results.toString());

        // Evaluate the remainder of this page
        return (EVAL_BODY_INCLUDE);

    }
    public int doEndTag() throws JspException {
        TagUtils.getInstance().write(pageContext, "</button>");
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
