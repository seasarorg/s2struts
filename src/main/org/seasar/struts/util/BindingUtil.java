package org.seasar.struts.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.beans.IndexedPropertyDesc;
import org.seasar.struts.beans.impl.IndexedPropertyDescImpl;
import org.seasar.struts.config.StrutsActionPropertyConfig;
import org.seasar.struts.factory.AnnotationHandler;
import org.seasar.struts.factory.AnnotationHandlerFactory;

/**
 * @author Satoshi Kimura
 */
public class BindingUtil {
    private static Map primitiveMap = new HashMap();

    static {
        primitiveMap.put(Character.TYPE, Character.class);
        primitiveMap.put(Short.TYPE, Short.class);
        primitiveMap.put(Integer.TYPE, Integer.class);
        primitiveMap.put(Long.TYPE, Long.class);
        primitiveMap.put(Double.TYPE, Double.class);
        primitiveMap.put(Float.TYPE, Float.class);
        primitiveMap.put(Boolean.TYPE, Boolean.class);
    }

    private BindingUtil() {
    }

    private static Class getPrimitiveWrappedClass(Class primitiveClass) {
        return (Class) primitiveMap.get(primitiveClass);
    }

    private static Object getValue(S2Container container, String name) {
        HttpServletRequest request = container.getRequest();
        Object var = request.getParameter(name);
        if (var != null) {
            return var;
        }
        var = request.getAttribute(name);
        if (var != null) {
            return var;
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            var = session.getAttribute(name);
            if (var != null) {
                return var;
            }
        }
        if (container.hasComponentDef(name)) {
            return container.getComponent(name);
        }
        return null;
    }

    public static void importProperties(Object action, S2Container container, BeanDesc beanDesc, ActionMapping mapping) {
        importParameter(action, container);
        for (int i = 0; i < beanDesc.getPropertyDescSize(); i++) {
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(i);
            String propertyName = propertyDesc.getPropertyName();
            Class propertyType = propertyDesc.getPropertyType();
            if (propertyDesc.hasWriteMethod()) {
                Object value = BindingUtil.getValue(container, propertyName);
                if (BindingUtil.isActionFormProperty(propertyDesc, mapping)) {
                    value = ActionFormUtil.getActualForm(container.getRequest(), mapping);
                }
                if (value != null) {
                    if (propertyType.isPrimitive()) {
                        propertyType = getPrimitiveWrappedClass(propertyType);
                    }
                    if (propertyType.isInstance(value) == true) {
                        propertyDesc.setValue(action, value);
                    }
                }
            }
        }
    }

    private static void importParameter(Object action, S2Container container) {
        Enumeration paramNames = container.getRequest().getParameterNames();
        BeanDesc beanDesc = new BeanDescImpl(action.getClass());
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if (paramName.endsWith("]") == false) {
                continue;
            }
            StringTokenizer tokenizer = new StringTokenizer(paramName, "[]");
            if (tokenizer.countTokens() == 2) {
                String propertyName = tokenizer.nextToken();
                int index = 0;
                try {
                    index = IntegerConversionUtil.toPrimitiveInt(tokenizer.nextToken());
                } catch (NumberFormatException e) {
                    continue;
                }
                String paramValue = container.getRequest().getParameter(paramName);
                IndexedPropertyDesc propertyDesc = new IndexedPropertyDescImpl(propertyName, String.class, beanDesc);
                if (propertyDesc.hasWriteMethod()) {
                    propertyDesc.setValue(action, index, paramValue);
                }
            }
        }
    }

    public static void exportProperties(Object action, S2Container container, BeanDesc beanDesc, ActionMapping mapping) {
        for (int i = 0; i < beanDesc.getPropertyDescSize(); ++i) {
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(i);
            if (propertyDesc.hasReadMethod()) {
                Object var = propertyDesc.getValue(action);
                if (var != null) {
                    if (BindingUtil.isActionFormProperty(propertyDesc, mapping)) {
                        ActionFormUtil.setActualForm(container.getRequest(), var, mapping);
                    } else {
                        AnnotationHandler annHandler = AnnotationHandlerFactory.getAnnotationHandler();
                        StrutsActionPropertyConfig propertyConfig = annHandler.createStrutsActionPropertyConfig(
                                beanDesc, propertyDesc);
                        if (propertyConfig.isSessionScope()) {
                            container.getSession().setAttribute(propertyDesc.getPropertyName(), var);
                        } else {
                            container.getRequest().setAttribute(propertyDesc.getPropertyName(), var);
                        }
                    }
                }
            }
        }
    }

    private static boolean isActionFormProperty(PropertyDesc propertyDesc, ActionMapping mapping) {
        return propertyDesc.getPropertyName().equals(mapping.getAttribute());
    }

}