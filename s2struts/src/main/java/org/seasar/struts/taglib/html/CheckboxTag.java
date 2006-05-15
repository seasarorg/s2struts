package org.seasar.struts.taglib.html;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.seasar.struts.Constants;

/**
 * @author Satoshi Kimura
 */
public class CheckboxTag extends org.apache.struts.taglib.html.CheckboxTag {
	private static final long serialVersionUID = 5387589565117383287L;

	public CheckboxTag() {
		super();
	}

	public int doStartTag() throws JspException {
		int ret = super.doStartTag();

		StringBuffer hidden = new StringBuffer();
		hidden.append("<input");
		prepareAttribute(hidden, "type", "hidden");
		prepareAttribute(hidden, "name", Constants.CHECKBOX_NAME + prepareName());
		prepareAttribute(hidden, "value", Boolean.TRUE);
		hidden.append(getElementClose());

		TagUtils.getInstance().write(super.pageContext, hidden.toString());
		return ret;
	}
}
