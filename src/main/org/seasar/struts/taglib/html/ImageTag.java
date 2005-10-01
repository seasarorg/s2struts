package org.seasar.struts.taglib.html;

import javax.servlet.jsp.JspException;

import org.seasar.framework.util.StringUtil;
import org.seasar.struts.util.RandomUtil;
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
        action = null;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    protected void setMethodBindingExpression() {
        if (StringUtil.isEmpty(property)) {
            property = RandomUtil.randomString();
        }
        S2StrutsContextUtil.setMethodBindingExpression(property, null, action);
    }

}
