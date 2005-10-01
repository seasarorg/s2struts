package org.seasar.struts.config.rule;

import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.config.StrutsActionFormConfig;

/**
 * @author Satoshi Kimura
 */
public class ZeroConfigActionFormRuleImpl implements ZeroConfigActionFormRule {

    public String getName(Class formClass, ModuleConfig config) {
        String name = ClassUtil.getShortClassName(formClass);
        return CommonNamingRule.decapitalizeName(name);
    }

    public boolean getRestricted(Class formClass, ModuleConfig config) {
        return StrutsActionFormConfig.DEFAULT_RESTRICTED;
    }
}
