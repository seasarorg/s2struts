package org.seasar.struts.action;

import org.apache.struts.action.Action;

/**
 * @author Satoshi Kimura
 */
public class Test1Action extends Action {
    private TestServiceMarker service;

    private boolean executedInitMethod = false;

    private boolean executedDestroyMethod = false;

    public Test1Action() {
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

    public void setTestServiceMarker(TestServiceMarker service) {
        this.service = service;
    }

    public boolean hasService() {
        return (service != null);
    }
}
