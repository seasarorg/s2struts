package org.seasar.struts.glue.exception;

import org.apache.struts.action.ActionMapping;

public class ActionComponentNotFoundException extends GlueException {

    protected ActionMapping mapping;

    public ActionComponentNotFoundException(ActionMapping mapping) {
        super("Action component not found. {0}", mapping);
        this.mapping = mapping;
    }

    public ActionMapping getMapping() {
        return mapping;
    }

}
