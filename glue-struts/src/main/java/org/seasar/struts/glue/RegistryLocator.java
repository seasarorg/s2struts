package org.seasar.struts.glue;

import org.seasar.struts.glue.registry.Seasar2Registry;

public class RegistryLocator {

    protected static Registry instance = new Seasar2Registry();

    private RegistryLocator() {
    }

    public static Registry getInstance() {
        return instance;
    }

    public static void setInstance(Registry instance) {
        RegistryLocator.instance = instance;
    }

}
