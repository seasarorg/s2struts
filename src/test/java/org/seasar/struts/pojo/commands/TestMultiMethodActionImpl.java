package org.seasar.struts.pojo.commands;

public class TestMultiMethodActionImpl implements TestMultiMethodAction {

    public String execute() {
        return "success";
    }

    public String download() {
        return null;
    }

    public String execute(int index) {
        return "success" + String.valueOf(index);
    }

    public String download(int index) {
        return null;
    }

}
