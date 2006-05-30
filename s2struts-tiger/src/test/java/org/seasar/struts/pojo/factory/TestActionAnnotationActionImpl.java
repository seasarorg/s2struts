package org.seasar.struts.pojo.factory;

import org.seasar.struts.annotation.tiger.Export;
import org.seasar.struts.annotation.tiger.ExportToSession;
import org.seasar.struts.annotation.tiger.ScopeType;

/**
 * @author Katsuhiko Nagashima
 */
public class TestActionAnnotationActionImpl implements TestActionAnnotationAction {

    private String bar;

    private String baz;

    private String foo;

    @ExportToSession
    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    @Export(ScopeType.SESSION)
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