package org.seasar.struts.validator.config;

import java.util.Map;

import org.apache.commons.validator.Field;

/**
 * @author Satoshi Kimura
 */
public interface ConfigRegister {
    void regist(Field field, Map parameters);
}
