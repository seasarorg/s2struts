package org.seasar.struts.action;

public class MethodBindingActionImpl implements MethodBindingAction {

    public String exe() {
        return "success";
    }

    public String exe(int index) {
        return "success" + String.valueOf(index);
    }

    public String download() {
        return null;
    }

}
