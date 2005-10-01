package org.seasar.struts.util;

import org.apache.struts.config.ForwardConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.context.ContentsType;
import org.seasar.struts.context.S2StrutsContext;

/**
 * @author Satoshi Kimura
 */
public abstract class S2StrutsContextUtil {

    public static void clear(ContentsType type) {
        getContext().clear(type);
    }

    public static void setPath(ForwardConfig forward) {
        getContext().setPath(forward.getPath());
    }

    public static void setPath(String path) {
        getContext().setPath(path);
    }

    public static String getPath() {
        return getContext().getPath();
    }
    
    public static void setMethodBindingExpression(String key, String value, String methodBindingExpression) {
        getContext().setMethodBindingExpression(key, value, methodBindingExpression);
    }
    
    public static String getMethodBindingExpression() {
        return getContext().getMethodBindingExpression();
    }

    private static S2StrutsContext getContext() {
        return (S2StrutsContext) getContainer().getComponent(S2StrutsContext.class);
    }

    private static S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }
}
