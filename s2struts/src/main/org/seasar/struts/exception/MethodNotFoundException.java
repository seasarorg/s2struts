package org.seasar.struts.exception;

/**
 * @author Satoshi Kimura
 */
public class MethodNotFoundException extends RuntimeException {

    public MethodNotFoundException() {
        super();
    }

    public MethodNotFoundException(String message) {
        super(message);
    }

    public MethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodNotFoundException(Throwable cause) {
        super(cause);
    }
}
