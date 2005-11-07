package org.seasar.httpunit.mock;

import javax.servlet.ServletContext;

/**
 * @author Satoshi Kimura
 */
public class HttpSessionDelegator extends AttributeFacade {
    private MockHttpSession session;
    private ServletContext context;

    public HttpSessionDelegator() {
    }

    public void setExtendHttpSession(MockHttpSession session) {
        this.session = session;
    }

    public ServletContext getServletContext() {
        return context;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }

    public void putValue(String name, Object value) {
        setAttribute(name, value);
    }

    public Object getValue(String name) {
        return getAttribute(name);
    }

    public void removeValue(String name) {
        removeAttribute(name);
    }

    public String[] getValueNames() {
        return ((String[]) attributes.keySet().toArray(new String[0]));
    }

    public void invalidate() {
        session.setValid(true);
    }
}