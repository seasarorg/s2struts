package org.seasar.struts.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Katsuhiko Nagashima
 */
public class RequestUtil {
    
    private RequestUtil() {
        
    }
    
    public static Object getValue(HttpServletRequest request, String name) {
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
        return null;
    }

}
