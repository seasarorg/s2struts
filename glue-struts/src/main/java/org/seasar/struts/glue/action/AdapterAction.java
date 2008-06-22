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
import org.apache.struts.action.ActionServlet;
import org.seasar.struts.glue.annotation.Form;
import org.seasar.struts.glue.annotation.RequestAttribute;
import org.seasar.struts.glue.annotation.SessionAttribute;
import org.seasar.struts.glue.util.FieldUtil;
import org.seasar.struts.glue.util.MethodUtil;

public class AdapterAction extends Action {

    protected ActionServlet servlet;

    protected Object action;

    protected Method actionMethod;

    protected Field actionFormField;

    protected List<Field> requestAttributeFields = new ArrayList<Field>();

    protected List<Field> sessionAttributeFields = new ArrayList<Field>();

    public AdapterAction(final ActionServlet servlet, final Object action,
            final Method actionMethod) {
        if (servlet == null) {
            throw new NullPointerException("servlet");
        }
        if (action == null) {
            throw new NullPointerException("action");
        }
        if (actionMethod == null) {
            throw new NullPointerException("actionMethod");
        }
        this.action = action;
        this.actionMethod = actionMethod;
    }

    public void setActionFormField(final Field actionFormField) {
        this.actionFormField = actionFormField;
    }

    public void addRequestAttributeFields(
            final List<Field> requestAttributeFields) {
        this.requestAttributeFields.addAll(requestAttributeFields);
    }

    public void addSessionAttributeFields(
            final List<Field> sessionAttributeFields) {
        this.sessionAttributeFields.addAll(sessionAttributeFields);
    }

    @Override
    public ActionForward execute(final ActionMapping mapping,
            final ActionForm actionForm, final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        bindActionServlet();
        bindActionForm(actionForm);
        impoprtRequestAttribute(request);
        impoprtSessionAttribute(request);
        final ActionForward forward = execute(mapping);
        exportActionForm(request, actionForm);
        exportRequestAttribute(request);
        exportSessionAttribute(request);
        return forward;
    }

    protected ActionForward execute(final ActionMapping mapping)
            throws Exception {
        final String forwardName = (String) MethodUtil.invoke(actionMethod,
                action, null);
        if (mapping != null && forwardName != null) {
            return mapping.findForward(forwardName);
        }
        return null;
    }

    protected void bindActionServlet() {
        if (Action.class.isInstance(action)) {
            Action.class.cast(action).setServlet(servlet);
        }
    }

    protected void bindActionForm(final ActionForm actionForm) {
        if (actionFormField != null) {
            FieldUtil.set(actionFormField, action, actionForm);
        }
    }

    protected void exportActionForm(final HttpServletRequest request,
            final ActionForm actionForm) {
        if (actionFormField == null) {
            return;
        }
        final Form form = actionFormField.getAnnotation(Form.class);
        if (!form.exportsToRequest()) {
            return;
        }
        String name = null;
        if ("".equals(form.name())) {
            name = actionFormField.getName();
        } else {
            name = form.name();
        }
        final Object value = FieldUtil.get(actionFormField, action);
        if (value != null) {
            request.setAttribute(name, value);
        }
    }

    protected void impoprtRequestAttribute(final HttpServletRequest request) {
        for (final Field field : requestAttributeFields) {
            final RequestAttribute attr = field
                    .getAnnotation(RequestAttribute.class);
            if (!attr.imports() || FieldUtil.get(field, action) != null) {
                continue;
            }
            String name = null;
            if ("".equals(attr.name())) {
                name = field.getName();
            } else {
                name = attr.name();
            }
            final Object value = request.getAttribute(name);
            FieldUtil.set(field, action, value);
        }
    }

    protected void exportRequestAttribute(final HttpServletRequest request) {
        for (final Field field : requestAttributeFields) {
            final RequestAttribute attr = field
                    .getAnnotation(RequestAttribute.class);
            if (!attr.exports()) {
                continue;
            }
            String name = null;
            if ("".equals(attr.name())) {
                name = field.getName();
            } else {
                name = attr.name();
            }
            final Object value = FieldUtil.get(field, action);
            if (value != null) {
                request.setAttribute(name, value);
            }
        }
    }

    protected void impoprtSessionAttribute(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        for (final Field field : sessionAttributeFields) {
            final SessionAttribute attr = field
                    .getAnnotation(SessionAttribute.class);
            if (!attr.imports() || FieldUtil.get(field, action) != null) {
                continue;
            }
            String name = null;
            if ("".equals(attr.name())) {
                name = field.getName();
            } else {
                name = attr.name();
            }
            final Object value = session.getAttribute(name);
            FieldUtil.set(field, action, value);
        }
    }

    protected void exportSessionAttribute(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        for (final Field field : sessionAttributeFields) {
            final SessionAttribute attr = field
                    .getAnnotation(SessionAttribute.class);
            if (!attr.exports()) {
                continue;
            }
            String name = null;
            if ("".equals(attr.name())) {
                name = field.getName();
            } else {
                name = attr.name();
            }
            final Object value = FieldUtil.get(field, action);
            if (value != null) {
                session.setAttribute(name, value);
            }
        }
    }

}
