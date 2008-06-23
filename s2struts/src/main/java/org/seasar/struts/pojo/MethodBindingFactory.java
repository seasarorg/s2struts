package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.pojo.util.IndexedUtil;
import org.seasar.struts.util.RequestUtil;

/**
 * {@link MethodBinding}のファクトリです。
 * 
 * @author taedium
 */
public class MethodBindingFactory {

    private MethodBindingFactory() {
    }

    /**
     * {@link MethodBinding}を返します。
     * 
     * @param expression
     * @return
     */
    public static MethodBinding getMethodBinding(String expression) {
        return new MethodBinding(expression);
    }

    /**
     * {@link MethodBinding}を返します。
     * 
     * @param request
     * @return
     */
    public static MethodBinding getMethodBinding(HttpServletRequest request) {
        String expression = RequestUtil.getActionExpression(request);
        if (expression == null) {
            return null;
        }

        if (IndexedUtil.isIndexedParameter(expression)) {
            int index = IndexedUtil.getIndex(expression);
            if (expression != null) {
                return new MethodBinding(IndexedUtil.getParameter(expression), index);
            }
        }

        return new MethodBinding(expression);
    }
}
