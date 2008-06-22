package org.seasar.struts.glue.exception;

public class IllegalActionFormTypeException extends GlueRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String className;

    protected String fieldName;

    public IllegalActionFormTypeException(final String className,
            final String fieldName) {
        super(
                "field({0}) of class({1}) is not org.apache.struts.action.ActionForm.",
                fieldName, className);
        this.className = className;
        this.fieldName = fieldName;
    }

    public String getClassName() {
        return className;
    }

    public String getFieldName() {
        return fieldName;
    }

}
