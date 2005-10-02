package org.seasar.struts.examples.employee.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(parameter="method", validate=false)
 */
public interface EmployeeAction {

	public String ERROR = "error";

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeSearch.do", redirect=true)
	 */
	public String SEARCH = "search";

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeList.do")
	 */
	public String LIST = "list";

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeList.do", redirect=true)
	 */
	public String BACK_LIST = "backList";

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeEdit.do")
	 */
	public String EDIT = "edit";

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeEditConfirm.do")
	 */
	public String EDIT_CONFIRM = "editConfirm";

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeConfirm.do")
	 */
	public String CONFIRM = "confirm";

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeStore.do")
	 */
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