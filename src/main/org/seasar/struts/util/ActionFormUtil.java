package org.seasar.struts.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.struts.Constants;

/**
 * @author Katsuhiko Nagashima
 */
public class ActionFormUtil {

    private ActionFormUtil() {
    }

    public static Object getActualForm(HttpServletRequest request, ActionMapping mapping) {
        Object form = getActionForm(request, mapping);
        return BeanValidatorFormUtil.toBean(form);
    }
    
    public static void setActualForm(HttpServletRequest request, Object form, ActionMapping mapping) {
        if (form instanceof ActionForm) {
            setActionForm(request, form, mapping);
        } else {
            BeanValidatorForm oldForm = (BeanValidatorForm) getActionForm(request, mapping);
            Object newForm = BeanValidatorFormUtil.toBeanValidatorForm(oldForm, form);
            setActionForm(request, newForm, mapping);
        }

    }

    private static ActionForm getActionForm(HttpServletRequest request, ActionMapping mapping) {
        if (Constants.REQUEST.equals(mapping.getScope())) {
            return (ActionForm) request.getAttribute(mapping.getAttribute());
        } else {
            HttpSession session = request.getSession();
            return (ActionForm) session.getAttribute(mapping.getAttribute());
        }
    }

    private static void setActionForm(HttpServletRequest request, Object form, ActionMapping mapping) {
        if (form == null) {
            return;
        }

        String scope = mapping.getScope();
        if (Constants.REQUEST.equals(scope)) {
            request.setAttribute(mapping.getAttribute(), form);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(mapping.getAttribute(), form);
        }

    }

}
