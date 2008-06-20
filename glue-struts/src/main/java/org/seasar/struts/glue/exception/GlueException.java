package org.seasar.struts.glue.exception;

import java.text.MessageFormat;

public class GlueException extends RuntimeException {

    protected Object[] args;

    protected String message;

    public GlueException(String pattern) {
        this(null, pattern, null);
    }

    public GlueException(String pattern, Object... args) {
        this(null, pattern, args);
    }

    public GlueException(Throwable cause, String pattern, Object... args) {
        super(cause);
        this.args = args;
        try {
            message = MessageFormat.format(pattern, args);
        } catch (Throwable ignore) {
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

}
