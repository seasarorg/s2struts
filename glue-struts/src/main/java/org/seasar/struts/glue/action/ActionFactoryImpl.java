package org.seasar.struts.glue.action;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.seasar.struts.glue.ActionFactory;
import org.seasar.struts.glue.annotation.Form;
import org.seasar.struts.glue.annotation.RequestAttribute;
import org.seasar.struts.glue.annotation.SessionAttribute;
import org.seasar.struts.glue.exception.ActionMethodNotFoundException;
import org.seasar.struts.glue.exception.IllegalActionFormTypeException;
import org.seasar.struts.glue.exception.IllegalActionMethodSignatureException;
import org.seasar.struts.glue.exception.TooManyFormFieldException;

public class ActionFactoryImpl implements ActionFactory {

    protected ConcurrentMap<String, ActionDesc> actionDescMap = new ConcurrentHashMap<String, ActionDesc>();

    public ActionFactoryImpl() {
    }

    public Action getAction(final ActionServlet servlet,
            final Object component, final String methodName) {
        final ActionDesc desc = getActionDesc(component.getClass());
        final Method actionMethod = getActionMethod(component.getClass(),
                methodName, desc.declaredMethods);
        final AdapterAction action = new AdapterAction(servlet, component,
                actionMethod);
        action.setActionFormField(desc.actionFormField);
        action.addRequestAttributeFields(desc.requestAttributeFields);
        action.addSessionAttributeFields(desc.sessionAttributeFields);
        return action;
    }

    protected ActionDesc getActionDesc(final Class<?> clazz) {
        ActionDesc actionDesc = actionDescMap.get(clazz.getName());
        if (actionDesc != null) {
            return actionDesc;
        }
        final List<Field> fields = getAllFields(clazz);
        actionDesc = new ActionDesc();
        actionDesc.actionFormField = getActionFromField(clazz, fields);
        actionDesc.requestAttributeFields = getRequestAttributeFields(fields);
        actionDesc.sessionAttributeFields = getSessionAttributeFields(fields);
        actionDesc.declaredMethods = getAllMethods(clazz);
        final ActionDesc actionDesc2 = actionDescMap.putIfAbsent(clazz
                .getName(), actionDesc);
        return actionDesc2 != null ? actionDesc2 : actionDesc;
    }

    protected List<Field> getAllFields(final Class<?> clazz) {
        final List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = clazz; c != Object.class; c = c.getSuperclass()) {
            for (final Field f : c.getDeclaredFields()) {
                fields.add(f);
            }
        }
        return fields;
    }

    protected Field getActionFromField(final Class<?> clazz,
            final List<Field> fields) {
        final List<Field> candidates = getAnnotatedFields(fields, Form.class);
        if (candidates.isEmpty()) {
            return null;
        } else if (candidates.size() == 1) {
            final Field f = candidates.get(0);
            if (!org.apache.struts.action.ActionForm.class.isAssignableFrom(f
                    .getType())) {
                throw new IllegalActionFormTypeException(clazz.getName(), f
                        .getName());
            }
            return f;
        }
        throw new TooManyFormFieldException(clazz.getName());
    }

    protected List<Field> getRequestAttributeFields(final List<Field> fields) {
        return getAnnotatedFields(fields, RequestAttribute.class);
    }

    protected List<Field> getSessionAttributeFields(final List<Field> fields) {
        return getAnnotatedFields(fields, SessionAttribute.class);
    }

    protected List<Field> getAnnotatedFields(final List<Field> fields,
            final Class<? extends Annotation> annotationClass) {
        final List<Field> annotatedFields = new ArrayList<Field>();
        for (final Field f : fields) {
            if (f.isAnnotationPresent(annotationClass)) {
                annotatedFields.add(f);
                f.setAccessible(true);
            }
        }
        return annotatedFields;
    }

    protected Map<String, Method> getAllMethods(final Class<?> clazz) {
        final Map<String, Method> methods = new HashMap<String, Method>();
        for (Class<?> c = clazz; c != Object.class; c = c.getSuperclass()) {
            for (final Method m : c.getDeclaredMethods()) {
                methods.put(m.getName(), m);
            }
        }
        return methods;
    }

    protected Method getActionMethod(final Class<?> clazz,
            final String methodName, final Map<String, Method> methods) {
        if (methodName == null) {
            return null;
        }
        final Method actionMethod = methods.get(methodName);
        if (actionMethod == null) {
            throw new ActionMethodNotFoundException(clazz.getName(), methodName);
        }
        final int modifiers = actionMethod.getModifiers();
        if (!Modifier.isPublic(modifiers)) {
            throw new IllegalActionMethodSignatureException(clazz.getName(),
                    methodName);
        }
        return actionMethod;
    }

    protected static class ActionDesc {
        protected Field actionFormField;

        protected List<Field> requestAttributeFields;

        protected List<Field> sessionAttributeFields;

        protected Map<String, Method> declaredMethods;
    }
}
