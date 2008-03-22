package org.seasar.struts.validator.exception;

/**
 * 検証に失敗した場合にスローされる検査例外です。
 * 
 * @author Satoshi Kimura
 */
public class ValidatorException extends Exception {

    private static final long serialVersionUID = -789621071460206431L;

    private String resourceKey;

    private Object[] messageArgs;

    /**
     * インスタンスを構築します。
     * 
     * @param resourceKey
     */
    public ValidatorException(String resourceKey) {
        this(resourceKey, new Object[0]);
    }

    /**
     * インスタンスを構築します。
     * 
     * @param resourceKey
     * @param messageArg
     */
    public ValidatorException(String resourceKey, Object messageArg) {
        this(resourceKey, new Object[] { messageArg });
    }

    /**
     * インスタンスを構築します。
     * 
     * @param resourceKey
     * @param messageArgs
     */
    public ValidatorException(String resourceKey, Object[] messageArgs) {
        this.resourceKey = resourceKey;
        this.messageArgs = (Object[]) messageArgs.clone();
    }

    /**
     * リソースキーを返します。
     * 
     * @return
     */
    public String getResourceKey() {
        return resourceKey;
    }

    /**
     * メッセージの引数を返します。
     * 
     * @return
     */
    public Object[] getMessageArgs() {
        return (Object[]) messageArgs.clone();
    }
}
