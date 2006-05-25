package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public interface PojoInvocation {
    
    void setRequest(HttpServletRequest request);

    HttpServletRequest getRequest();
    
    void setResponse(HttpServletResponse response);

    HttpServletResponse getResponse();
    
    void setActionInterface(Class actionInterface);

    Class getActionInterface();
    
    void setActionInstance(Object actionInstance);

    Object getActionInstance();
    
    void setActionForm(ActionForm form);

    ActionForm getActionForm();
    
    void setActionMapping(ActionMapping mapping);

    ActionMapping getActionMapping();

    String execute();

}
