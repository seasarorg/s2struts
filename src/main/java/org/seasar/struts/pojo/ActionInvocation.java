package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public interface ActionInvocation {

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    Class getActionInterface();

    Object getActionInstance();

    ActionForm getActionForm();

    ActionMapping getActionMapping();

    String execute();

}
