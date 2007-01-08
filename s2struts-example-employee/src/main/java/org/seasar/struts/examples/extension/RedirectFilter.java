package org.seasar.struts.examples.extension;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
        this.config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (isRedirect(httpRequest, httpResponse)) {
            saveRequestUri(httpRequest, httpResponse);
            redirect(httpRequest, httpResponse);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isRedirect(HttpServletRequest request,
            HttpServletResponse response) {

        if (request.getSession().getAttribute(getSessionKey()) != null) {
            return false;
        }

        String uri = getRedirectUri(request);
        if (request.getRequestURI().equalsIgnoreCase(uri)) {
            return false;
        }

        String[] ignoreUris = getIgnoreUris(request);
        for (int i = 0; i < ignoreUris.length; i++) {
            if (request.getRequestURI().toLowerCase().startsWith(
                    ignoreUris[i].toLowerCase())) {
                return false;
            }
        }

        return true;
    }

    private void redirect(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String uri = getRedirectUri(request);
        response.sendRedirect(response.encodeRedirectURL(uri));
    }

    private void saveRequestUri(HttpServletRequest request,
            HttpServletResponse response) {

        if (!request.getMethod().equalsIgnoreCase("GET")) {
            request.getSession().removeAttribute(getRequestUriKey());
            return;
        }

        request.getSession().setAttribute(getRequestUriKey(),
                request.getRequestURI());
    }

    private String getSessionKey() {
        return this.config.getInitParameter("sessionKey");
    }

    private String getRequestUriKey() {
        return this.config.getInitParameter("requestUriKey");
    }

    private String getRedirectUri(HttpServletRequest request) {
        String path = this.config.getInitParameter("redirectPath");
        return request.getContextPath() + path;
    }

    private String[] getIgnoreUris(HttpServletRequest request) {
        String ignorePath = this.config.getInitParameter("ignorePath");
        String[] result = ignorePath.split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = request.getContextPath() + result[i].trim();
        }
        return result;
    }

}
