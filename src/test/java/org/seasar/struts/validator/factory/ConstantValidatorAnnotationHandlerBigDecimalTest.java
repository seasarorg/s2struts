package org.seasar.struts.validator.factory;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.form.ValidatorAnnotationBigDecimalForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ConstantValidatorAnnotationHandlerBigDecimalTest extends
        S2TestCase {

    private ValidatorAnnotationHandler annHandler;

    private Form form;

    public void setUp() {
        include("s2struts.dicon");

        annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void setUpAfterContainerInit() {
        form = annHandler.createForm("testForm",
                ValidatorAnnotationBigDecimalForm.class);
    }

    public void testFieldCount() {
        assertEquals(1, form.getFields().size());
    }

    public void testAutoSqlDate() {
        Field field = form.getField("bigDecimal");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
    }

}
