package org.seasar.struts.pojo.processor.commands;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.chain.commands.servlet.SelectAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;
import org.seasar.struts.Constants;
import org.seasar.struts.lessconfig.autoregister.impl.StrutsConfigRegisterImpl;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;
import org.seasar.struts.pojo.MethodBinding;
import org.seasar.struts.pojo.MethodBindingFactory;
import org.seasar.struts.pojo.factory.ActionAnnotationHandler;
import org.seasar.struts.pojo.factory.ActionAnnotationHandlerFactory;

public class S2SelectAction extends SelectAction {

    private static final Logger log = Logger.getLogger(StrutsConfigRegisterImpl.class);

    protected String getPath(ActionContext context) {
        String path = super.getPath(context);
        ServletActionContext saContext = (ServletActionContext) context;
        HttpServletRequest request = saContext.getRequest();
        request.setAttribute(Constants.ORIGINAL_PATH_KEY, path);
        MethodBinding methodBinding = MethodBindingFactory.getMethodBinding(request, path);
        if (methodBinding == null) {
            return path;
        }
        Method method = methodBinding.getMethod();
        ActionAnnotationHandler handler = ActionAnnotationHandlerFactory.getAnnotationHandler();
        String actualPath = handler.getPath(method);
        if (actualPath == null) {
            ActionPathNamingRule namingRule = getActionPathNamingRule();
            actualPath = namingRule.toActionPathName(methodBinding.getComponentClass());
        }
        log.log("DSTR0001", new Object[] { methodBinding.getExpression(), actualPath });
        return actualPath;
    }

    protected ActionPathNamingRule getActionPathNamingRule() {
        return (ActionPathNamingRule) SingletonS2ContainerFactory.getContainer().getComponent(
                ActionPathNamingRule.class);
    }
}
