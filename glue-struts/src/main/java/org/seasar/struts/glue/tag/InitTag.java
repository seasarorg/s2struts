package org.seasar.struts.glue.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseTag;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.FormTag;
import org.seasar.struts.glue.ActionFactory;
import org.seasar.struts.glue.NameExtracter;
import org.seasar.struts.glue.Registry;
import org.seasar.struts.glue.RegistryLocator;

public class InitTag extends BaseTag {

    private String component;

    private String method = "@init";

    boolean commited;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int doStartTag() throws JspException {
        commited = pageContext.getResponse().isCommitted();
        if (commited) {
            return SKIP_BODY;
        }

        ModuleConfig moduleConfig = TagUtils.getInstance().getModuleConfig(
                pageContext);
        if (moduleConfig == null) {
            throwException(new JspException("moduleConfig not found"));
        }

        Registry registry = RegistryLocator.getInstance();
        NameExtracter nameExtracter = registry.getComponent("nameExtracter");
        String componentName = nameExtracter.extractComponentName(component);
        String methodName = nameExtracter.extractMethodName(method);
        String actionName = getActionName();

        if (actionName != null) {
            String mappingName = TagUtils.getInstance().getActionMappingName(
                    actionName);
            ActionMapping mapping = (ActionMapping) moduleConfig
                    .findActionConfig(mappingName);
            if (mapping == null) {
                throwException(new JspException("mapping not found"));
            }
            if (mapping.getType() != null) {
                return SKIP_BODY;
            }
            componentName = mapping.getPath();
        }

        ActionForm actionForm = (ActionForm) pageContext.getAttribute(
                Constants.BEAN_KEY, PageContext.REQUEST_SCOPE);
        if (componentName == null) {
            throwException(new JspException("componentName is null"));
        }
        Action action = getAction(componentName, methodName);
        try {
            action.execute(null, actionForm, pageContext.getRequest(),
                    pageContext.getResponse());
        } catch (Exception e) {
            throwException(new JspException("invocation failed", e));
        }
        return SKIP_BODY;
    }

    public int doEndTag() {
        if (commited) {
            return SKIP_PAGE;
        } else {
            return EVAL_PAGE;
        }
    }

    protected String getActionName() {
        for (Tag tag = this; tag != null; tag = tag.getParent()) {
            if (FormTag.class.isInstance(tag)) {
                return FormTag.class.cast(tag).getAction();
            }
        }
        return null;
    }

    protected Action getAction(String componentName, String methodName)
            throws JspException {
        Registry registry = RegistryLocator.getInstance();
        if (!registry.hasComponent(componentName)) {
            throwException(new JspException("action component(" + componentName
                    + ") not found"));
        }
        Object object = registry.getComponent(componentName);
        if (!Action.class.isInstance(object)) {
            throwException(new JspException(componentName
                    + " is not action component"));
        }
        ActionFactory actionFactory = registry.getComponent("actionFactory");
        return actionFactory.getAction(object, methodName);
    }

    protected void throwException(JspException e) throws JspException {
        pageContext.setAttribute(Globals.EXCEPTION_KEY, e,
                PageContext.REQUEST_SCOPE);
        throw e;
    }

}
