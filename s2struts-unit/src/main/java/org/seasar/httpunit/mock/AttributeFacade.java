package org.seasar.httpunit.mock;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Satoshi Kimura
 */
public class AttributeFacade {
    protected Hashtable attributes = new Hashtable();

    protected AttributeFacade() {
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public Object removeAttribute(String name) {
        return attributes.remove(name);
    }

    public Enumeration getAttributeNames() {
        return attributes.keys();
    }
}