package org.seasar.struts.glue.exception;

public class IllegalActionMethodSignatureException extends GlueException {

    protected String className;

    protected String methodName;

    public IllegalActionMethodSignatureException(String className,
            String methodName) {
        super("Action method({0}) of class({1}) must be public.", methodName,
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
