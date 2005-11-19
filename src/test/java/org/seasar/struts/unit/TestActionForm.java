package org.seasar.struts.unit;

import org.apache.struts.action.ActionForm;

/**
 * @author Satoshi Kimura
 */
public class TestActionForm extends ActionForm {

    private String val;

    public TestActionForm() {
    }

    /**
     * @return Returns the val.
     */
    public String getVal() {
        return val;
    }
    /**
     * @param val The val to set.
     */
    public void setVal(String val) {
        this.val = val;
    }
}