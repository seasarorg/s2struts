package org.seasar.struts.hotdeploy;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.hotdeploy.web.add.AddAction;
import org.seasar.struts.hotdeploy.web.add.AddForm;

public class ActionFormNamingRuleTest extends S2TestCase {

    private NamingRule namingRule;

    public void setUp() {
        include("ActionFormNamingRuleTest.dicon");
    }
    
    public void testIsTarget() {
        assertTrue(namingRule.isTargetClass(AddForm.class));
    }
    
    public void testIsNotTarget() {
        assertFalse(namingRule.isTargetClass(AddAction.class));
    }
    
    public void testDefineName() {
        String name = namingRule.defineName(AddForm.class);
        assertEquals("add_addForm", name);
    }
    
    public void testDefineClass() {
        Class clazz = namingRule.defineClass("add_addForm");
        assertEquals(AddForm.class, clazz);
    }
    
}
