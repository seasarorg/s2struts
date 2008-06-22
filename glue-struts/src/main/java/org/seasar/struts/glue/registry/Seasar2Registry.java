package org.seasar.struts.glue.registry;

import org.seasar.framework.container.S2Container;

public class Seasar2Registry extends AbstractRegistry {

    protected S2Container container;

    public Seasar2Registry() {
    }

    public boolean hasComponent(final String componentName) {
        return getContainer().hasComponentDef(componentName);
    }

    public <T> T getComponent(final String componentName) {
        return (T) getContainer().getComponent(componentName);
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(final S2Container container) {
        this.container = container;
    }

}
