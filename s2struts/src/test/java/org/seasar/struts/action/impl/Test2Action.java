package org.seasar.struts.action.impl;

import org.apache.struts.action.Action;

/**
 * @author skimura
 */
public class Test2Action extends Action implements TestServiceMarker {
    private boolean executedInitMethod = false;

    private boolean executedDestroyMethod = false;

    public Test2Action() {
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
}