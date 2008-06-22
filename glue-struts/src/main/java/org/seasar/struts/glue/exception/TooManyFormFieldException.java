package org.seasar.struts.glue.exception;

public class TooManyFormFieldException extends GlueRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String className;

    public TooManyFormFieldException(final String className) {
        super("Class({0}) has multiple @Form annotated fields.", className);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

}
