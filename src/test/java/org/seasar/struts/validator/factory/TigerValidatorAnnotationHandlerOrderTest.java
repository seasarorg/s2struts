package org.seasar.struts.validator.factory;

import java.util.List;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.form.ValidatorAnnotationOrderForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TigerValidatorAnnotationHandlerOrderTest extends S2TestCase {

    private ValidatorAnnotationHandler annHandler;

    private Form form;

    public void setUp() {
        include("s2struts.dicon");

        annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void setUpAfterContainerInit() {
        form = annHandler.createForm("testForm", ValidatorAnnotationOrderForm.class);
    }

    public void testOrder() {
        List fields = form.getFields();
        assertNotNull(fields);
        assertEquals(3, fields.size());

        assertEquals("classType", ((Field) fields.get(0)).getKey());
        assertEquals("className", ((Field) fields.get(1)).getKey());
        assertEquals("arg", ((Field) fields.get(2)).getKey());
    }

}
