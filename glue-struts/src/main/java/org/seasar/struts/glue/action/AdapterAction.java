package org.seasar.struts.glue.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.struts.glue.annotation.RequestAttribute;
import org.seasar.struts.glue.annotation.SessionAttribute;
import org.seasar.struts.glue.util.FieldUtil;
import org.seasar.struts.glue.util.MethodUtil;

public class AdapterAction extends Action {

    protected Object action;

    protected Method actionMethod;

    protected Field actionFormField;

    protected List<Field> requestAttributeFields = new ArrayList<Field>();

    protected List<Field> sessionAttributeFields = new ArrayList<Field>();

    public AdapterAction(Object action, Method actionMethod) {
        if (action == null) {
            throw new NullPointerException("action");
        }
        if (actionMethod == null) {
            throw new NullPointerException("actionMethod");
        }
        this.action = action;
        this.actionMethod = actionMethod;
    }

    public void setActionFormField(Field actionFormField) {
        this.actionFormField = actionFormField;
    }

    public void addRequestAttributeFields(List<Field> requestAttributeFields) {
        this.requestAttributeFields.addAll(requestAttributeFields);
    }

    public void addSessionAttributeFields(List<Field> sessionAttributeFields) {
        this.sessionAttributeFields.addAll(sessionAttributeFields);
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        bindActionForm(actionForm);
        impoprtRequestAttribute(request);
        impoprtSessionAttribute(request);
        ActionForward forward = execute(mapping);
        exportRequestAttribute(request);
        exportSessionAttribute(request);
        return forward;
    }

    protected ActionForward execute(ActionMapping mapping) throws Exception {
        String forwardName = (String) MethodUtil.invoke(actionMethod, action,
                null);
        if (mapping != null && forwardName != null) {
            return mapping.findForward(forwardName);
        }
        return null;
    }

    protected void bindActionForm(ActionForm actionForm) {
        if (actionFormField != null) {
            FieldUtil.set(actionFormField, action, actionForm);
        }
    }

    protected void impoprtRequestAttribute(HttpServletRequest request) {
        for (Field field : requestAttributeFields) {
            RequestAttribute ra = field.getAnnotation(RequestAttribute.class);
            if (!ra.imports() || FieldUtil.get(field, action) != null) {
                continue;
            }
            String name = null;
            if ("".equals(ra.name())) {
                name = field.getName();
            } else {
                name = ra.name();
            }
            Object value = request.getAttribute(name);
            FieldUtil.set(field, action, value);
        }
    }

    protected void exportRequestAttribute(HttpServletRequest request) {
        for (Field field : requestAttributeFields) {
            RequestAttribute ra = field.getAnnotation(RequestAttribute.class);
            if (!ra.exports()) {
                continue;
            }
            String name = null;
            if ("".equals(ra.name())) {
                name = field.getName();
            } else {
                name = ra.name();
            }
            Object value = FieldUtil.get(field, action);
            if (value != null) {
                request.setAttribute(name, value);
            }
        }
    }

    protected void impoprtSessionAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        for (Field field : sessionAttributeFields) {
            SessionAttribute sa = field.getAnnotation(SessionAttribute.class);
            if (!sa.imports() || FieldUtil.get(field, action) != null) {
                continue;
            }
            String name = null;
            if ("".equals(sa.name())) {
                name = field.getName();
            } else {
                name = sa.name();
            }
            Object value = session.getAttribute(name);
            FieldUtil.set(field, action, value);
        }
    }

    protected void exportSessionAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        for (Field field : sessionAttributeFields) {
            SessionAttribute sa = field.getAnnotation(SessionAttribute.class);
            if (!sa.exports()) {
                continue;
            }
            String name = null;
            if ("".equals(sa.name())) {
                name = field.getName();
            } else {
                name = sa.name();
            }
            Object value = FieldUtil.get(field, action);
            if (value != null) {
                session.setAttribute(name, value);
            }
        }
    }

}
