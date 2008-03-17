package org.seasar.struts.processor;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author taedium
 */
public class PathResolverImpl implements PathResolver {

    public String resolve(HttpServletRequest request, String path) {
        return path;
    }
}
