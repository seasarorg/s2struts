package org.seasar.struts.pojo.commands;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.pojo.PojoCommand;
import org.seasar.struts.pojo.PojoInvocation;
import org.seasar.struts.util.BindingUtil;

public class PropertyBindingCommand implements PojoCommand {
    
    public String execute(PojoInvocation invocation) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ActionMapping mapping = invocation.getActionMapping();
        Object action = invocation.getActionInstance();
        
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(action.getClass());
        BindingUtil.importProperties(action, container, beanDesc, mapping);
        String forward = invocation.execute();
        BindingUtil.exportProperties(action, container, beanDesc, mapping);

        return forward;
    }

}
