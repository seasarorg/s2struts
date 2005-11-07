package org.seasar.struts.examples.add;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.struts.examples.form.CalculationForm;

/**
 * @author higa
 * @author Satoshi Kimura
 */
public class AddAction extends Action {
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		CalculationForm calForm = (CalculationForm) form;
		int result = addService_.add(calForm.getArg1(), calForm.getArg2());
		calForm.setResult(result);
		return (mapping.findForward("success"));
	}

	private AddService addService_;

	public AddAction(AddService addService) {
		addService_ = addService;
		System.out.println("created new AddAction");
	}

}
