package org.seasar.struts.action;

import org.apache.struts.action.Action;

/**
 * @author skimura
 */
public class Test3Action extends Action {
    private TestServiceMarker service;

    private boolean executedInitMethod = false;

    private boolean executedDestroyMethod = false;

    public Test3Action(TestServiceMarker service) {
        this.service = service;
    }

    public void initMethod() {
        executedInitMethod = true;
    }

    public void destroyMethod() {
        executedDestroyMethod = true;
    }

    public boolean isExecutedDestroyMethod() {
        return executedDestroyMethod;
    }

    public boolean isExecutedInitMethod() {
        return executedInitMethod;
    }

    public boolean hasService() {
        return (service != null);
    }
}