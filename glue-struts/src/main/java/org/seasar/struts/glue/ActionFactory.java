package org.seasar.struts.glue;

import org.apache.struts.action.Action;

public interface ActionFactory {

    Action getAction(Object component, String methodName);
}
