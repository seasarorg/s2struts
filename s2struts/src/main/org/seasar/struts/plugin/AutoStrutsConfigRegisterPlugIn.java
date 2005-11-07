package org.seasar.struts.plugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.ValidatorPlugIn;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.config.AutoActionFormRegister;
import org.seasar.struts.config.AutoActionRegister;
import org.seasar.struts.config.AutoStrutsConfigRule;
import org.seasar.struts.config.AutoValidationRegister;
import org.seasar.struts.util.ClassFinder;
import org.seasar.struts.util.ClassFinderImpl;

/**
 * Example.
 * <p>
 * 
 * <plug-in className="org.seasar.struts.plugin.AutoStrutsConfigRegisterPlugIn">
 *   <set-property property="enableJar" value="false"/>
 *   <set-property property="actionClassPattern" value="foo.bar.action.*Action"/>
 *   <set-property property="formClassPattern" value="foo.bar.form.*Form"/>
 *   <set-property property="docRoot" value="/WEB-INF/jsp"/>
 *   <set-property property="viewExtension" value="jsp,html,view"/>
 * </plug-in>
 * 
 * @author Satoshi Kimura
 */
public class AutoStrutsConfigRegisterPlugIn implements PlugIn {

    private ClassFinder classFinder = new ClassFinderImpl();

    private boolean enableJar;

    public AutoStrutsConfigRegisterPlugIn() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    public void destroy() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
     *      org.apache.struts.config.ModuleConfig)
     */
    public void init(ActionServlet actionServlet, ModuleConfig config) throws ServletException {
        try {
            this.classFinder.find(isEnableJar());

            if (actionServlet != null) {
                this.classFinder.find(actionServlet, isEnableJar());
            }

            regist(actionServlet, config);
        } finally {
            this.classFinder.destroy();
        }
    }

    private void regist(ActionServlet actionServlet, ModuleConfig config) {
        AutoActionFormRegister.regist(config, this.classFinder.getClassCollection());
        AutoActionRegister.regist(actionServlet.getServletContext(), config, this.classFinder.getClassCollection());

        ValidatorResources resources = getValidatorResources(actionServlet, config);
        AutoValidationRegister.regist(resources, config, this.classFinder.getClassCollection());
    }

    private ValidatorResources getValidatorResources(ActionServlet actionServlet, ModuleConfig config) {
        ServletContext servletContext = actionServlet.getServletContext();
        String key = ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix();
        return (ValidatorResources) servletContext.getAttribute(key);
    }

    public boolean isEnableJar() {
        return this.enableJar;
    }

    public void setEnableJar(boolean enableJar) {
        this.enableJar = enableJar;
    }

    public void setActionClassPattern(String actionClassPattern) {
        configRule().setActionClassPattern(actionClassPattern);
    }

    public void setFormClassPattern(String formClassPattern) {
        configRule().setFormClassPattern(formClassPattern);
    }

    public void setDocRoot(String docRoot) {
        configRule().setDocRoot(docRoot);
    }

    public void setViewExtension(String viewExtension) {
        configRule().setViewExtension(viewExtension);
    }

    private static AutoStrutsConfigRule configRule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (AutoStrutsConfigRule) container.getComponent(AutoStrutsConfigRule.class);
    }

}