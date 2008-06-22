package org.seasar.struts.glue.exception;

import java.text.MessageFormat;

public class GlueRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected Object[] args;

    protected String message;

    public GlueRuntimeException(final String pattern) {
        this(null, pattern, null);
    }

    public GlueRuntimeException(final String pattern, final Object... args) {
        this(null, pattern, args);
    }

    public GlueRuntimeException(final Throwable cause, final String pattern,
            final Object... args) {
        super(cause);
        this.args = args;
        try {
            message = MessageFormat.format(pattern, args);
        } catch (final Throwable ignore) {
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object[] getArgs() {
        return args;
    }

}
