package org.seasar.struts.glue.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.glue.ActionFactory;
import org.seasar.struts.glue.MethodNameExtracter;
import org.seasar.struts.glue.RegistryLocator;
import org.seasar.struts.glue.registry.AbstractRegistry;

public class GlueImplTest extends S2TestCase {

    private GlueImpl glue = new GlueImpl();

    public void testIsTarget() throws Exception {
        RegistryLocator.setInstance(new AbstractRegistry() {

            public <T> T getComponent(final String componentName) {
                return null;
            }

            public boolean hasComponent(final String componentName) {
                return "hoge".equals(componentName);
            }

        });
        final ActionMapping mapping = new ActionMapping();
        mapping.setPath("hoge");
        assertTrue(glue.isTarget(mapping));
    }

    public void testGetAction() throws Exception {
        final ActionServlet servlet = new ActionServlet();
        final ActionMapping mapping = new ActionMapping();
        mapping.setPath("hoge");
        final String componentName = "hoge";
        final String methodName = "foo";
        final Object component = new Object();
        final Action action = new Action();

        RegistryLocator.setInstance(new AbstractRegistry() {
            public <T> T getComponent(final String componentName) {
                return (T) component;
            }

            public boolean hasComponent(final String _componentName) {
                return componentName.equals(_componentName);
            }

        });

        final ActionFactory actionFactory = new ActionFactory() {
            public Action getAction(ActionServlet _servlet, Object _component,
                    String _methodName) {
                if (servlet == _servlet && component == _component
                        && methodName == _methodName) {
                    return action;
                }
                return null;
            }

        };

        final MethodNameExtracter methodNameExtracter = new MethodNameExtracter() {
            public String extract(HttpServletRequest request) {
                return methodName;
            }
        };

        glue.setActionFactory(actionFactory);
        glue.setMethodNameExtracter(methodNameExtracter);
        assertSame(action, glue.getAction(servlet, getRequest(), getResponse(),
                mapping));
    }

    public void testGetAction2() throws Exception {
        final ActionServlet servlet = new ActionServlet();
        final String componentName = "hoge";
        final String methodName = "foo";
        final Object component = new Object();
        final Action action = new Action();

        RegistryLocator.setInstance(new AbstractRegistry() {

            public <T> T getComponent(final String componentName) {
                return (T) component;
            }

            public boolean hasComponent(final String _componentName) {
                return componentName.equals(_componentName);
            }

        });

        final ActionFactory actionFactory = new ActionFactory() {

            public Action getAction(ActionServlet _servlet, Object _component,
                    String _methodName) {
                if (servlet == _servlet && component == _component
                        && methodName == _methodName) {
                    return action;
                }
                return null;
            }

        };

        glue.setActionFactory(actionFactory);
        assertSame(action, glue.getAction(servlet, componentName, methodName));
    }
}
