package org.seasar.struts;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.action.ComponentNameCreator;
import org.seasar.struts.action.ComponentNameCreatorImpl;

/**
 * @author Satoshi Kimura
 */
public class S2StrutsInitializerTest extends S2TestCase {

    public S2StrutsInitializerTest(String name) {
        super(name);
    }

    protected void setUp() {
    }

    public void setUpInitAlreadyRegist() {
        SingletonS2ContainerFactory.getContainer().register(ComponentNameCreatorImpl.class);
    }

    public void testInitDidNotRegist() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        try {
            container.getComponent(ComponentNameCreator.class);
            fail();
        } catch (ComponentNotFoundRuntimeException e) {
            // success
        }

        S2StrutsInitializer.init();

        assertTrue(container.getComponent(ComponentNameCreator.class) instanceof ComponentNameCreatorImpl);

    }
    public void testInitAlreadyRegist() {
        S2Container container = SingletonS2ContainerFactory.getContainer();

        assertTrue(container.getComponent(ComponentNameCreator.class) instanceof ComponentNameCreatorImpl);

        S2StrutsInitializer.init();

        assertTrue(container.getComponent(ComponentNameCreator.class) instanceof ComponentNameCreatorImpl);
    }
}