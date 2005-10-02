package org.seasar.httpunit.mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.Enumerator;
import org.apache.catalina.util.RequestUtil;
import org.apache.tomcat.util.http.FastHttpDateFormat;
import org.seasar.framework.container.S2Container;

/**
 * @author Satoshi Kimura
 */
public class HttpServletRequestDelegator extends AttributeFacade {
    private static SimpleDateFormat[] formats = {new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US)};
    private RequestDispatcher requestDispatcher;
    private MockHttpServletRequest request;
    private MockHttpSession session;
    private ServletContext context;
    private S2Container container;
    private Map parameters = new HashMap();
    private List cookies = new ArrayList();
    private Hashtable headers = new Hashtable();

    public HttpServletRequestDelegator() {
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
     */
    public HttpSession getSession(boolean create) {
        if ((session == null) && (create)) {
            this.session = createHttpSessionObject();
        } else if ((session != null) && (session.isValid() && (create))) {
            this.session = createHttpSessionObject();
            session.setValid(false);
        }

        if ((session != null) && (!session.isValid())) {
            return this.session;
        } else {
            return null;
        }
    }

    private MockHttpSession createHttpSessionObject() {
        return (MockHttpSession) container.getComponent(MockHttpSession.class);
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getSession()
     */
    public HttpSession getSession() {
        return getSession(true);
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getCookies()
     */
    public Cookie[] getCookies() {
        if (cookies.isEmpty()) {
            return null;
        } else {
            return (Cookie[]) cookies.toArray(new Cookie[cookies.size()]);
        }
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
     */
    public String getHeader(String name) {
        return (String) headers.get(name);
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
     */
    public Enumeration getHeaders(String name) {
        return new StringTokenizer(getHeader(name), ",");
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
     */
    public Enumeration getHeaderNames() {
        return headers.keys();
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
     */
    public long getDateHeader(String name) {
        String value = getHeader(name);

        if (value == null) {
            return -1L;
        }

        long result = FastHttpDateFormat.parseDate(value, formats);

        if (result != (-1L)) {
            return result;
        }

        throw new IllegalArgumentException(value);
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return requestDispatcher;
    }

    public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }

    public void setHttpServletRequest(MockHttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String getParameter(String name) {
        parseParameters();

        Object value = parameters.get(name);

        if (value == null) {
            return (null);
        } else if (value instanceof String[]) {
            return (((String[]) value)[0]);
        } else if (value instanceof String) {
            return ((String) value);
        } else {
            return (value.toString());
        }
    }

    public Map getParameterMap() {
        parseParameters();

        return parameters;
    }

    public Enumeration getParameterNames() {
        parseParameters();

        return new Enumerator(parameters.keySet());
    }

    public String[] getParameterValues(String name) {
        parseParameters();

        Object value = parameters.get(name);

        if (value == null) {
            return null;
        } else if (value instanceof String[]) {
            return (String[]) value;
        } else if (value instanceof String) {
            String[] values = new String[1];
            values[0] = (String) value;
            return values;
        } else {
            String[] values = new String[1];
            values[0] = value.toString();
            return values;
        }
    }

    public String getPathTranslated() {
        if (context == null) {
            return null;
        } else if (request.getPathInfo() == null) {
            return null;
        } else {
            return (context.getRealPath(request.getPathInfo()));
        }
    }

    public int getIntHeader(String name) {
        String value = request.getHeader(name);

        if (value == null) {
            return -1;
        }

        int ret = Integer.parseInt(value);

        return ret;
    }

    /**
     * @see MockHttpServletRequest#addParameter(String, String)
     */
    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    /**
     * @see MockHttpServletRequest#addParameter(String, String[])
     */
    public void addParameter(String name, String[] values) {
        parameters.put(name, values);
    }

    /**
     * @see MockHttpServletRequest#addCookie(Cookie)
     */
    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    /**
     * @see MockHttpServletRequest#addHeader(String, String)
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    /**
     * Merge the parameters from the saved query parameter string (if any), and the parameters already present on this
     * request (if any), such that the parameter values from the query string show up first if there are duplicate
     * parameter names.
     */
    private void parseParameters() {
        String queryParamString = request.getQueryString();

        if ((queryParamString == null) || (queryParamString.length() < 1)) {
            return;
        }

        HashMap queryParameters = new HashMap();
        String encoding = request.getCharacterEncoding();

        if (encoding == null) {
            encoding = "ISO-8859-1";
        }

        try {
            RequestUtil.parseParameters(queryParameters, queryParamString, encoding);
        } catch (Exception e) {
            // ignore
        }

        Iterator keys = parameters.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object value = queryParameters.get(key);

            if (value == null) {
                queryParameters.put(key, parameters.get(key));

                continue;
            }

            queryParameters.put(key, mergeValues(value, parameters.get(key)));
        }

        parameters.putAll(queryParameters);
    }

    /**
     * Merge the two sets of parameter values into a single String array.
     * 
     * @param values1 First set of values
     * @param values2 Second set of values
     */
    private String[] mergeValues(Object values1, Object values2) {
        List results = new ArrayList();
        addStringValues(results, values1);
        addStringValues(results, values2);

        String[] values = new String[results.size()];

        return ((String[]) results.toArray(values));
    }

    private void addStringValues(List list, Object value) {
        if (value == null) {
            ;
        } else if (value instanceof String) {
            list.add(value);
        } else if (value instanceof String[]) {
            String[] values = (String[]) value;

            for (int i = 0; i < values.length; i++) {
                list.add(values[i]);
            }
        } else {
            list.add(value.toString());
        }
    }
}