package org.seasar.struts.pojo.processor;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TestCheckboxForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private boolean check;
    
    public TestCheckboxForm(boolean check) {
        this.check = check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return this.check;
    }

}
