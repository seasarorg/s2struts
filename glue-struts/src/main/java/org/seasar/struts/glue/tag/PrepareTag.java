package org.seasar.struts.glue.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseTag;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.FormTag;
import org.seasar.struts.glue.Glue;
import org.seasar.struts.glue.RegistryLocator;

public class PrepareTag extends BaseTag {

    private static final long serialVersionUID = 1L;

    private String componentName;

    private String methodName = "prepare";

    public PrepareTag() {
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(final String componentName) {
        this.componentName = componentName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    @Override
    public int doStartTag() throws JspException {
        final ModuleConfig moduleConfig = TagUtils.getInstance()
                .getModuleConfig(pageContext);
        if (moduleConfig == null) {
            throwException(new JspException("moduleConfig not found"));
        }

        if (componentName == null) {
            final String actionName = getActionName();
            if (actionName == null) {
                throwException(new JspException("actionName not found"));
            }
            final String mappingName = TagUtils.getInstance()
                    .getActionMappingName(actionName);
            final ActionMapping actionMapping = (ActionMapping) moduleConfig
                    .findActionConfig(mappingName);
            if (actionMapping == null) {
                throwException(new JspException("actionMapping not found"));
            }
            componentName = actionMapping.getPath();
        }

        Action action = null;
        final ActionServlet servlet = (ActionServlet) pageContext
                .getServletContext().getAttribute(Globals.ACTION_SERVLET_KEY);
        final Glue glue = RegistryLocator.getInstance().getGlue();
        try {
            action = glue.getAction(servlet, componentName, methodName);
        } catch (final Exception e) {
            throwException(new JspException("action component(" + componentName
                    + ") not found", e));
        }

        final ActionForm actionForm = (ActionForm) pageContext.getAttribute(
                Constants.BEAN_KEY, PageContext.REQUEST_SCOPE);
        try {
            action.execute(null, actionForm, pageContext.getRequest(),
                    pageContext.getResponse());
        } catch (final Exception e) {
            throwException(new JspException("action component(" + componentName
                    + ") threw Exception", e));
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    protected String getActionName() {
        for (Tag tag = this; tag != null; tag = tag.getParent()) {
            if (FormTag.class.isInstance(tag)) {
                return FormTag.class.cast(tag).getAction();
            }
        }
        return null;
    }

    protected void throwException(final JspException e) throws JspException {
        pageContext.setAttribute(Globals.EXCEPTION_KEY, e,
                PageContext.REQUEST_SCOPE);
        throw e;
    }

}
