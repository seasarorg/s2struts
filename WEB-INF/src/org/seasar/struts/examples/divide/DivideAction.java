package org.seasar.struts.examples.divide;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.struts.examples.form.CalculationForm;

/**
 * @author Satoshi Kimura
 */
public class DivideAction extends Action {

	private DivideService divideService_;

	public DivideAction() {
		System.out.println("created new DivideAction");
	}

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		CalculationForm calForm = (CalculationForm) form;
		int result = divideService_.divide(calForm.getArg1(), calForm.getArg2());
		calForm.setResult(result);
		return (mapping.findForward("success"));
	}
	
    public void setDivideService(DivideService divideService_) {
        this.divideService_ = divideService_;
    }
}
