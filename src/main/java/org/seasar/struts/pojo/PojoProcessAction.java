/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class PojoProcessAction extends Action {

    private ClassRegister classRegister;

    public void setClassRegister(ClassRegister classRegister) {
        this.classRegister = classRegister;
    }

    private List pojoCommands = new ArrayList();

    public void addPojoCommnad(PojoCommand pojoCommand) {
        this.pojoCommands.add(pojoCommand);
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (this.pojoCommands.size() == 0) {
            throw new RuntimeException("PojoCommand is not registered.");
        }

        S2Container container = SingletonS2ContainerFactory.getContainer();
        Class actionInterface = this.classRegister.getClass(mapping.getType());
        if (!container.hasComponentDef(actionInterface)) {
            throw new RuntimeException("POJOAction of " + actionInterface.getName()
                    + " is not registered in S2Container.");
        }
        Object actionInstance = container.getComponent(actionInterface);

        PojoInvocation invocation = new PojoInvocationImpl(this.pojoCommands, mapping,
                actionInterface, actionInstance, form, request, response);
        String forward = invocation.execute();
        if (forward != null) {
            return mapping.findForward(forward);
        } else {
            return null;
        }
    }

}
