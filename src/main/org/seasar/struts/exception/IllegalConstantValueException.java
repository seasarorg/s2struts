package org.seasar.struts.exception;

/**
 * @author Katsuhiko Nagashima
 */
public class IllegalConstantValueException extends RuntimeException {

    public IllegalConstantValueException() {
        super();
    }

    public IllegalConstantValueException(String message) {
        super(message);
    }

    public IllegalConstantValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalConstantValueException(Throwable cause) {
        super(cause);
    }

}
