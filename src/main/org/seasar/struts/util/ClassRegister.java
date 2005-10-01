package org.seasar.struts.util;

/**
 * @author Satoshi Kimura
 */
public interface ClassRegister {
    void regist(String type);
    void regist(Class clazz);
    Class getClass(String type);
    void destroy();
}