package org.seasar.struts.pojo.commands;

public class TestSingleMethodNoPublicMethodAction {

    protected String protectedMethod() {
        privateMethod();
        return "";
    }

    private String privateMethod() {
        return "";
    }

}
