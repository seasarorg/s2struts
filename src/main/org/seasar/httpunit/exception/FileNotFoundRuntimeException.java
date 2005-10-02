package org.seasar.httpunit.exception;

/**
 * @author Satoshi Kimura
 */
public class FileNotFoundRuntimeException extends RuntimeException {
    /**
     *
     */
    public FileNotFoundRuntimeException() {
        super();
    }

    /**
     * @param message
     */
    public FileNotFoundRuntimeException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public FileNotFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public FileNotFoundRuntimeException(Throwable cause) {
        super(cause);
    }
}