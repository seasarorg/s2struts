package org.seasar.struts.lessconfig.config.rule.impl;

import org.seasar.framework.convention.NamingConvention;
import org.seasar.struts.lessconfig.config.StrutsActionFormConfig;
import org.seasar.struts.lessconfig.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.lessconfig.factory.StrutsConfigAnnotationHandlerFactory;

/**
 * {@link NamingConvention}に従ってサブアプリケーション内にあるActionFormの名前とクラスを解決します。
 * 
 * @author taedium
 */
public class SubApplicationActionFormNamingRule extends DefaultActionFormNamingRule {

    private NamingConvention convention;

    /**
     * {@link NamingConvention 命名規約}を設定します。
     * 
     * @param convention
     */
    public void setConvention(NamingConvention convention) {
        this.convention = convention;
    }

    public Class toComponentClass(String name) {
        Class formClass = convention.fromComponentNameToClass(name);
        if (formClass != null) {
            StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
            StrutsActionFormConfig strutsActionForm = annHandler.createStrutsActionFormConfig(formClass);
            if (strutsActionForm != null && StrutsActionFormConfig.DEFAULT_NAME.equals(strutsActionForm.name())) {
                return formClass;
            }
        }
        return null;
    }

    public String toActionFormName(Class formClass) {
        return convention.fromClassNameToComponentName(formClass.getName());
    }

}
