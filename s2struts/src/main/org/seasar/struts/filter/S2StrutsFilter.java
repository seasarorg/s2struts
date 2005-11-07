package org.seasar.struts.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.context.S2StrutsContext;

/**
 * @author Satoshi Kimura
 */
public class S2StrutsFilter implements Filter {

    //private FilterConfig config;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        S2StrutsContext context = (S2StrutsContext) container.getComponent(S2StrutsContext.class);

        setPath(context, (HttpServletRequest) req);

        chain.doFilter(req, res);
    }

    private void setPath(S2StrutsContext context, HttpServletRequest request) {
        context.setPath(request.getServletPath());
    }

    public void init(FilterConfig config) throws ServletException {
        //this.config = config;
    }

    public void destroy() {
        //config = null;
    }

}
