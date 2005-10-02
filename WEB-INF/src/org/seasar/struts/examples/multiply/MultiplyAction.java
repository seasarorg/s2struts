package org.seasar.struts.examples.multiply;

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
public class MultiplyAction extends Action {

	private MultiplyService multiplyService_;

	public MultiplyAction(MultiplyService multiplyService) {
	    multiplyService_ = multiplyService;
	}

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		CalculationForm calForm = (CalculationForm) form;
		int result = multiplyService_.multiply(calForm.getArg1(), calForm.getArg2());
		calForm.setResult(result);
		return (mapping.findForward("success"));
	}
}
