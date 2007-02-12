package org.seasar.struts.validator.exception;

/**
 * @author Satoshi Kimura
 */
public class ValidatorException extends Exception {

    private static final long serialVersionUID = -789621071460206431L;

    private String resourceKey;

    private Object[] messageArgs;

    public ValidatorException(String resourceKey) {
        this(resourceKey, new Object[0]);
    }

    public ValidatorException(String resourceKey, Object messageArg) {
        this(resourceKey, new Object[] { messageArg });
    }

    public ValidatorException(String resourceKey, Object[] messageArgs) {
        this.resourceKey = resourceKey;
        this.messageArgs = (Object[]) messageArgs.clone();
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public Object[] getMessageArgs() {
        return (Object[]) messageArgs.clone();
    }
}
