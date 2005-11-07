package org.seasar.struts.config.rule;

import javax.servlet.ServletContext;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;

/**
 * @author Satoshi Kimura
 */
public interface ZeroConfigActionRule {
    String getPath(Class actionClass, ModuleConfig config);

    String getName(Class actionClass, ModuleConfig config);

    String getScope(Class actionClass, ModuleConfig config);

    boolean getValidate(Class actionClass, ModuleConfig config);

    String getInput(Class actionClass, ModuleConfig config);

    String getParameter(Class actionClass, ModuleConfig config);

    String getAttribute(Class actionClass, ModuleConfig config);

    String getForward(Class actionClass, ModuleConfig config);

    String getInclude(Class actionClass, ModuleConfig config);

    String getPrefix(Class actionClass, ModuleConfig config);

    String getSuffix(Class actionClass, ModuleConfig config);

    boolean getUnknown(Class actionClass, ModuleConfig config);

    String getRoles(Class actionClass, ModuleConfig config);

    void addFowardConfig(Class actionClass, ActionConfig actionConfig, ServletContext servletContext);

}
