package org.seasar.struts.glue;

public interface Registry {

    boolean hasComponent(String componentName);

    <T> T getComponent(String componentName);

    Glue getGlue();

}
