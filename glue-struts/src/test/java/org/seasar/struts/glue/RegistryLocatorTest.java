package org.seasar.struts.glue;

import junit.framework.TestCase;

import org.seasar.struts.glue.Registry;
import org.seasar.struts.glue.RegistryLocator;

public class RegistryLocatorTest extends TestCase {

    public void test() throws Exception {
        Registry registry = RegistryLocator.getInstance();
        assertNotNull(registry);
    }
}
