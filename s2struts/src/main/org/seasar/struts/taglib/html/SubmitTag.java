package org.seasar.struts.taglib.html;

import javax.servlet.jsp.JspException;

import org.seasar.framework.util.Base64Util;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.framework.util.StringUtil;
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
        this.indexId = null;
        this.action = null;
    }

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

        if (indexId != null) {
            super.indexed = true;
        }
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    protected void prepareIndex(StringBuffer handlers, String name) throws JspException {
        if (this.indexId == null) {
            super.prepareIndex(handlers, name);
            return;
        }
        Object index = super.pageContext.getAttribute(this.indexId);
        handlers.append("[");
        handlers.append(IntegerConversionUtil.toPrimitiveInt(index));
        handlers.append("]");
    }
    
    protected void setMethodBindingExpression() {
        if (StringUtil.isEmpty(super.property)) {
            super.property = Base64Util.encode(this.action.getBytes());
        }
        String val = super.value;
        if(val == null) {
            val = super.text;
        }
        if(val == null) {
            val = super.getDefaultValue();
        }
        S2StrutsContextUtil.setMethodBindingExpression(super.property, val, this.action);
    }
}