package org.seasar.struts.examples;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.seasar.framework.container.hotdeploy.HotdeployBehavior;
import org.seasar.framework.container.impl.S2ContainerBehavior;

public class MyHotDeployFilter implements Filter {

    private static final String KEY = MyHotDeployFilter.class.getName();

    // private FilterConfig config = null;

    public MyHotDeployFilter() {
    }

    public void init(FilterConfig config) throws ServletException {
        // this.config = config;
    }

    public void destroy() {
        // config = null;
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        if (request.getAttribute(KEY) == null) {
            S2ContainerBehavior.Provider provider = S2ContainerBehavior
                    .getProvider();
            if (provider instanceof HotdeployBehavior) {
                HotdeployBehavior ondemand = (HotdeployBehavior) provider;
                ondemand.start();
                request.setAttribute(KEY, Thread.currentThread()
                        .getContextClassLoader());
                try {
                    chain.doFilter(request, response);
                } finally {
                    ondemand.stop();
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            ClassLoader cl = (ClassLoader) request.getAttribute(KEY);
            Thread.currentThread().setContextClassLoader(cl);
            chain.doFilter(request, response);
        }
    }
}
