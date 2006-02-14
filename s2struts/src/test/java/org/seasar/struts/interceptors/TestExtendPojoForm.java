package org.seasar.struts.interceptors;

public class TestExtendPojoForm extends TestPojoForm {

    public TestExtendPojoForm(String msg) {
        super("extend:" + msg);
    }
    
}
