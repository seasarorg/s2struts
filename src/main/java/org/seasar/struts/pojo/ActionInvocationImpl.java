package org.seasar.struts.pojo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ActionInvocationImpl implements ActionInvocation {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private ActionMapping mapping;

    private ActionForm form;

    private Class actionInterface;

    private Object actionInstance;
    
    private List actionCommands;
    
    private int index = 0;
    
    public ActionInvocationImpl(List actionCommands, ActionMapping mapping, Class actionInterface,
            Object actionInstance, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        
        this.actionCommands = actionCommands;
        this.mapping = mapping;
        this.actionInterface = actionInterface;
        this.actionInstance = actionInstance;
        this.form = form;
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public Class getActionInterface() {
        return this.actionInterface;
    }

    public Object getActionInstance() {
        return this.actionInstance;
    }

    public ActionForm getActionForm() {
        return this.form;
    }

    public ActionMapping getActionMapping() {
        return this.mapping;
    }

    public String execute() {
        if (this.index < this.actionCommands.size()) {
            ActionCommand command = (ActionCommand) actionCommands.get(this.index);
            this.index++;
            return command.execute(this);
        }
        throw new RuntimeException("Action cannot be called.");
    }

}
