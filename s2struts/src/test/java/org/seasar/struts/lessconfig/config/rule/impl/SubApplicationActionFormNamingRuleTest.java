package org.seasar.struts.lessconfig.config.rule.impl;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.convention.impl.NamingConventionImpl;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.lessconfig.config.rule.ActionFormNamingRule;
import org.seasar.struts.lessconfig.config.rule.impl.web.aaa.TestForm;

public class SubApplicationActionFormNamingRuleTest extends S2TestCase {

    private ActionFormNamingRule rule;

    protected void setUp() throws Exception {
        super.setUp();
        NamingConventionImpl convention = (NamingConventionImpl) getNamingConvention();
        convention.addRootPackageName(ClassUtil.getPackageName(getClass()));
        register(SubApplicationActionFormNamingRule.class);
    }

    public void testToComponentClass() throws Exception {
        Class clazz = rule.toComponentClass("aaa_testForm");
        assertEquals(TestForm.class, clazz);
    }
}
