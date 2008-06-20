package org.seasar.struts.glue.registry;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.glue.Registry;

public class Seasar2Registry implements Registry {

    public boolean hasComponent(String componentName) {
        return getContainer().hasComponentDef(componentName);
    }

    public <T> T getComponent(String componentName) {
        return (T) getContainer().getComponent(componentName);
    }

    protected S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

}
