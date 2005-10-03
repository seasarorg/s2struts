package org.seasar.struts.config.rule;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.Constants;
import org.seasar.struts.config.AutoStrutsConfigRule;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;

/**
 * @author Satoshi Kimura
 */
public class ZeroConfigActionRuleImpl implements ZeroConfigActionRule {
    private static final Logger logger = Logger.getLogger(ZeroConfigActionRuleImpl.class);

    private AutoStrutsConfigRule configRule;

    public String getPath(Class actionClass, ModuleConfig config) {
        String path = ClassUtil.getShortClassName(actionClass);
        return "/" + CommonNamingRule.decapitalizeName(path).replaceFirst("Action$", "");
    }

    public String getName(Class actionClass, ModuleConfig config) {
        String name = ClassUtil.getShortClassName(actionClass);
        name = CommonNamingRule.decapitalizeName(name).replaceFirst("Action$", "");
        String formName = name + "Form";
        String dtoName = name + "Dto";
        if (config.findFormBeanConfig(formName) != null) {
            return formName;
        } else if (config.findFormBeanConfig(dtoName) != null) {
            return dtoName;
        } else if (config.findFormBeanConfig(name) != null) {
            return name;
        }
        return StrutsActionConfig.DEFAULT_NAME;
    }

    public String getScope(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_SCOPE;
    }

    public boolean getValidate(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_VALIDATE;
    }

    public String getInput(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_INPUT;
    }

    public String getParameter(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_PARAMETER;
    }

    public String getAttribute(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_ATTRIBUTE;
    }

    public String getForward(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_FORWARD;
    }

    public String getInclude(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_INCLUDE;
    }

    public String getPrefix(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_PREFIX;
    }

    public String getSuffix(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_SUFFIX;
    }

    public boolean getUnknown(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_UNKNOWN;
    }

    public String getRoles(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_ROLES;
    }

    public void addFowardConfig(Class actionClass, ActionConfig actionConfig, ServletContext servletContext) {
        ForwardConfig forwardConfig = actionConfig.findForwardConfig(Constants.SUCCESS);
        if (forwardConfig != null) {
            return;
        }

        String path = null;
        String[] viewExtention = this.configRule.getViewExtention();
        for (int i = 0; i < viewExtention.length; i++) {
            String file = getPath(actionClass, null) + "." + viewExtention[i];
            path = this.configRule.getDocRoot() + file;
            String packageDir = "/" + actionClass.getPackage().getName().replace('.', '/');

            path = getExistFilePath(this.configRule.getDocRoot(), packageDir, file, actionConfig, servletContext);
            if (path != null) {
                break;
            }
        }

        addFowardConfig(path, actionConfig);
    }

    private void addFowardConfig(String path, ActionConfig actionConfig) {
        ForwardConfig forwardConfig = new ActionForward();
        forwardConfig.setName(Constants.SUCCESS);
        forwardConfig.setPath(path);
        forwardConfig.setRedirect(StrutsActionForwardConfig.DEFALUT_REDIRECT);
        actionConfig.addForwardConfig(forwardConfig);
    }

    private String getExistFilePath(String docRoot, String packageDir, String file, ActionConfig actionConfig,
            ServletContext servletContext) {
        String path = docRoot + packageDir + file;
        if (new File(servletContext.getRealPath(path)).exists()) {
            return path;
        } else if (StringUtil.isEmpty(packageDir)) {
            if (isLastExtention(file) && actionConfig.findForwardConfigs().length == 0) {
                String message = "View file was not found." + new File(path).getAbsolutePath();
                logger.info(message);
                //throw new IllegalStateException("View file was not found." + new File(path).getAbsolutePath());
            }
            return null;
        } else {
            int index = packageDir.indexOf("/", 1);
            packageDir = packageDir.substring(index + 1);
            if (index < 0) {
                packageDir = "";
            } else {
                packageDir = "/" + packageDir;
            }
            return getExistFilePath(docRoot, packageDir, file, actionConfig, servletContext);
        }
    }

    private boolean isLastExtention(String file) {
        return file.endsWith(this.configRule.getViewExtention()[this.configRule.getViewExtention().length - 1]);
    }

    public void setAutoStrutsConfigPattern(AutoStrutsConfigRule configRule) {
        this.configRule = configRule;
    }
}
