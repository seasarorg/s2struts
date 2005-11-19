package org.seasar.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.Constants;

/**
 * @author Satoshi Kimura
 */
public class POJOActionImpl implements POJOAction {
    private String foo;
    private int bar;
    private String baz;
    private String[] qux = {"", "", null, null};
    private HttpServletRequest request;

    public final static String bar_EXPORT = Constants.SESSION;

    /**
     * @return Returns the bar.
     */
    public int getBar() {
        return bar;
    }
    /**
     * @param bar The bar to set.
     */
    public void setBar(int bar) {
        this.bar = bar;
    }
    /**
     * @return Returns the baz.
     */
    public String getBaz() {
        return baz;
    }
    /**
     * @param baz The baz to set.
     */
    public void setBaz(String baz) {
        this.baz = baz;
    }
    /**
     * @return Returns the foo.
     */
    public String getFoo() {
        return foo;
    }
    /**
     * @param foo The foo to set.
     */
    public void setFoo(String foo) {
        this.foo = foo;
    }
    /**
     * @return Returns the qux.
     */
    public String[] getQux() {
        return qux;
    }
    /**
     * @param qux The qux to set.
     */
    public void setQux(String[] qux) {
        this.qux = qux;
    }
    /**
     * @param qux The qux to set.
     */
    public void setQux(int i, String qux) {
        this.qux[i] = qux;
    }

    /**
     * @return Returns the request.
     */
    public HttpServletRequest getRequest() {
        return request;
    }
    /**
     * @param request The request to set.
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String exe() {
        return "success";
    }
}