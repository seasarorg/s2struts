package org.seasar.struts.processor;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author taedium
 */
public interface PathResolver {

    String resolve(HttpServletRequest request, String path);
}
