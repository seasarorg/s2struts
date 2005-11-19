package org.seasar.httpunit.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.loader.WebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.ClassLoaderFactory;
import org.apache.catalina.startup.ContextConfig;
import org.apache.log4j.Category;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.naming.resources.BaseDirContext;
import org.apache.naming.resources.FileDirContext;
import org.apache.naming.resources.WARDirContext;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.TooManyRegistrationRuntimeException;
import org.seasar.framework.container.impl.S2ContainerImpl;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.httpunit.exception.ServletRuntimeException;

/**
 * @author Satoshi Kimura
 */
public class StandardContext extends org.apache.catalina.core.StandardContext {
    private static final String TMP_DIR = "java.io.tmpdir";

    private static final String DEFAULT_WEB_XML = "org/seasar/httpunit/core/web.xml";

    private static final String HOST_NAME = "localhsot";

    private static final String CONTEXT_NAME = "\u002f\u0053\u0032\u0041\u0079\u0061\u0079\u0061";

    private final File logFile = new File(getWorkDir(), CONTEXT_NAME + ".log");

    {
        String loggerName = "tomcat." + HOST_NAME + "." + CONTEXT_NAME;
        Category logger = Logger.getInstance(loggerName);
        logger.setLevel(Level.WARN);

        try {
            logger.addAppender(new FileAppender(new SimpleLayout(), logFile.getAbsolutePath()));
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public StandardContext() {
        super();
    }

    public ObjectName getParentName() throws MalformedObjectNameException {
        ObjectName parentName = new ObjectName("localhsot:type=Host,host=localhsot");

        return parentName;
    }

    public String getWorkDir() {
        return System.getProperty(TMP_DIR);
    }

    public boolean getPrivileged() {
        return true;
    }

    public void init() throws Exception {
        initSystemProperty();

        setCatalinaHome();

        setClassLoader();

        setName();

        addLifecycleListener();

        super.controller = super.oname;

        super.init();
    }

    private void initSystemProperty() {
        setSystemProperty("catalina.useNaming", "true");

        setDocumentBuilderImpl();
    }

    private void setDocumentBuilderImpl() {
        setSystemProperty("javax.xml.parsers.DocumentBuilderFactory",
                "org.apache.crimson.jaxp.DocumentBuilderFactoryImpl");
    }

    private static void setSystemProperty(String key, String value) {
        synchronized (System.class) {
            if (null == System.getProperty(key)) {
                System.setProperty(key, value);
            }
        }
    }

    private void setClassLoader() throws Exception {
        ClassLoader classLoader = createClassLoader();

        setparentClassLoader();

        setLoader(classLoader);
    }

    private ClassLoader createClassLoader() throws Exception {
        String docBase = getDocBase();
        File webInf = new File(new File(docBase), "WEB-INF");
        File[] unpacked = {new File(webInf, "classes")};
        File[] packed = {new File(webInf, "lib")};
        ClassLoader parentClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = ClassLoaderFactory.createClassLoader(unpacked, packed, parentClassLoader);

        return classLoader;
    }

    private void setLoader(ClassLoader classLoader) {
        WebappLoader webappLoader = new WebappLoader(classLoader);
        webappLoader.setLoaderClass(WebappClassLoader.class.getName());
        webappLoader.setContainer(this);
        webappLoader.setDelegate(true);

        // todo warファイルのテスト
        BaseDirContext context = null;

        if (new File(getDocBase()).isFile() && getDocBase().endsWith(".war")) {
            context = new WARDirContext();
        } else {
            context = new FileDirContext();
        }

        webappLoader.getContainer().setResources(context);
        setLoader(webappLoader);
    }

    private void setparentClassLoader() {
        parentClassLoader = Thread.currentThread().getContextClassLoader();
        setParentClassLoader(parentClassLoader);
    }

    private void addLifecycleListener() {
        ContextConfig contextConfig = new ContextConfig();
        contextConfig.setDefaultWebXml(DEFAULT_WEB_XML);
        super.addLifecycleListener(contextConfig);
    }

    /**
     *  
     */
    private void setCatalinaHome() {
        System.setProperty("catalina.home", "");
    }

    private void setName() {
        Engine engine = new StandardEngine();
        engine.setName("S2StrutsUnitEngine");

        Host host = new StandardHost();
        host.setName(HOST_NAME);
        host.setParent(engine);
        setParent(host);
        setName(CONTEXT_NAME);
    }

    public void loadOnStartup() throws ServletException, IOException {
        List list = getChildrenList();
        StandardWrapper[] children = (StandardWrapper[]) list.toArray(new StandardWrapper[list.size()]);
        super.loadOnStartup(children);
    }

    public Map getHttpServlets() {
        List children = getChildrenList();
        Map servlets = new HashMap();

        for (int i = 0; i < children.size(); i++) {
            try {
                StandardWrapper wrapper = (StandardWrapper) children.get(i);
                Servlet servlet = wrapper.allocate();
                servlets.put(wrapper.getName(), servlet);
                wrapper.deallocate(servlet);
            } catch (ServletException e) {
                e.printStackTrace();
                throw new ServletRuntimeException(e);
            }
        }

        return servlets;
    }

    public HttpServlet getHttpServlet(String servletName) {
        return (HttpServlet) getHttpServlets().get(servletName);
    }

    public HttpServlet getHttpServlet(Class servletClass) {
        S2Container container = new S2ContainerImpl();

        try {
            Collection servlets = getHttpServlets().values();

            for (Iterator iterator = servlets.iterator(); iterator.hasNext();) {
                container.register(iterator.next());
            }

            return (HttpServlet) container.getComponent(servletClass);
        } catch (TooManyRegistrationRuntimeException e) {
            throw new org.seasar.httpunit.exception.TooManyRegistrationRuntimeException(e);
        } catch (ComponentNotFoundRuntimeException e) {
            return null;
        } finally {
            container.destroy();
        }
    }

    private List getChildrenList() {
        List children = new ArrayList();
        children.addAll(super.children.values());

        return children;
    }
}