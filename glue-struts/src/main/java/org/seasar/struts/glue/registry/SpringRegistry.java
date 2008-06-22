package org.seasar.struts.glue.registry;

import org.springframework.beans.factory.BeanFactory;

public class SpringRegistry extends AbstractRegistry {

    protected BeanFactory beanFactory;

    public SpringRegistry() {
    }

    public boolean hasComponent(final String componentName) {
        return beanFactory.containsBean(componentName);
    }

    public <T> T getComponent(final String componentName) {
        return (T) beanFactory.getBean(componentName);
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(final BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
