package org.seasar.struts.config;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.FieldUtil;
import org.seasar.struts.config.rule.ZeroConfigActionRule;
import org.seasar.struts.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.factory.StrutsConfigAnnotationHandlerFactory;


/**
 * @author Katsuhiko Nagashima
 */
public class AutoActionRegister {
    
    private static final Logger log = Logger.getLogger(AutoActionRegister.class);

    private AutoActionRegister() {
    }

    public static void regist(ServletContext servletContext, ModuleConfig config, Collection classes) {
        StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
        classes = ClassComparator.sort(classes);
        
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Class clazz = (Class) iterator.next();
            StrutsActionConfig strutsAction = annHandler.createStrutsActionConfig(clazz);
            if (strutsAction == null && isActionClass(clazz)) {
                strutsAction = new NullStrutsActionConfig();
            }
            if (strutsAction != null) {
                registAction(strutsAction, clazz, config, servletContext);
            }
        }
    }
    
    private static boolean isActionClass(Class clazz) {
        return clazz.getName().matches(configRule().getActionClassPattern());
    }
    
    private static void registAction(StrutsActionConfig action, Class actionClass, ModuleConfig config,
            ServletContext servletContext) {
        String path = getPath(action, actionClass, config);
        if (!hasActionConfig(config, path)) {
            addActionConfig(servletContext, config, action, actionClass);
        }
    }

    public static boolean hasActionConfig(ModuleConfig config, String path) {
        ActionConfig[] actionConfigs = config.findActionConfigs();
        for (int i = 0; i < actionConfigs.length; ++i) {
            if (path.equals(actionConfigs[i].getPath())) {
                return true;
            }
        }
        return false;
    }

    private static void addActionConfig(ServletContext servletContext, ModuleConfig config, StrutsActionConfig action,
            Class actionClass) {
        ActionConfig actionConfig = new ActionMapping();
        actionConfig.setAttribute(getAttribute(action, actionClass, config));
        actionConfig.setForward(getForward(action, actionClass, config));
        actionConfig.setInclude(getInclude(action, actionClass, config));
        actionConfig.setInput(getInput(action, actionClass, config));
        actionConfig.setModuleConfig(config);
        actionConfig.setName(getName(action, actionClass, config));
        actionConfig.setParameter(getParameter(action, actionClass, config));
        actionConfig.setPath(getPath(action, actionClass, config));
        actionConfig.setPrefix(getPrefix(action, actionClass, config));
        actionConfig.setRoles(getRoles(action, actionClass, config));
        actionConfig.setScope(getScope(action, actionClass, config));
        actionConfig.setSuffix(getSuffix(action, actionClass, config));
        actionConfig.setType(actionClass.getName());
        actionConfig.setUnknown(getUnknown(action, actionClass, config));
        actionConfig.setValidate(getValidate(action, actionClass, config));

        addFowardConfig(servletContext, actionConfig, actionClass);

        config.addActionConfig(actionConfig);

        if (log.isDebugEnabled()) {
            log.debug("auto regist " + actionConfig);
            ForwardConfig[] forwardConfigs = actionConfig.findForwardConfigs();
            for (int i = 0; i < forwardConfigs.length; i++) {
                log.debug("auto regist " + forwardConfigs[i]);
            }
        }
    }

    private static void addFowardConfig(ServletContext servletContext, ActionConfig actionConfig, Class actionClass) {
        StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
        Field[] fields = actionClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            StrutsActionForwardConfig actionForward = annHandler.createStrutsActionForwardConfig(fields[i]);
            if (actionForward != null) {
                fields[i].setAccessible(true);
                String name = FieldUtil.get(fields[i], actionClass).toString();
                ForwardConfig forwardConfig = new ActionForward();
                forwardConfig.setName(name);
                forwardConfig.setPath(actionForward.path());
                forwardConfig.setRedirect(actionForward.redirect());
                actionConfig.addForwardConfig(forwardConfig);
            }
        }
        if (actionClass.getName().matches(configRule().getActionClassPattern())) {
            rule().addFowardConfig(actionClass, actionConfig, servletContext);
        }
    }

    private static AutoStrutsConfigRule configRule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (AutoStrutsConfigRule) container.getComponent(AutoStrutsConfigRule.class);
    }

    private static ZeroConfigActionRule rule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ZeroConfigActionRule) container.getComponent(ZeroConfigActionRule.class);
    }

    private static String getPath(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_PATH.equals(action.path()) ? rule().getPath(actionClass, config) : action.path();
    }

    private static String getAttribute(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_ATTRIBUTE.equals(action.attribute()) ? rule().getAttribute(actionClass, config) : action
                .attribute();
    }

    private static String getForward(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_FORWARD.equals(action.forward()) ? rule().getForward(actionClass, config) : action
                .forward();
    }

    private static String getInclude(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_INCLUDE.equals(action.include()) ? rule().getInclude(actionClass, config) : action
                .include();
    }

    private static String getInput(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_INPUT.equals(action.input()) ? rule().getInput(actionClass, config) : action.input();
    }

    private static String getName(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_NAME.equals(action.name()) ? rule().getName(actionClass, config) : action.name();
    }

    private static String getParameter(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_PARAMETER.equals(action.parameter()) ? rule().getParameter(actionClass, config) : action
                .parameter();
    }

    private static String getPrefix(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_PREFIX.equals(action.prefix()) ? rule().getPrefix(actionClass, config) : action.prefix();
    }

    private static String getRoles(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_ROLES.equals(action.roles()) ? rule().getRoles(actionClass, config) : action.roles();
    }

    private static String getScope(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_SCOPE.equals(action.scope()) ? rule().getScope(actionClass, config) : action.scope();
    }

    private static String getSuffix(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_SUFFIX.equals(action.suffix()) ? rule().getSuffix(actionClass, config) : action.suffix();
    }

    private static boolean getUnknown(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return action.unknown() == StrutsActionConfig.DEFAULT_UNKNOWN ? rule().getUnknown(actionClass, config) : action
                .unknown();
    }

    private static boolean getValidate(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return action.validate() == StrutsActionConfig.DEFAULT_VALIDATE ? rule().getValidate(actionClass, config) : action
                .validate();
    }

}
