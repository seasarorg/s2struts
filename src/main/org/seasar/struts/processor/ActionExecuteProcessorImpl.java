package org.seasar.struts.processor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;
import org.seasar.struts.context.ContentsType;
import org.seasar.struts.exception.MethodNotFoundException;
import org.seasar.struts.util.BindingUtil;
import org.seasar.struts.util.ClassRegister;
import org.seasar.struts.util.InvokeUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 */
public class ActionExecuteProcessorImpl implements ActionExecuteProcessor {

    private ClassRegister classRegister;

    /**
     * @see org.seasar.struts.processor.ActionExecuteProcessor#processActionExecute(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.apache.struts.action.ActionForm,
     *      org.apache.struts.action.ActionMapping)
     */
    public ActionForward processActionExecute(HttpServletRequest request, HttpServletResponse response, Object action,
            Object form, ActionMapping mapping) throws IOException, ServletException {
        
        String forward = (String) InvokeUtil.invoke(S2StrutsContextUtil.getMethodBindingExpression(), mapping);
        if (forward != null) {
            S2StrutsContextUtil.clear(ContentsType.MethodBindingExpression);
            return mapping.findForward(forward);
        }

        String actionName = mapping.getType();
        Class actionInterface = classRegister.getClass(actionName);
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ComponentDef componentDef = container.getComponentDef(actionInterface);
        BeanDesc beanDesc = new BeanDescImpl(componentDef.getComponentClass());

        BindingUtil.importProperties(action, container, beanDesc, mapping);
        forward = execute(request, actionInterface, action, mapping);
        BindingUtil.exportProperties(action, container, beanDesc, mapping);

        if (forward != null) {
            return mapping.findForward(forward);
        } else {
            return null;
        }
    }

    private String execute(HttpServletRequest request, Class actionInterface, Object action, ActionMapping mapping) {
        Method[] methods = actionInterface.getMethods();
        String forward = null;
        if (methods.length == 0) {
            throw new MethodNotFoundException("Action is " + actionInterface.getName());
        } else if (methods.length == 1) {
            forward = (String) MethodUtil.invoke(methods[0], action, null);
        } else {
            String param = mapping.getParameter();
            String methodName = request.getParameter(param);
            Method method = ClassUtil.getMethod(action.getClass(), methodName, null);
            forward = (String) MethodUtil.invoke(method, action, null);
        }
        return forward;
    }

    /**
     * @param classRegister The classRegister to set.
     */
    public void setClassRegister(ClassRegister classRegister) {
        this.classRegister = classRegister;
    }

}