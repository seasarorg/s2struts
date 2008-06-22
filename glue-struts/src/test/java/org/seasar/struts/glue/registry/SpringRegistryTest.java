package org.seasar.struts.glue.registry;

import junit.framework.TestCase;

import org.seasar.struts.glue.ActionFactory;
import org.seasar.struts.glue.Glue;
import org.seasar.struts.glue.MethodNameExtracter;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class SpringRegistryTest extends TestCase {

    public void testHasComponent() {
        final XmlBeanFactory beanFactory = new XmlBeanFactory(
                new ClassPathResource("glue-struts.xml"));
        final SpringRegistry registry = new SpringRegistry();
        registry.setBeanFactory(beanFactory);
        assertTrue(registry.hasComponent("glue"));
        assertTrue(registry.hasComponent("actionFactory"));
        assertTrue(registry.hasComponent("methodNameExtracter"));
    }

    public void testGetComponent() {
        final XmlBeanFactory beanFactory = new XmlBeanFactory(
                new ClassPathResource("glue-struts.xml"));
        final SpringRegistry registry = new SpringRegistry();
        registry.setBeanFactory(beanFactory);
        final Glue glue = registry.getComponent("glue");
        assertNotNull(glue);
        final ActionFactory actionFactory = registry
                .getComponent("actionFactory");
        assertNotNull(actionFactory);
        final MethodNameExtracter methodNameExtracter = registry
                .getComponent("methodNameExtracter");
        assertNotNull(methodNameExtracter);
    }

}
