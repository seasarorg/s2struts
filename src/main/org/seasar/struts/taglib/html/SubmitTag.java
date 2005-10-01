package org.seasar.struts.taglib.html;

import javax.servlet.jsp.JspException;

import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.util.RandomUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 */
public class SubmitTag extends org.apache.struts.taglib.html.SubmitTag {
    protected String indexId;
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
        indexId = null;
        action = null;
    }

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

        if (indexId != null) {
            super.indexed = true;
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    protected void prepareIndex(StringBuffer handlers, String name) throws JspException {
        if (indexId == null) {
            super.prepareIndex(handlers, name);
            return;
        }
        Object value = super.pageContext.getAttribute(indexId);
        handlers.append("[");
        handlers.append(IntegerConversionUtil.toPrimitiveInt(value));
        handlers.append("]");
    }
    
    protected void setMethodBindingExpression() {
        if (StringUtil.isEmpty(property)) {
            property = RandomUtil.randomString();
        }
        String value = super.value;
        if(value == null) {
            value = super.text;
        }
        if(value == null) {
            value = super.getDefaultValue();
        }
        S2StrutsContextUtil.setMethodBindingExpression(property, value, action);
    }
}