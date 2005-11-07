package org.seasar.struts.plugin;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.seasar.struts.S2StrutsInitializer;

/**
 * @author Satoshi Kimura
 */
public class RegistActionClassPlugIn implements PlugIn {

    public RegistActionClassPlugIn() {
    }

    /**
     * empty.
     * 
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    public void destroy() {
    }

    /**
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
     *      org.apache.struts.config.ModuleConfig)
     * @see S2StrutsInitializer#registActionClass(ActionServlet, ModuleConfig)
     */
    public void init(ActionServlet servlet, ModuleConfig config) {
        S2StrutsInitializer.registActionClass(servlet, config);
    }

}