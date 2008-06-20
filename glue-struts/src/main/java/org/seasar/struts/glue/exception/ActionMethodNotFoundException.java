package org.seasar.struts.glue.exception;

public class ActionMethodNotFoundException extends GlueException {

    protected String className;

    protected String methodName;

    public ActionMethodNotFoundException(Exception cause, String className,
            String methodName) {
        super(cause, "Action method({0}) of class({1}) not found.", methodName,
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
