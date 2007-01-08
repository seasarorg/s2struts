package org.seasar.struts.pojo.commands;

public class TestSingleMethodImplementAction {

    public String execute() {
        return "success";
    }

    protected String protectedMethod() {
        privateMethod();
        return "";
    }

    private String privateMethod() {
        return "";
    }

}
