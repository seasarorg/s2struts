package org.seasar.struts.unit;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.httpunit.S2HttpTestCase;
import org.seasar.struts.unit.mock.MockActionMapping;

/**
 * @author Satoshi Kimura
 */
public abstract class S2StrutsTestCase extends S2HttpTestCase {
    private String actionServletName;
    private RequestProcessorWrapper requestProcessorWrapper;

    protected S2StrutsTestCase(String name) {
        super(name);
    }

    protected S2StrutsTestCase(String name, boolean useS2MockObject) {
        super(name, useS2MockObject);
    }

    protected Action createAction(Class actionClass) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (false == container.hasComponentDef(actionClass)) {
            container.register(actionClass);
        }
        Action action = (Action) container.getComponent(actionClass);
        return action;
    }

    protected String execute(String path) throws Exception {
        setPath(path);

        HttpServletRequest req = getHttpServletRequest();
        HttpServletResponse res = getHttpServletResponse();

        ActionServletWrapper actionServletWrapper = new ActionServletWrapper(getActionServlet());
        RequestProcessor processor = actionServletWrapper.getRequestProcessor(req, res);
        requestProcessorWrapper = new RequestProcessorWrapper(processor);
        requestProcessorWrapper.init(getActionServlet(), actionServletWrapper.getModuleConfig(req));
        requestProcessorWrapper.process(req, res);

        return requestProcessorWrapper.getActionForward().getName();
    }

    protected String execute(Class actionClass) throws Exception {
        return execute(createAction(actionClass), createActionForm(), null);
    }

    protected String execute(Class actionClass, String methodName) throws Exception {
        return execute(createAction(actionClass), createActionForm(), methodName);
    }

    protected String execute(Action action) throws Exception {
        return execute(action, createActionForm(), null);
    }

    protected String execute(Action action, String methodName) throws Exception {
        return execute(action, createActionForm(), methodName);
    }

    protected String execute(Class actionClass, ActionForm form) throws Exception {
        return execute(createAction(actionClass), form, null);
    }

    protected String execute(Class actionClass, ActionForm form, String methodName) throws Exception {
        return execute(createAction(actionClass), form, methodName);
    }

    protected String execute(Action action, ActionForm form) throws Exception {
        return execute(action, form, null);
    }

    protected String execute(Action action, ActionForm form, String methodName) throws Exception {
        ActionMapping mapping = createActionMapping();
        HttpServletRequest request = getHttpServletRequest();

        if (methodName != null) {
            mapping.setParameter(methodName);

            if (request instanceof MockHttpServletRequest) {
                ((MockHttpServletRequest) request).addParameter(methodName, methodName);
            } else if (request instanceof org.seasar.httpunit.mock.MockHttpServletRequest) {
                ((org.seasar.httpunit.mock.MockHttpServletRequest) request).addParameter(methodName, methodName);
            }
        }

        ActionForward forward = action.execute(mapping, form, request, getHttpServletResponse());

        if (forward == null) {
            return null;
        }

        return forward.getName();
    }

    private ActionForm createActionForm() {
        // todo struts-configを読み込んだときクリエストからフォームを作成するかも
        return null; //new ActionForm();
    }

    private ActionMapping createActionMapping() {
        return new MockActionMapping();
    }

    protected void setActionServletName(String actionServletName) {
        this.actionServletName = actionServletName;
    }

    protected ActionServlet getActionServlet() {
        if (actionServletName != null) {
            return getActionServlet(actionServletName);
        } else {
            return (ActionServlet) getHttpServlet(ActionServlet.class);
        }
    }

    protected ActionServlet getActionServlet(String servletName) {
        return (ActionServlet) getHttpServlet(servletName);
    }

    protected ActionMessages getActionErrors() {
        return (ActionMessages) getMockReq().getAttribute(Globals.ERROR_KEY);
    }

    protected ActionMessages getActionMessages() {
        return (ActionMessages) getMockReq().getAttribute(Globals.MESSAGE_KEY);
    }

    protected void assertEquals(ActionMessages expected, ActionMessages actual) {
        assertEquals(null, expected, actual);
    }

    protected void assertEquals(String message, ActionMessages expected, ActionMessages actual) {
        message = (message == null) ? "" : message;
        assertEquals(message + " size", expected.size(), actual.size());

        Iterator expectedMessages = expected.get();
        Iterator actualMessages = actual.get();

        while (actualMessages.hasNext()) {
            ActionMessage expectedMessage = (ActionMessage) expectedMessages.next();
            ActionMessage actualMessage = (ActionMessage) actualMessages.next();
            assertEquals(message + " key", expectedMessage.getKey(), actualMessage.getKey());
            assertEquals(message + " values", expectedMessage.getValues(), actualMessage.getValues());
        }
    }

    protected Action getExecutedAction() {
        return requestProcessorWrapper.getExecutedAction();
    }

    protected ActionForm getExecutedActionForm() {
        return requestProcessorWrapper.getExecutedActionForm();
    }
}