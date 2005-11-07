package org.seasar.struts.action;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.S2Container;

/**
 * @see org.seasar.struts.ComponentNameCreator
 * @author Satoshi Kimura
 */
public class ComponentNameCreatorImpl implements ComponentNameCreator {

    /**
     * ActionMappingからコンポーネント名を、作成する。 <br>
     * コンポーネント名を検索する順番は、
     * <UL>
     * <LI>module名(prefix)+path</LI>
     * <LI>path</LI>
     * </UL>
     * となる。
     * 
     * @see org.seasar.struts.ComponentNameCreator#createComponentName(org.seasar.framework.container.S2Container,
     *      org.apache.struts.action.ActionMapping)
     */
    public String createComponentName(S2Container container, ActionMapping mapping) {
        String moduleName = mapping.getModuleConfig().getPrefix();
        String path = mapping.getPath();

        String componentName = moduleName + path;
        if (false == container.hasComponentDef(componentName)) {
            componentName = path;
        }
        return componentName;
    }
}