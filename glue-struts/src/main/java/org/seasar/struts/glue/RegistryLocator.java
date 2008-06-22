package org.seasar.struts.glue;

public class RegistryLocator {

    protected static Registry instance;

    private RegistryLocator() {
    }

    public static Registry getInstance() {
        return instance;
    }

    public static void setInstance(final Registry instance) {
        RegistryLocator.instance = instance;
    }

}
