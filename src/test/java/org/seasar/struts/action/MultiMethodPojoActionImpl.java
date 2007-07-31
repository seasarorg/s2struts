package org.seasar.struts.action;

public class MultiMethodPojoActionImpl implements MultiMethodPojoAction {

    public String exe() {
        return "success";
    }

    public String download() {
        return null;
    }

    public String exe(int index) {
        return "success" + String.valueOf(index);
    }

    public String download(int index) {
        return null;
    }

}
