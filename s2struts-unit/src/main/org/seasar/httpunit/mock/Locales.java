package org.seasar.httpunit.mock;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/**
 * @author Satoshi Kimura
 */
public class Locales implements Enumeration {
    Iterator locales = null;
    List list = new ArrayList();

    public Locales() {
    }

    public boolean hasMoreElements() {
        return locales.hasNext();
    }

    public Object nextElement() {
        return locales.next();
    }

    public void add(Locale locale) {
        list.add(locale);
        locales = list.iterator();
    }
}