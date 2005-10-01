package org.seasar.struts.config.rule;

import org.seasar.framework.util.StringUtil;

/**
 * @author Satoshi Kimura
 */
public abstract class CommonNamingRule {
    public static String decapitalizeName(String name) {
        if (StringUtil.isEmpty(name)) {
            return name;
        } else {
            name = name.replaceFirst("Impl$", "");
        }

        if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
            return name;
        }
        char chars[] = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}
