package org.seasar.struts.action.impl;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.action.ComponentNameCreator;

/**
 * @author Satoshi Kimura
 */
public class ComponentNameCreatorImplTest extends S2TestCase {
    private ComponentNameCreator componentNameCreator;

    public ComponentNameCreatorImplTest(String name) {
        super(name);
    }

    protected void setUp() {
        include("s2struts.dicon");
    }

    public void setUpCreateComponentName() {
        include("ComponentNameCreatorImplTest.dicon");
    }

    public void testCreateComponentName() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ActionMapping mapping = new ActionMapping();
        String moduleName = "/module";
        String path = "/path";
        setInfo(mapping, moduleName, path);
        String componentName = componentNameCreator.createComponentName(container, mapping);
        assertEquals("/module/path", componentName);
    }

    public void setUpCreateComponentNameNoModuleInDicon() {
        include("ComponentNameCreatorImplOnlyPathTest.dicon");
    }

    public void testCreateComponentNameNoModuleInDicon() {
        SingletonS2ContainerFactory.init();
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ActionMapping mapping = new ActionMapping();
        String moduleName = "/module";
        String path = "/path";
        setInfo(mapping, moduleName, path);
        container = SingletonS2ContainerFactory.getContainer();
        String componentName = componentNameCreator.createComponentName(container, mapping);
        assertEquals("/path", componentName);
    }

    public void setUpCreateComponentNameOnlyPath() {
        include("ComponentNameCreatorImplTest.dicon");
    }

    public void testCreateComponentNameOnlyPath() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ActionMapping mapping = new ActionMapping();
        String moduleName = "";
        String path = "/path";
        setInfo(mapping, moduleName, path);
        String componentName = new ComponentNameCreatorImpl().createComponentName(container, mapping);
        assertEquals("/path", componentName);
    }

    private void setInfo(ActionMapping mapping, String moduleName, String path) {
        mapping.setModuleConfig(new ModuleConfigImpl(moduleName));
        mapping.setPath(path);
    }

}