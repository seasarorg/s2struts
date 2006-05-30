package org.seasar.struts.pojo;

public class TestMethodBindingActionImpl implements TestMethodBindingAction {

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
