package org.seasar.struts.action;

/**
 * @author Katsuhiko Nagashima
 */
public class ActionAnnotationActionImpl implements ActionAnnotationAction {

    private String bar;

    private String baz;

    private String foo;
    
    /**
     * @org.seasar.struts.annotation.backport175.ExportToSession
     */
    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    /**
     * @org.seasar.struts.annotation.backport175.Export(org.seasar.struts.Constants.SESSION)
     */
    public String getBaz() {
        return baz;
    }

    public void setBaz(String baz) {
        this.baz = baz;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String exe() {
        return "success";
    }

}