package org.seasar.struts.validator.factory;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.form.TestForm;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class ConstantValidatorAnnotationHandlerTest extends S2TestCase {
    
    private ValidatorAnnotationHandler annHandler;
    
    private Form form;
    
    public void setUp() {
        include("s2struts.dicon");
        
        annHandler = new ConstantValidatorAnnotationHandler();
    }
    
    public void setUpAfterContainerInit() {
        form = annHandler.createForm("testForm", TestForm.class);
    }

    public void testArg() {
        Field field = form.getField("arg");
        assertNotNull(field);
        assertEquals("Arg", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }
    
    public void testRequired() {
        Field field = form.getField("required");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
    }
    
    public void testInteger() {
        Field field = form.getField("integer");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
    }
    
    public void testDate() {
        Field field = form.getField("date");
        assertNotNull(field);
        assertEquals("date", field.getDepends());
        assertEquals("yyyyMMdd", field.getVarValue("datePattern"));
    }
    
    public void testAutoInteger() {
        Field field = form.getField("autoInteger");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
    }
    
    public void testAutoDate() {
        Field field = form.getField("autoDate");
        assertNotNull(field);
        assertEquals("date", field.getDepends());
        assertEquals("yyyy/MM/dd", field.getVarValue("datePattern"));
    }
    
    public void testNoValidateDate() {
        Field field = form.getField("noValidateDate");
        assertNull(field);
    }
    
    public void testCreditCard() {
        Field field = form.getField("creditCard");
        assertNotNull(field);
        assertEquals("creditCard", field.getDepends());
    }
    
    public void testLength() {
        Field field = form.getField("length");
        assertNotNull(field);
        assertEquals("minlength,maxlength = " + field.getDepends(),
                "minlength,maxlength".length(), field.getDepends().length());
        assertEquals("3", field.getVarValue("minlength"));
        assertEquals("5", field.getVarValue("maxlength"));
    }
    
    public void testByteLength() {
        Field field = form.getField("byteLength");
        assertNotNull(field);
        assertEquals("minbytelength,maxbytelength = " + field.getDepends(),
                "minbytelength,maxbytelength".length(), field.getDepends().length());
        assertEquals("3", field.getVarValue("minbytelength"));
        assertEquals("5", field.getVarValue("maxbytelength"));
        assertEquals("ISO8859_1", field.getVarValue("charset"));
    }
    
    public void testRange() {
        Field field = form.getField("range");
        assertNotNull(field);
        assertEquals("floatRange", field.getDepends());
        assertEquals("5.0", field.getVarValue("min"));
        assertEquals("10.1", field.getVarValue("max"));
    }
    
    public void testMix() {
        Field field = form.getField("mix");
        assertNotNull(field);
        assertEquals("required,minlength,maxlength,mask,email = " + field.getDepends(),
                "required,minlength,maxlength,mask,email".length(), field.getDepends().length());
        assertEquals("10", field.getVarValue("minlength"));
        assertEquals("15", field.getVarValue("maxlength"));
        assertEquals("com$", field.getVarValue("mask"));
        
        assertEquals("mustendcom", field.getMsg("mask"));
    }
    
}
