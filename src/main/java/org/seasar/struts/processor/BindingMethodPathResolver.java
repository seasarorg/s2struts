package org.seasar.struts.processor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.log.Logger;
import org.seasar.struts.config.rule.ZeroConfigActionRule;
import org.seasar.struts.factory.ActionAnnotationHandler;
import org.seasar.struts.factory.ActionAnnotationHandlerFactory;
import org.seasar.struts.util.ModuleConfigUtil;

/**
 * 
 * @author taedium
 */
public class BindingMethodPathResolver implements PathResolver {

    public static final String zeroConfigActionRule_BINDING = "bindingType=may";

    private static final Logger log = Logger.getLogger(BindingMethodPathResolver.class);

    private ZeroConfigActionRule zeroConfigActionRule;

    public void setZeroConfigActionRule(
            ZeroConfigActionRule zeroConfigActionRule) {
        this.zeroConfigActionRule = zeroConfigActionRule;
    }

    public String resolve(HttpServletRequest request, String path) {
        MethodBinding methodBinding = MethodBindingFactory.getMethodBinding(
                request, path);
        if (methodBinding == null) {
            return path;
        }
        Method method = methodBinding.getMethod();
        ActionAnnotationHandler handler = ActionAnnotationHandlerFactory
                .getAnnotationHandler();
        String actualPath = handler.getPath(method);
        if (actualPath == null) {
            if (zeroConfigActionRule == null) {
                actualPath = path;
            } else {
                Class actionClass = methodBinding.getComponentClass();
                ModuleConfig config = ModuleConfigUtil.getModuleConfig();
                actualPath = zeroConfigActionRule.getPath(actionClass, config);
            }
        }
        log.log("DSTR0001", new Object[] { methodBinding.getExpression(),
                actualPath });
        return actualPath;
    }
}
