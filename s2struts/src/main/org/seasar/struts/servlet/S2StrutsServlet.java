package org.seasar.struts.servlet;

import org.seasar.framework.container.servlet.S2ContainerServlet;
import org.seasar.struts.S2StrutsInitializer;

/**
 * This servelet performs required processing for S2 and Struts cooperates.
 * 
 * @author Satoshi Kimura
 */
public class S2StrutsServlet extends S2ContainerServlet {

    private static final long serialVersionUID = S2StrutsServlet.class.hashCode();

    /**
     * Create a S2Container instance , and initialize for S2Strus.
     * 
     * @see S2ContainerServlet#init()
     * @see S2StrutsInitializer#init()
     */
    public void init() {
        super.init();
        S2StrutsInitializer.init();
    }
}