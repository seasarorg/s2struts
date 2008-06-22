package org.seasar.struts.glue.exception;

import java.util.Collections;
import java.util.List;

public class TooManyMethodNameParameterException extends GlueRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected List<String> methodNameParameters;

    public TooManyMethodNameParameterException(
            final List<String> methodNameParameters) {
        super("Multiple method name({0}) found.", getText(methodNameParameters));
        this.methodNameParameters = methodNameParameters;
    }

    protected static String getText(final List<String> methodNames) {
        final StringBuilder buf = new StringBuilder();
        for (final String name : methodNames) {
            buf.append(name);
            buf.append(", ");
        }
        buf.setLength(buf.length() - 2);
        return buf.toString();
    }

    public List<String> getMethodNameParameters() {
        return Collections.unmodifiableList(methodNameParameters);
    }

}
