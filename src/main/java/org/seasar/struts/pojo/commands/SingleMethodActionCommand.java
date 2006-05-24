package org.seasar.struts.pojo.commands;

import java.lang.reflect.Method;

import org.seasar.framework.util.MethodUtil;
import org.seasar.struts.pojo.ActionCommand;
import org.seasar.struts.pojo.ActionInvocation;

public class SingleMethodActionCommand implements ActionCommand {

    public String execute(ActionInvocation invocation) {
        Class actionInterface = invocation.getActionInterface();
        Object action = invocation.getActionInstance();
        
        Method[] methods = actionInterface.getMethods();
        if (methods.length == 1) {
            return (String) MethodUtil.invoke(methods[0], action, null);
        }

        return invocation.execute();
    }

}
