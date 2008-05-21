package org.seasar.struts.examples.web.employee;

import org.seasar.struts.annotation.tiger.StrutsActionForward;

public abstract class AbstractEmployeeAction {

    @StrutsActionForward
    public static final String CONFIRM = "/pages/employee/confirm.jsp";

    @StrutsActionForward
    public static final String EDIT = "/pages/employee/edit.jsp";

    @StrutsActionForward
    public static final String SEARCH = "/pages/employee/search.jsp";

    @StrutsActionForward
    public static final String LIST = "/pages/employee/list.jsp";

    protected String crudType;

    public String getCrudType() {
        return crudType;
    }

    public void setCrudType(String crudType) {
        this.crudType = crudType;
    }
}
