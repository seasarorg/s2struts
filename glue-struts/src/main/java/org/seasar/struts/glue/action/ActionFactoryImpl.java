package org.seasar.struts.glue.action;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.struts.action.Action;
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

    public Action getAction(Object component, String methodName) {
        Class<?> clazz = component.getClass();
        Method actionMethod = getActionMethod(clazz, methodName);
        ActionDesc desc = getActionDesc(clazz);

        AdapterAction action = new AdapterAction(component, actionMethod);
        action.setActionFormField(desc.actionFormField);
        action.addRequestAttributeFields(desc.requestAttributeFields);
        action.addSessionAttributeFields(desc.sessionAttributeFields);
        return action;
    }

    protected ActionDesc getActionDesc(Class<?> clazz) {
        ActionDesc actionDesc = actionDescMap.get(clazz.getName());
        if (actionDesc != null) {
            return actionDesc;
        }
        List<Field> fields = getAllFields(clazz);
        actionDesc = new ActionDesc();
        actionDesc.actionFormField = getActionFromField(clazz, fields);
        actionDesc.requestAttributeFields = getRequestAttributeFields(fields);
        actionDesc.sessionAttributeFields = getSessionAttributeFields(fields);
        ActionDesc actionDesc2 = actionDescMap.putIfAbsent(clazz.getName(),
                actionDesc);
        return actionDesc2 == null ? actionDesc : actionDesc2;
    }

    protected List<Field> getAllFields(Class<?> clazz) {
        List<Field> result = new ArrayList<Field>();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            for (Field f : clazz.getDeclaredFields()) {
                result.add(f);
            }
        }
        return result;
    }

    protected Field getActionFromField(Class<?> clazz, List<Field> fields) {
        List<Field> candidates = getAnnotatedFields(fields, Form.class);
        if (candidates.isEmpty()) {
            return null;
        } else if (candidates.size() == 1) {
            Field f = candidates.get(0);
            if (!org.apache.struts.action.ActionForm.class.isAssignableFrom(f
                    .getType())) {
                throw new IllegalActionFormTypeException(clazz.getName(), f
                        .getName());
            }
            f.setAccessible(true);
            return f;
        }
        throw new TooManyFormFieldException(clazz.getName());
    }

    protected List<Field> getRequestAttributeFields(List<Field> fields) {
        return getAnnotatedFields(fields, RequestAttribute.class);
    }

    protected List<Field> getSessionAttributeFields(List<Field> fields) {
        return getAnnotatedFields(fields, SessionAttribute.class);
    }

    protected List<Field> getAnnotatedFields(List<Field> fields,
            Class<? extends Annotation> annotationClass) {
        List<Field> annotatedFields = new ArrayList<Field>();
        for (Field f : fields) {
            if (f.isAnnotationPresent(annotationClass)) {
                annotatedFields.add(f);
                f.setAccessible(true);
            }
        }
        return annotatedFields;
    }

    protected Method getActionMethod(Class<?> clazz, String methodName) {
        if (methodName == null) {
            return null;
        }
        Method actionMethod = null;
        try {
            actionMethod = clazz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new ActionMethodNotFoundException(e, clazz.getName(),
                    methodName);
        }
        int modifiers = actionMethod.getModifiers();
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
    }
}
