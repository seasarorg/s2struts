package org.seasar.httpunit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.LifecycleException;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.httpunit.core.StandardContext;
import org.seasar.httpunit.exception.FileNotFoundRuntimeException;
import org.seasar.httpunit.exception.LifecycleRuntimeException;
import org.seasar.httpunit.exception.ServletRuntimeException;
import org.seasar.httpunit.mock.HttpServletRequestDelegator;
import org.seasar.httpunit.mock.MockHttpServletRequest;
import org.seasar.httpunit.mock.MockHttpServletResponse;
import org.seasar.httpunit.mock.MockObjectInterceptor;
import org.seasar.httpunit.mock.MockObjectInterceptorMapping;

/**
 * @author Satoshi Kimura
 */
public abstract class S2HttpTestCase extends S2TestCase {
    private boolean useS2MockObject;
    private StandardContext context;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ServletContext servletContext;
    private String path;
    private String webappsName;
    private int portStringLength = 0;

    /**
     * <p>
     * 指定された名前でテストケースを作成します。
     * </p>
     * <p>
     * S2HttpUnitで提供するモックオブジェクトを使用します。
     * </p>
     * 
     * @param name テストケースの名前
     */
    protected S2HttpTestCase(String name) {
        this(name, false);
    }

    /**
     * <p>
     * 指定された名前でテストケースを作成します。
     * </p>
     * <p>
     * S2のextensionパッケージに含まれるモックオブジェクトを選択します。 <br>
     * 使用する場合、useS2MockObjectにtrueを設定します。falseの場合、S2HttpUnitで提供するモックオブジェクトを使用します。
     * </p>
     * 
     * @param name テストケースの名前
     * @param useS2MockObject 使用するモックオブジェクトの指定
     */
    protected S2HttpTestCase(String name, boolean useS2MockObject) {
        super(name);
        this.useS2MockObject = useS2MockObject;
    }

    /**
     * @see S2TestCase#setUpContainer()
     */
    protected void setUpContainer() throws Throwable {
        super.setUpContainer();

        if (useS2MockObject == false) {
            setServletContext(null);
            setRequest(null);
            setResponse(null);
            getContainer().setServletContext(null);
            getContainer().setRequest(null);
            getContainer().setResponse(null);
        }
    }

    /**
     * @see S2TestCase#setUpAfterContainerInit()
     */
    protected void setUpAfterContainerInit() throws Throwable {
        if (useS2MockObject == false) {
            MockHttpServletRequest req = (MockHttpServletRequest) getComponent(MockHttpServletRequest.class);
            MockHttpServletResponse res = (MockHttpServletResponse) getComponent(MockHttpServletResponse.class);
            setMockReq(req);
            setMockRes(res);
            getContainer().setRequest(req);
            getContainer().setResponse(res);

            try {
                HttpServletRequestDelegator delegator = (HttpServletRequestDelegator) getComponent(HttpServletRequestDelegator.class);
                delegator.setContainer(getContainer());
            } catch (ComponentNotFoundRuntimeException e) {
                //ignore
            }
        }
    }

    /**
     * <p>
     * HttpServletRequestのモックオブジェクトを返します。
     * </p>
     * 
     * @return HttpServletRequestのモックオブジェクト
     */
    protected MockHttpServletRequest getMockReq() {
        return request;
    }

    private void setMockReq(MockHttpServletRequest request) {
        this.request = request;
    }

    /**
     * <p>
     * HttpServletRequestを返します。
     * </p>
     * 
     * @return コンストラクタで指定したモックオブジェクト
     * @see S2TestCase#getRequest()
     * @see S2HttpTestCase#getMockReq()
     */
    protected HttpServletRequest getHttpServletRequest() {
        if (useS2MockObject) {
            return getRequest();
        } else {
            return getMockReq();
        }
    }

