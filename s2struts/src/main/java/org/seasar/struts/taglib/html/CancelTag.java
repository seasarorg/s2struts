package org.seasar.struts.taglib.html;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.Constants;

public class CancelTag extends SubmitTag {
    private static final long serialVersionUID = -4527737065254142441L;

    public CancelTag() {
        super();
        property = Constants.CANCEL_PROPERTY;

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
        property = Constants.CANCEL_PROPERTY;

    }

}
