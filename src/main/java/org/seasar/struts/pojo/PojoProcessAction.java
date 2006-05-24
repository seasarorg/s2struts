package org.seasar.struts.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.util.ClassRegister;

public class PojoProcessAction extends Action {

    private ClassRegister classRegister;

    public void setClassRegister(ClassRegister classRegister) {
        this.classRegister = classRegister;
    }

    private List actionCommands = new ArrayList();

    public void addActionCommnad(ActionCommand actionCommand) {
        this.actionCommands.add(actionCommand);
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (this.actionCommands.size() == 0) {
            throw new RuntimeException("ActionCommand is not registered.");
        }

        S2Container container = SingletonS2ContainerFactory.getContainer();
        Class actionInterface = this.classRegister.getClass(mapping.getType());
        if (!container.hasComponentDef(actionInterface)) {
            throw new RuntimeException("POJOAction of " + actionInterface.getName()
                    + " is not registered in S2Container.");
        }
        Object actionInstance = container.getComponent(actionInterface);

        ActionInvocation invocation = new ActionInvocationImpl(this.actionCommands, mapping,
                actionInterface, actionInstance, form, request, response);
        String forward = invocation.execute();
        if (forward != null) {
            return mapping.findForward(forward);
        } else {
            return null;
        }
    }

}
