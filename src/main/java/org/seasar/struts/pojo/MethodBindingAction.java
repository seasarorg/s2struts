package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MethodBindingAction extends Action {

    private MethodBinding methodBinding;

    public MethodBindingAction(MethodBinding methodBinding) {
        this.methodBinding = methodBinding;
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String forward = (String) this.methodBinding.invoke(mapping);
        if (forward != null) {
            return mapping.findForward(forward);
        } else {
            return null;
        }
    }

}
