package org.seasar.struts.glue.exception;

public class NoMethodNameParameterException extends GlueException {

    public NoMethodNameParameterException() {
        super("Method name parameter not existent.");
    }
}
