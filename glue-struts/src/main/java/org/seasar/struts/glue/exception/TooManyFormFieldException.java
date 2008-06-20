package org.seasar.struts.glue.exception;

public class TooManyFormFieldException extends GlueException {

    protected String className;

    public TooManyFormFieldException(String className) {
        super("Class({0}) has multiple @Form annotated fields.", className);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

}
