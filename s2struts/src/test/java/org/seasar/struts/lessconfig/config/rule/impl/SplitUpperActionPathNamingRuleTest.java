package org.seasar.struts.lessconfig.config.rule.impl;

import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;
import org.seasar.struts.lessconfig.config.rule.impl.action.PackageRuleComponentAction;
import org.seasar.struts.lessconfig.config.rule.impl.action.RuleComponentAction;

public class SplitUpperActionPathNamingRuleTest extends S2TestCase {

    private ActionPathNamingRule namingRule;

    public void setUp() throws Exception {
        super.setUp();
        include("SplitUpperActionPathNamingRuleTest.dicon");
    }

    public void testToComponentClassRuleComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("");

        Class clazz = this.namingRule.toComponentClass(config, "/rule/component");
        assertEquals(RuleComponentAction.class, clazz);
    }

    public void testToComponentClassPackageComponentName() {
        ModuleConfig config = new ModuleConfigImpl();
        config.setPrefix("");

        Class clazz = this.namingRule.toComponentClass(config, "/package/rule/component");
        assertEquals(PackageRuleComponentAction.class, clazz);
    }

    //
    //
    //

    public void testToActionPathNameRuleComponentName() {
        String path = this.namingRule.toActionPathName(RuleComponentAction.class);
        assertEquals("/rule/component", path);
    }

    public void testToActionPathNamePackageComponentName() {
        String path = this.namingRule.toActionPathName(PackageRuleComponentAction.class);
        assertEquals("/package/rule/component", path);
    }

    //
    //
    //

    public void testToQualifiedComponentClass() {
        assertEquals("", SplitUpperActionPathNamingRule.toQualifiedComponentClass(""));
        assertEquals("/", SplitUpperActionPathNamingRule.toQualifiedComponentClass("/"));
        assertEquals("/a", SplitUpperActionPathNamingRule.toQualifiedComponentClass("/a"));
        assertEquals("/a", SplitUpperActionPathNamingRule.toQualifiedComponentClass("/a/"));
        assertEquals("/a_b", SplitUpperActionPathNamingRule.toQualifiedComponentClass("/a/b"));
        assertEquals("/a_b_c", SplitUpperActionPathNamingRule.toQualifiedComponentClass("/a/b/c"));
        assertEquals("/aaa_bbb", SplitUpperActionPathNamingRule.toQualifiedComponentClass("/aaa/bbb"));
        assertEquals("/aaa_bbb_ccc", SplitUpperActionPathNamingRule.toQualifiedComponentClass("/aaa/bbb/ccc"));
        assertEquals("/aaa_bbb_ccc", SplitUpperActionPathNamingRule.toQualifiedComponentClass("/aaa//bbb/ccc"));
    }

    public void testToQualifiedNextPath() {
        assertEquals("", SplitUpperActionPathNamingRule.toQualifiedNextPath(""));
        assertEquals("/", SplitUpperActionPathNamingRule.toQualifiedNextPath("/"));
        assertEquals("/a", SplitUpperActionPathNamingRule.toQualifiedNextPath("/a"));
        assertEquals("/a", SplitUpperActionPathNamingRule.toQualifiedNextPath("/a/"));
        assertEquals("/aB", SplitUpperActionPathNamingRule.toQualifiedNextPath("/a/b"));
        assertEquals("/a/bC", SplitUpperActionPathNamingRule.toQualifiedNextPath("/a/b/c"));
        assertEquals("/aaaBbb", SplitUpperActionPathNamingRule.toQualifiedNextPath("/aaa/bbb"));
        assertEquals("/aaa/bbbCcc", SplitUpperActionPathNamingRule.toQualifiedNextPath("/aaa/bbb/ccc"));
        assertEquals("/user/passwordUpdate", SplitUpperActionPathNamingRule
                .toQualifiedNextPath("/user/password/update"));
    }

    public void testToUpperNameToPath() {
        assertNull(SplitUpperActionPathNamingRule.toSplitUpperName(null));
        assertEquals("/foo/bar/hoge", SplitUpperActionPathNamingRule.toSplitUpperName("/foo/bar/hoge"));
        assertEquals("/foo/bar/hoge", SplitUpperActionPathNamingRule.toSplitUpperName("/foo/BarHoge"));
        assertEquals("/foo/bar/hoge", SplitUpperActionPathNamingRule.toSplitUpperName("/fooBarHoge"));
        assertEquals("/foo/bar/hoge", SplitUpperActionPathNamingRule.toSplitUpperName("/FooBarHoge"));
        assertEquals("/foo/_/bar/hoge", SplitUpperActionPathNamingRule.toSplitUpperName("/Foo_BarHoge"));
        assertEquals("/foo/-/bar/hoge", SplitUpperActionPathNamingRule.toSplitUpperName("/Foo-BarHoge"));
    }

}
