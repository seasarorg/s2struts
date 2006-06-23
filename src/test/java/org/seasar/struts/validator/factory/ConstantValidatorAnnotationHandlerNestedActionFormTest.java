package org.seasar.struts.validator.factory;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.form.ValidatorAnnotationActionForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ConstantValidatorAnnotationHandlerNestedActionFormTest extends S2TestCase {

    private ValidatorAnnotationHandler annHandler;

    private Form form;

    public void setUp() {
        include("s2struts.dicon");

        annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void setUpAfterContainerInit() {
        form = annHandler.createForm("testForm", ValidatorAnnotationActionForm.class);
    }

    public void testNestedActionForm() {
        Field field = form.getField("nestedFormList[].value");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("Value", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }
    
}
