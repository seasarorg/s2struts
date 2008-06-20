package org.seasar.struts.glue.registry;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.glue.ActionFactory;
import org.seasar.struts.glue.NameExtracter;

public class Seasar2RegistryTest extends S2TestCase {

    @Override
    protected void setUp() {
        include("glue-struts.dicon");
    }

    public void testHasComponent() throws Exception {
        Seasar2Registry registry = new Seasar2Registry();
        assertTrue(registry.hasComponent("actionFactory"));
        assertTrue(registry.hasComponent("nameExtracter"));
    }

    public void testGetComponent() throws Exception {
        Seasar2Registry registry = new Seasar2Registry();
        ActionFactory actionFactory = registry.getComponent("actionFactory");
        assertNotNull(actionFactory);
        NameExtracter nameExtracter = registry.getComponent("nameExtracter");
        assertNotNull(nameExtracter);
    }
}
