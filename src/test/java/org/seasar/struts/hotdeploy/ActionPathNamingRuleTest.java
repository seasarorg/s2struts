package org.seasar.struts.hotdeploy;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.hotdeploy.web.add.AddAction;
import org.seasar.struts.hotdeploy.web.add.AddForm;

public class ActionPathNamingRuleTest extends S2TestCase {
    
    private NamingRule namingRule;

    public void setUp() {
        include("ActionPathNamingRuleTest.dicon");
    }
    
    public void testIsTarget() {
        assertTrue(namingRule.isTargetClass(AddAction.class));
    }
    
    public void testIsNotTarget() {
        assertFalse(namingRule.isTargetClass(AddForm.class));
    }
    
    public void testDefineName() {
        String name = namingRule.defineName(AddAction.class);
        assertEquals("/add_add", name);
    }
    
    public void testDefineClass() {
        Class clazz = namingRule.defineClass("/add_add");
        assertEquals(AddAction.class, clazz);
    }
    
}
