package org.seasar.struts.action;

public class MultiMethodPojoActionImpl implements MultiMethodPojoAction {

    public String exe() {
        return "success";
    }

    public String download() {
        return null;
    }

}
