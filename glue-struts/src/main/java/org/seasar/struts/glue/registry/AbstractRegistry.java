package org.seasar.struts.glue.registry;

import org.seasar.struts.glue.Glue;
import org.seasar.struts.glue.Registry;

public abstract class AbstractRegistry implements Registry {

    protected String glueName = "glue";

    public AbstractRegistry() {
    }

    public void setGlueName(final String glueName) {
        this.glueName = glueName;
    }

    public Glue getGlue() {
        return getComponent(glueName);
    }

}
