/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 * @author nakamura-to
 *
 */
public class SuppressPropertyUtilsBeanTest extends TestCase {

    public void testGetProperty() throws Exception {
        List classes = new ArrayList();
        classes.add(FullName.class);
        PropertyUtilsBean propertyUtils = new SuppressPropertyUtilsBean(classes);
        Person person = new Person();
        person.getFullName().setFirstName("aaa");
        person.getFullName().setLastName("bbb");
        person.getAddress().setStreet("ccc");
        person.setAge("20");
        try {
            propertyUtils.getProperty(person, "fullName.firstName");
        } catch (NoSuchMethodException expected) {
        }
        try {
            propertyUtils.getProperty(person, "fullName.lastName");
        } catch (NoSuchMethodException expected) {
        }
        assertEquals("ccc", propertyUtils.getProperty(person, "address.street"));
        assertEquals("20", propertyUtils.getProperty(person, "age"));
    }

    public void testDescribe() throws Exception {
        List classes = new ArrayList();
        classes.add(FullName.class);
        PropertyUtilsBean propertyUtils = new SuppressPropertyUtilsBean(classes);
        Person person = new Person();
        Map properties = propertyUtils.describe(person);
        assertEquals(3, properties.size());
        assertTrue(properties.containsKey("address"));
        assertTrue(properties.containsKey("age"));
        assertTrue(properties.containsKey("fullName"));
    }

    public void testBeanUtilsBean_default_populate() throws Exception {
        BeanUtilsBean beanUtils = new BeanUtilsBean();
        Person person = new Person();
        Map properties = new HashMap();
        properties.put("fullName.firstName", "aaa");
        properties.put("fullName.lastName", "bbb");
        properties.put("address.street", "ccc");
        properties.put("age", "20");
        beanUtils.populate(person, properties);
        assertEquals("aaa", person.getFullName().getFirstName());
        assertEquals("bbb", person.getFullName().getLastName());
        assertEquals("ccc", person.getAddress().getStreet());
        assertEquals("20", person.getAge());
    }

    public void testBeanUtilsBean_suppressed_populate() throws Exception {
        List classes = new ArrayList();
        classes.add(FullName.class);
        BeanUtilsBean beanUtils = new BeanUtilsBean(new ConvertUtilsBean(),
                new SuppressPropertyUtilsBean(classes));
        Person person = new Person();
        Map properties = new HashMap();
        properties.put("fullName.firstName", "aaa");
        properties.put("fullName.lastName", "bbb");
        properties.put("address.street", "ccc");
        properties.put("age", "20");
        beanUtils.populate(person, properties);
        assertNull(person.getFullName().getFirstName());
        assertNull(person.getFullName().getLastName());
        assertEquals("ccc", person.getAddress().getStreet());
        assertEquals("20", person.getAge());
    }

    public class Person {
        private FullName fullName = new FullName();

        private Address address = new Address();

        private String age;

        public FullName getFullName() {
            return fullName;
        }

        public void setFullName(FullName fullName) {
            this.fullName = fullName;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    public static class FullName {
        private String firstName;

        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public static class Address {
        private String street;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }
}
