package org.seasar.struts.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

/**
 * @author Satoshi Kimura
 */
public class FieldChecks extends org.apache.struts.validator.FieldChecks {

    /**
     * Checks if the field's length of byte is less than or equal to the maximum value. A <code>Null</code> will be
     * considered an error.
     * 
     * @param bean The bean validation is being performed on.
     * @param validatorAction The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the current field being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if any validation errors occur.
     * @param validator The <code>Validator</code> instance, used to access other field values.
     * @param request Current request object.
     * @return True if stated conditions met.
     */
    public static boolean validateMaxByteLength(Object bean, ValidatorAction validatorAction, Field field,
            ActionMessages errors, Validator validator, HttpServletRequest request) {

        String value = toString(bean, field);

        if (!GenericValidator.isBlankOrNull(value)) {
            try {
                int max = parseInt(field.getVarValue("maxbytelength"));
                String charset = field.getVarValue("charset");

                if (!GenericValidator.maxByteLength(value, max, charset)) {
                    addError(errors, field, validator, validatorAction, request);
                    return false;
                }
            } catch (Exception e) {
                addError(errors, field, validator, validatorAction, request);
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the field's length of byte is greater than or equal to the minimum value. A <code>Null</code> will be
     * considered an error.
     * 
     * @param bean The bean validation is being performed on.
     * @param validatorAction The <code>ValidatorAction</code> that is currently being performed.
     * @param field The <code>Field</code> object associated with the current field being validated.
     * @param errors The <code>ActionMessages</code> object to add errors to if any validation errors occur.
     * @param validator The <code>Validator</code> instance, used to access other field values.
     * @param request Current request object.
     * @return True if stated conditions met.
     */
    public static boolean validateMinByteLength(Object bean, ValidatorAction validatorAction, Field field,
            ActionMessages errors, Validator validator, HttpServletRequest request) {

        String value = toString(bean, field);

        if (!GenericValidator.isBlankOrNull(value)) {
            try {
                int min = parseInt(field.getVarValue("minbytelength"));
                String charset = field.getVarValue("charset");

                if (!GenericValidator.minByteLength(value, min, charset)) {
                    addError(errors, field, validator, validatorAction, request);
                    return false;
                }
            } catch (Exception e) {
                addError(errors, field, validator, validatorAction, request);
                return false;
            }
        }

        return true;
    }

    private static String toString(Object bean, Field field) {
        String value = null;
        if (isString(bean)) {
            value = (String) bean;
        } else {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        }
        return value;
    }

    private static int parseInt(String value) {
        return java.lang.Integer.parseInt(value);
    }

    private static void addError(ActionMessages errors, Field field, Validator validator,
            ValidatorAction validatorAction, HttpServletRequest request) {
        errors.add(field.getKey(), Resources.getActionMessage(validator, request, validatorAction, field));
    }
}
