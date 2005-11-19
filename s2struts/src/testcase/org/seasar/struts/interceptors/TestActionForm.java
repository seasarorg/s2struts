package org.seasar.struts.interceptors;

import org.apache.struts.action.ActionForm;

/**
 * @author Katsuhiko Nagashima
 */
public class TestActionForm extends ActionForm {

    private String msg = "new";

    public TestActionForm() {
    }

    public TestActionForm(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}