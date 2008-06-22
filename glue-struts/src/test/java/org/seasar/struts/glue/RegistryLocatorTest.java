package org.seasar.struts.glue;

import junit.framework.TestCase;

import org.seasar.struts.glue.registry.AbstractRegistry;

public class RegistryLocatorTest extends TestCase {

    public void test() throws Exception {
        RegistryLocator.setInstance(new AbstractRegistry() {

            public <T> T getComponent(final String componentName) {
                return null;
            }

            public boolean hasComponent(final String componentName) {
                return false;
            }

        });
        final Registry registry = RegistryLocator.getInstance();
        assertNotNull(registry);
    }
}
