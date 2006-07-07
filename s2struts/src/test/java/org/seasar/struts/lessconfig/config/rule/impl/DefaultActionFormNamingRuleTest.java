package org.seasar.struts.lessconfig.config.rule.impl;

import org.seasar.extension.unit.S2TestCase;

public class DefaultActionFormNamingRuleTest extends S2TestCase {

    private DefaultActionFormNamingRule namingRule;

    public void setUp() throws Exception {
        super.setUp();
        include("DefaultActionFormNamingRuleTest.dicon");
    }

    public void testToComponentClassFormComponentName() {
        assertTrue(this.getContainer().hasComponentDef("formComponentForm"));
        Class clazz = this.namingRule.toComponentClass("formComponentForm");
        assertEquals(FormComponentForm.class, clazz);
    }

    public void testToComponentClassAutoConverteFormComponentName() {
        assertFalse(this.getContainer().hasComponentDef("formComponentDto"));
        Class clazz = this.namingRule.toComponentClass("formComponentDto");
        assertEquals(FormComponentForm.class, clazz);
    }

    public void testToComponentClassDtoComponentName() {
        assertTrue(this.getContainer().hasComponentDef("dtoComponentDto"));
        Class clazz = this.namingRule.toComponentClass("dtoComponentDto");
        assertEquals(DtoComponentDto.class, clazz);
    }

    public void testToComponentClassAutoConverteDtoComponentName() {
        assertFalse(this.getContainer().hasComponentDef("dtoComponentForm"));
        Class clazz = this.namingRule.toComponentClass("dtoComponentForm");
        assertEquals(DtoComponentDto.class, clazz);
    }

    public void testToComponentClassNoRegisteredComponentName() {
        assertFalse(this.getContainer().hasComponentDef("noRegisteredComponentDto"));
        Class clazz = this.namingRule.toComponentClass("noRegisteredComponentDto");
        assertNull(clazz);
    }

    public void testToActionFormNameComponentName() {
        assertTrue(this.getContainer().hasComponentDef("formComponentForm"));
        String name = this.namingRule.toActionFormName(FormComponentForm.class);
        assertEquals("formComponentForm", name);
    }

    public void testToActionFormNameNoRegisteredComponentName() {
        assertFalse(this.getContainer().hasComponentDef("noRegisteredComponentDto"));
        String name = this.namingRule.toActionFormName(NoRegisteredComponentDto.class);
        assertEquals("defaultActionFormNamingRuleTest$NoRegisteredComponentDto", name);
    }

    //
    //
    //

    public static class FormComponentForm {
    }

    public static class DtoComponentDto {
    }

    public static class NoRegisteredComponentDto {
    }

}
