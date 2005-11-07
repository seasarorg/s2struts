package org.seasar.httpunit.exception;

/**
 * サーブレットをクラス名で取得しようとした場合に、 web.xmlに同じクラスのサーブレットが2つ以上登録されている場合に発生します。
 * 
 * @author Satoshi Kimura
 */
public class TooManyRegistrationRuntimeException extends RuntimeException {
    /**
     *
     */
    public TooManyRegistrationRuntimeException() {
        super();
    }

    /**
     * @param message
     */
    public TooManyRegistrationRuntimeException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public TooManyRegistrationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public TooManyRegistrationRuntimeException(Throwable cause) {
        super(cause);
    }
}