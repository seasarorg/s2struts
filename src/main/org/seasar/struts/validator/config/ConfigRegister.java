package org.seasar.struts.validator.config;

import java.lang.reflect.Method;

import org.apache.commons.validator.Field;

/**
 * @author Satoshi Kimura
 */
public interface ConfigRegister {
    void regist(Field field, Method method);
}
