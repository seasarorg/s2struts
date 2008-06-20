package org.seasar.struts.glue.exception;

public class IllegalActionFormTypeException extends GlueException {

    protected String className;

    protected String fieldName;

    public IllegalActionFormTypeException(String className, String fieldName) {
        super(
                "field({0}) type of class({1}) must extend org.apache.struts.action.ActionForm.",
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
