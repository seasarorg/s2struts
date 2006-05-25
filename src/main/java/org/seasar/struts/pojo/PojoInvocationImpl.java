package org.seasar.struts.pojo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PojoInvocationImpl implements PojoInvocation {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private ActionMapping mapping;

    private ActionForm form;

    private Class actionInterface;

    private Object actionInstance;
    
    private List pojoCommands;
    
    private int index = 0;
    
    public PojoInvocationImpl(List pojoCommands, ActionMapping mapping, Class actionInterface,
            Object actionInstance, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        
        this.pojoCommands = pojoCommands;
        this.mapping = mapping;
        this.actionInterface = actionInterface;
        this.actionInstance = actionInstance;
        this.form = form;
        this.request = request;
        this.response = response;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public void setActionInterface(Class actionInterface) {
        this.actionInterface = actionInterface;
    }

    public Class getActionInterface() {
        return this.actionInterface;
    }

    public void setActionInstance(Object actionInstance) {
        this.actionInstance = actionInstance;
    }

    public Object getActionInstance() {
        return this.actionInstance;
    }
    
    public void setActionForm(ActionForm form) {
        this.form = form;
    }

    public ActionForm getActionForm() {
        return this.form;
    }
    
    public void setActionMapping(ActionMapping mapping) {
        this.mapping = mapping;
    }

    public ActionMapping getActionMapping() {
        return this.mapping;
    }

    public String execute() {
        if (this.index < this.pojoCommands.size()) {
            PojoCommand command = (PojoCommand) pojoCommands.get(this.index);
            this.index++;
            return command.execute(this);
        }
        throw new RuntimeException("Action cannot be called.");
    }

}
