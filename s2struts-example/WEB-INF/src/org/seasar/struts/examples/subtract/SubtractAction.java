package org.seasar.struts.examples.subtract;

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
public class SubtractAction extends Action {
    private SubtractService subtractService_;

    public SubtractAction(SubtractService subtractService) {
        subtractService_ = subtractService;
        System.out.println("created new SubtractAction");
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

        CalculationForm calForm = (CalculationForm) form;
        int result = subtractService_.subtract(calForm.getArg1(), calForm.getArg2());
        calForm.setResult(result);
        return (mapping.findForward("success"));
    }
}