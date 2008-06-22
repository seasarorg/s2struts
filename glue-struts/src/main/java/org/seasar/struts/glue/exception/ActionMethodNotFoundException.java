package org.seasar.struts.glue.exception;

public class ActionMethodNotFoundException extends GlueRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String className;

    protected String methodName;

    public ActionMethodNotFoundException(final String className,
            final String methodName) {
        super("Action method({0}) of class({1}) not found.", methodName,
                className);
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

}
