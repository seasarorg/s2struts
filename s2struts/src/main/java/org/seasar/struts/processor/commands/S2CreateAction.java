package org.seasar.struts.processor.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.commands.AbstractCreateAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.action.ActionFactory;

/**
 * 実行するActionをS2Containerから取得し、ActionContextに設定する。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class S2CreateAction extends AbstractCreateAction {

    protected Action getAction(ActionContext context, String type, ActionConfig actionConfig) throws Exception {
        ServletActionContext saContext = (ServletActionContext) context;
        HttpServletRequest request = saContext.getRequest();
        HttpServletResponse response = saContext.getResponse();
        ActionMapping mapping = (ActionMapping) actionConfig;

        ActionFactory actionFactory = getActionFactory();
        return actionFactory.processActionCreate(request, response, mapping, saContext.getLogger(), saContext
                .getMessageResources(), saContext.getActionServlet());
    }

    //
    //
    //

    private ActionFactory getActionFactory() {
        return (ActionFactory) getContainer().getComponent(ActionFactory.class);
    }

    private S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

}
