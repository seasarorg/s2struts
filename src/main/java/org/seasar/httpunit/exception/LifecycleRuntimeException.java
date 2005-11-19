package org.seasar.httpunit.exception;

/**
 * @author Satoshi Kimura
 */
public class LifecycleRuntimeException extends RuntimeException {
    /**
     *
     */
    public LifecycleRuntimeException() {
        super();
    }

    /**
     * @param message
     */
    public LifecycleRuntimeException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public LifecycleRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public LifecycleRuntimeException(Throwable cause) {
        super(cause);
    }
}