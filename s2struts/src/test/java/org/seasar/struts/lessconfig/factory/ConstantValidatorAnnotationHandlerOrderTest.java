package org.seasar.struts.lessconfig.factory;

import java.util.List;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ConstantValidatorAnnotationHandlerOrderTest extends S2TestCase {

    private ValidatorAnnotationHandler annHandler;

    private Form form;

    public void setUp() {
        include("s2struts.dicon");

        annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void setUpAfterContainerInit() {
        form = annHandler.createForm("testForm", TestValidatorAnnotationOrderForm.class);
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
