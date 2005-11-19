package org.seasar.httpunit.exception;

/**
 * @author Satoshi Kimura
 */
public class ServletRuntimeException extends RuntimeException {
    /**
     *
     */
    public ServletRuntimeException() {
        super();
    }

    /**
     * @param message
     */
    public ServletRuntimeException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ServletRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public ServletRuntimeException(Throwable cause) {
        super(cause);
    }
}