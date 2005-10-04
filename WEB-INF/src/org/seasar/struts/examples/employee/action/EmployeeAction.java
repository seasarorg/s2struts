package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(parameter = "method", validate = false)
public interface EmployeeAction {

    public String ERROR = "error";

    @StrutsActionForward(path = "/employeeSearch.do", redirect = true)
    public String SEARCH = "search";

    @StrutsActionForward(path = "/employeeList.do")
    public String LIST = "list";

    @StrutsActionForward(path = "/employeeList.do", redirect = true)
    public String BACK_LIST = "backList";

    @StrutsActionForward(path = "/employeeEdit.do")
    public String EDIT = "edit";

    @StrutsActionForward(path = "/employeeEditConfirm.do")
    public String EDIT_CONFIRM = "editConfirm";

    @StrutsActionForward(path = "/employeeConfirm.do")
    public String CONFIRM = "confirm";

    @StrutsActionForward(path = "/employeeStore.do")
    public String STORE = "store";

    //
    //
    //

    public String goError();

    public String goSearch();

    public String goList();

    public String goEditForCreate();

    public String goEditForUpdate();

    public String goDelete();

    public String goInquire();

    public String goConfirm();

    public String goPreviousFromEdit();

    public String goPreviousFromConfirm();

    public String goStore();

    public String goStoreToNext();

}