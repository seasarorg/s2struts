package org.seasar.struts.beans;

/**
 * @author Satoshi Kimura
 */
public interface IndexedPropertyDesc {
    void setValue(Object target, int index, Object value);

    Object getValue(Object target, int index);

    boolean hasReadMethod();
    
    boolean hasWriteMethod();
}