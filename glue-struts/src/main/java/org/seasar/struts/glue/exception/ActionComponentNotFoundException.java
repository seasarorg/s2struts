package org.seasar.struts.glue.exception;

public class ActionComponentNotFoundException extends GlueRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String componentName;

    public ActionComponentNotFoundException(final String componentName) {
        super("Action component({0}) not found.", componentName);
        this.componentName = componentName;
    }

    public String getComponentName() {
        return componentName;
    }

}
