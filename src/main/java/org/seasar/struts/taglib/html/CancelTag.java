package org.seasar.struts.taglib.html;

import javax.servlet.jsp.JspException;

public class CancelTag extends SubmitTag {

    private static final long serialVersionUID = -5828952087103949643L;

    private static final String DEFAULT_PROPERTY = "org.seasar.struts.taglib.html.CancelTag.CANCEL";

    public CancelTag() {
        super();
        // property = Constants.CANCEL_PROPERTY;
        super.cancel = true;

    }

    public int doEndTag() throws JspException {
        if (super.action == null) {
            super.property = DEFAULT_PROPERTY;
        }
        return super.doEndTag();
    }

    /**
     * Returns the onClick event handler.
     */
    public String getOnclick() {
        return super.getOnclick() == null ? "bCancel=true;" : super.getOnclick();
    }

    // /**
    // * Render the opening element.
    // *
    // * @return The opening part of the element.
    // */
    // protected String getElementOpen() {
    // return "<input type=\"submit\"";
    // }

    /**
     * Prepare the name element
     * 
     * @return The element name.
     */
    protected String prepareName() throws JspException {
        return property;
    }

    /**
     * Return the default value.
     * 
     * @return The default value if none supplied.
     */
    protected String getDefaultValue() {
        return "Cancel";
    }

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        super.cancel = true;
        // property = Constants.CANCEL_PROPERTY;

    }

}
