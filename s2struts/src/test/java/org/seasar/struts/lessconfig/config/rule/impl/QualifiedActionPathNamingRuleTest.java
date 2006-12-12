package org.seasar.struts.lessconfig.config.rule.impl;

import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;
import org.seasar.struts.lessconfig.config.rule.impl.action.ModulePathComponentAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.NoModulePathComponentAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.NoRegisteredComponentAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.PackageRuleComponentAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.PathComponentAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.PojoComponentAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.PojoComponentOneAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.PojoComponentTwoAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.RuleComponentAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.UndefinedComponentAction;

public class QualifiedActionPathNamingRuleTest extends S2TestCase {

    private ActionPathNamingRule namingRule;

    public void setUp() throws Exception {
        super.setUp();
        include("QualifiedActionPathNamingRuleTest.dicon");
    }

    public void testToComponentClassPathComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("");

        Class clazz = this.namingRule.toComponentClass(config, "/path");
        assertEquals(PathComponentAction.class, clazz);
    }

    public void testToComponentClassModulePathComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("/module");

        Class clazz = this.namingRule.toComponentClass(config, "/modulePath");
        assertEquals(ModulePathComponentAction.class, clazz);
    }

    public void testToComponentClassNoModulePathComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("/module");

        Class clazz = this.namingRule.toComponentClass(config, "/noModulePath");
        assertEquals(NoModulePathComponentAction.class, clazz);
    }

    public void testToComponentClassRuleComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("");

        Class clazz = this.namingRule.toComponentClass(config, "/ruleComponent");
        assertEquals(RuleComponentAction.class, clazz);
    }

    public void testToComponentClassPackageComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("");

        Class clazz = this.namingRule.toComponentClass(config, "/package/ruleComponent");
        assertEquals(PackageRuleComponentAction.class, clazz);
    }

    public void testToComponentClassPojoComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("");

        Class clazz = this.namingRule.toComponentClass(config, "/pojoComponent");
        assertEquals(PojoComponentAction.class, clazz);
    }

    public void testToComponentClassNoRegisteredComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("");

        assertFalse(this.getContainer().hasComponentDef("/noRegisterdComponent"));
        Class clazz = this.namingRule.toComponentClass(config, "/noRegisterdComponent");
        assertNull(clazz);
    }

    public void testToComponentClassMultiImplements() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("");

        Class clazz = this.namingRule.toComponentClass(config, "/pojoComponentOne");
        assertEquals(PojoComponentOneAction.class, clazz);
        clazz = this.namingRule.toComponentClass(config, "/pojoComponentTwo");
        assertEquals(PojoComponentTwoAction.class, clazz);
    }

    //
    //
    //

    public void testToActionPathNamePathComponentName() {
        String path = this.namingRule.toActionPathName(PathComponentAction.class);
        assertEquals("/path", path);
    }

    public void testToActionPathNameRuleComponentName() {
        String path = this.namingRule.toActionPathName(RuleComponentAction.class);
        assertEquals("/ruleComponent", path);
    }

    public void testToActionPathNamePackageComponentName() {
        String path = this.namingRule.toActionPathName(PackageRuleComponentAction.class);
        assertEquals("/package/ruleComponent", path);
    }

    public void testToActionPathNamePojoComponentName() {
        String path = this.namingRule.toActionPathName(PojoComponentAction.class);
        assertEquals("/pojoComponent", path);
    }

    public void testToActionPathNameUndefinedComponentName() {
        assertTrue(this.getContainer().hasComponentDef(UndefinedComponentAction.class));
        String path = this.namingRule.toActionPathName(UndefinedComponentAction.class);
        assertNull(path);
    }

    public void testToActionPathNameNoRegisterdComponentName() {
        assertFalse(this.getContainer().hasComponentDef(NoRegisteredComponentAction.class));
        String path = this.namingRule.toActionPathName(NoRegisteredComponentAction.class);
        assertNull(path);
    }

}
