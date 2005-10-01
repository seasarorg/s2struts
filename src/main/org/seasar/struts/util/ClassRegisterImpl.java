package org.seasar.struts.util;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.util.ClassUtil;

/**
 * @author Satoshi Kimura
 */
public class ClassRegisterImpl implements ClassRegister {
    private Map classes = new HashMap();

    public ClassRegisterImpl() {
    }

    public void regist(String type) {
        getClass(type);
    }
    
    public void regist(Class clazz) {
        getClass(clazz.getName());
    }
    
    public synchronized Class getClass(String type) {
        Class clazz = (Class) classes.get(type);
        if (clazz == null) {
            clazz = ClassUtil.forName(type);
            classes.put(type, clazz);
        }
        return clazz;
    }

    public synchronized void destroy() {
        classes = null;
    }

}