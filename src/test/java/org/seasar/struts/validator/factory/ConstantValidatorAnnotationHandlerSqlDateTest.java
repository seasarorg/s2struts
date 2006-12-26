package org.seasar.struts.validator.factory;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.form.ValidatorAnnotationSqlDateForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ConstantValidatorAnnotationHandlerSqlDateTest extends S2TestCase {

    private ValidatorAnnotationHandler annHandler;

    private Form form;

    public void setUp() {
        include("s2struts.dicon");

        annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void setUpAfterContainerInit() {
        form = annHandler.createForm("testForm", ValidatorAnnotationSqlDateForm.class);
    }

    public void testFieldCount() {
        assertEquals(1, form.getFields().size());
    }

    public void testAutoSqlDate() {
        Field field = form.getField("autoSqlDate");
        assertNotNull(field);
        assertEquals("date", field.getDepends());
        assertEquals("yyyy/MM/dd", field.getVarValue("datePattern"));
    }

}
