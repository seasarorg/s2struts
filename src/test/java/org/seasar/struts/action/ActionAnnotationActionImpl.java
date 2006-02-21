package org.seasar.struts.action;

import org.seasar.struts.Constants;

/**
 * @author Katsuhiko Nagashima
 */
public class ActionAnnotationActionImpl implements ActionAnnotationAction {

    private String bar;

    private String baz;

    private String foo;
    
    public static final String bar_EXPORT = Constants.SESSION;

    private static final int foo_EXPORT = 0;

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public static final String baz_EXPORT = Constants.SESSION;
    
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