package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.MultipartRequestWrapper;
import org.seasar.struts.Constants;
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
     * @param request
     * @return
     */
    public static MethodBinding getMethodBinding(HttpServletRequest request) {
        if (request instanceof MultipartRequestWrapper && request.getParameterNames().hasMoreElements()) {
            RequestUtil.decodeBase64Parameter(request);
        }

        String expression = (String) request.getAttribute(Constants.ACTION_EXPRESSION_KEY);
        if (expression == null) {
            return null;
        }

        if (IndexedUtil.isIndexedParameter(expression)) {
            String indexedKey = IndexedUtil.getParameter(expression);
            int index = IndexedUtil.getIndex(expression);
            if (expression != null) {
                return new MethodBinding(indexedKey, index);
            }
        }

        return new MethodBinding(expression);
    }
}
