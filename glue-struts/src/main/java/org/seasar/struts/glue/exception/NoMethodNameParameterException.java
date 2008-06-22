package org.seasar.struts.glue.exception;

public class NoMethodNameParameterException extends GlueRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NoMethodNameParameterException() {
        super("Method name parameter not existent.");
    }
}