    /**
     * <p>
     * MockHttpServletResponseのモックオブジェクトを返します。
     * </p>
     * 
     * @return MockHttpServletResponseのモックオブジェクト
     */
    protected MockHttpServletResponse getMockRes() {
        return response;
    }

    private void setMockRes(MockHttpServletResponse response) {
        this.response = response;
    }

    /**
     * <p>
     * HttpServletResponseを返します。
     * </p>
     * 
     * @return コンストラクタで指定したモックオブジェクト
     * @see S2TestCase#getResponse()
     * @see S2HttpTestCase#getMockRes()
     */
    protected HttpServletResponse getHttpServletResponse() {
        if (useS2MockObject) {
            return getResponse();
        } else {
            return getMockRes();
        }
    }

    /**
     * <p>
     * {@link #setDocBase(String)}メソッドを使用した場合に、ServletContextを返します。 <br>
     * そうでない場合は、nullを返します。
     * <p>
     * 
     * @return ServletContextオブジェクト
     */
    protected ServletContext getContext() { // todo 名前変更?
        return servletContext;
    }

    private void setContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * <p>
     * ServletContextを返します。
     * </p>
     * 
     * @return コンストラクタで指定したオブジェクト
     * @see S2TestCase#getServletContext()
     * @see S2HttpTestCase#getContext()
     */
    protected ServletContext getServletCxt() { // XXX 名前変更したい =>getServletContext
        if (useS2MockObject) {
            return getServletContext();
        } else {
            return getContext();
        }
    }

    protected void setDocBase(String docBase) {
        String docBasePath = convertDocBasePath(docBase);
        SingletonS2ContainerFactory.init();
        setContext(createServletContext(docBasePath));
        getContainer().setServletContext(this.servletContext);
        loadOnStartup();
    }

    protected HttpServlet getHttpServlet(String servletName) {
        return context.getHttpServlet(servletName);
    }

    protected HttpServlet getHttpServlet(Class servletClass) {
        return context.getHttpServlet(servletClass);
    }

    protected String getWebappsName() {
        return webappsName;
    }

    protected void setWebappsName(String webappsName) {
        this.webappsName = webappsName;
        setReturnValue(MockHttpServletRequest.class, "getContextPath", "/" + webappsName);
    }

    protected String getPath() {
        return path;
    }

    protected void setPath(String path) {
        this.path = path;

        String url = createRequestURL();
        MockObjectInterceptor interceptor = getMockComponent(MockHttpServletRequest.class);

        int pos = url.indexOf('?');

        if (pos < 0) {
            pos = Integer.MAX_VALUE;
        } else {
            String queryString = url.substring(pos + 1);
            interceptor.setReturnValue("getQueryString", queryString);
        }

        String requestURL = url.substring(0, pos);
        String requestURI = requestURL.substring(url.indexOf(request.getServerName())
                + request.getServerName().length() + portStringLength);
        interceptor.setReturnValue("getRequestURI", requestURI);
        interceptor.setReturnValue("getRequestURL", new StringBuffer(requestURL));

        String servletPath = requestURI;

        if (hasWebappsName() == true) {
            pos = requestURI.indexOf(getWebappsName());
            servletPath = requestURI.substring(pos + getWebappsName().length());
        }

        interceptor.setReturnValue("getServletPath", servletPath);
    }

    private boolean hasWebappsName() {
        return !((getWebappsName() == null) || (getWebappsName().length() == 0));
    }

    private String createRequestURL() {
        String scheme = request.getScheme();
        int port = request.getServerPort();
        StringBuffer urlBuffer = new StringBuffer(scheme);
        urlBuffer.append("://");
        urlBuffer.append(request.getServerName());
        portStringLength = 0;

        if (0 < port) {
            if ("http".equals(scheme) && (80 == port)) {
            } else if ("https".equals(scheme) && (443 == port)) {
            } else {
                urlBuffer.append(":" + request.getServerPort());
                portStringLength = ":".length() + Integer.toString(request.getServerPort()).length();
            }
        }

        if (getWebappsName() != null) {
            urlBuffer.append("/");
            urlBuffer.append(getWebappsName());
        }

        urlBuffer.append(path);

        String url = urlBuffer.toString();

        return url;
    }

