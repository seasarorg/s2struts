package org.seasar.struts.processor;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.struts.util.S2StrutsContextUtil;

public class PathResolverImplTest extends S2TestCase {

    private PathResolver resolver;

    protected void setUp() throws Exception {
        super.setUp();
        include(getClass().getName().replace('.', '/') + ".dicon");
    }

    public void testResolve() {
        MockHttpServletRequest request = getRequest();
        request.setParameter("key", "value");
        S2StrutsContextUtil.setMethodBindingExpression("/path", "key", "value",
                "#{hoge.execute}");

        String path = resolver.resolve(request, "/path");
        assertEquals("/actualPath", path);
    }

    public void testResolve_noAnnotation() {
        MockHttpServletRequest request = getRequest();
        request.setParameter("key", "value");
        S2StrutsContextUtil.setMethodBindingExpression("/path", "key", "value",
                "#{hoge.execute2}");

        String path = resolver.resolve(request, "/path");
        assertEquals("/hoge", path);
    }

    public void testResolve_noExpression() {
        MockHttpServletRequest request = getRequest();
        String path = resolver.resolve(request, "/path");
        assertEquals("/path", path);
    }

    public static class Hoge {
        public static String execute_BINDING_METHOD = "path=/actualPath";

        public String execute() {
            return null;
        }

        public String execute2() {
            return null;
        }
    }

}
