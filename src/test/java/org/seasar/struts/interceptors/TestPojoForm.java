package org.seasar.struts.interceptors;

import java.io.Serializable;

/**
 * @author Katsuhiko Nagashima
 */
public class TestPojoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msg = "new";

    public TestPojoForm() {
    }

    public TestPojoForm(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