    public void runBare() throws Throwable {
        setUpForS2HttpTestCase();

        try {
            super.runBare();
        } finally {
            tearDownForS2HttpTestCase();
        }
    }

    private void setUpForS2HttpTestCase() {
    }

    private void tearDownForS2HttpTestCase() {
        try {
            if (context != null) {
                context.stop();
            }
        } catch (LifecycleException e) {
            throw new LifecycleRuntimeException(e);
        }
    }

    private void loadOnStartup() {
        try {
            context.loadOnStartup();
            context.getHttpServlets();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } catch (ServletException e) {
            throw new ServletRuntimeException(e);
        }
    }

    private ServletContext createServletContext(String docBase) {
        context = new StandardContext();
        context.setDocBase(docBase);

        try {
            context.start();
        } catch (LifecycleException e) {
            throw new LifecycleRuntimeException(e);
        }

        return context.getServletContext();
    }

    private String convertDocBasePath(String path) {
        String prefix = getClass().getPackage().getName().replace('.', '/');

        if (path.length() != 0) {
            path = path + "/";
        }

        path = path + "WEB-INF";

        String resourcePath = prefix + "/" + path;

        if (ResourceUtil.getResourceNoException(path) != null) {
            return new File(ResourceUtil.getClassLoader().getResource(path).getFile()).getParent();
        } else if (ResourceUtil.getResourceNoException(resourcePath) != null) {
            path = resourcePath;

            return new File(ResourceUtil.getClassLoader().getResource(path).getFile()).getParent();
        } else {
            if (new File(path).exists() == false) {
                throw new FileNotFoundRuntimeException(path);
            }

            return new File(path).getParent();
        }
    }

    protected void setRequestReturnValue(String methodName, Object value) {
        setReturnValue(MockHttpServletRequest.class, methodName, value);
    }

    protected void setRequestReturnValue(String methodName, Object key, Object value) {
        setReturnValue(MockHttpServletRequest.class, methodName, key, value);
    }

    protected void setResponseReturnValue(String methodName, Object value) {
        setReturnValue(MockHttpServletResponse.class, methodName, value);
    }

    protected void setResponseReturnValue(String methodName, Object key, Object value) {
        setReturnValue(MockHttpServletResponse.class, methodName, key, value);
    }

    private void setReturnValue(Class clazz, String methodName, Object value) {
        MockObjectInterceptor interceptor = getMockComponent(clazz);
        interceptor.setReturnValue(methodName, value);
    }

    private void setReturnValue(Class clazz, String methodName, Object key, Object value) {
        MockObjectInterceptor interceptor = getMockComponent(clazz);
        interceptor.setReturnValue(methodName, key, value);
    }

    protected void sort(String[] arrays) {
        Arrays.sort(arrays);
    }

    protected static List toSortedList(Enumeration e) {
        if (e == null) {
            return null;
        }

        List sortedList = new ArrayList();

        while (e.hasMoreElements()) {
            sortedList.add(e.nextElement());
        }

        Collections.sort(sortedList);

        return sortedList;
    }

    private MockObjectInterceptor getMockComponent(Class clazz) {
        return (MockObjectInterceptor) getComponent(MockObjectInterceptorMapping.getMockObjectInterceptorName(clazz));
    }

    // TODO S2TestCaseに移動
    public void assertEquals(String message, Object[] expected, Object[] actual) {
        message = (message == null) ? "" : message;

        assertEquals(message + " length", expected.length, actual.length);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(message + " index is " + i, expected[i], actual[i]);
        }
    }

    public void assertEquals(Object[] expected, Object[] actual) {
        assertEquals(null, expected, actual);
    }
}