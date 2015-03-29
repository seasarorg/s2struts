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
package org.seasar.struts.bean;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.FormPropertyConfig;
import org.apache.struts.validator.BeanValidatorForm;
import org.apache.struts.validator.DynaValidatorForm;
import org.apache.struts.validator.ValidatorForm;

/**
 * @author nakamura-to
 *
 */
public class FixPageBeanUtilsBeanTest extends TestCase {

    private BeanUtilsBean beanUtilsBean;

    private ValidatorForm validatorForm;

    private BeanValidatorForm beanValidatorForm;

    private DynaValidatorForm dynaValidatorForm;

    protected void setUp() throws Exception {
        super.setUp();

        beanUtilsBean = new FixPageBeanUtilsBean(new ConvertUtilsBean(), new SuppressPropertyUtilsBean());

        {
            FormBeanConfig config = new FormBeanConfig();
            config.setType(ValidatorForm.class.getName());
            validatorForm = (ValidatorForm) config.createActionForm(new ActionServlet());
        }

        {
            FormBeanConfig config = new FormBeanConfig();
            config.setType(MyForm.class.getName());
            beanValidatorForm = (BeanValidatorForm) config.createActionForm(new ActionServlet());
        }

        {
            FormBeanConfig config = new FormBeanConfig();
            config.setType(DynaValidatorForm.class.getName());
            config.addFormPropertyConfig(new FormPropertyConfig("page", int.class.getName(), "0"));
            dynaValidatorForm = (DynaValidatorForm) config.createActionForm(new ActionServlet());
        }
    }

    public void testValidatorForm_pageIsNone() throws Exception {
        Map properties = new HashMap();
        beanUtilsBean.populate(validatorForm, properties);

        assertEquals(0, validatorForm.getPage());
    }

    public void testValidatorForm_pageIsMinus() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(-1));
        beanUtilsBean.populate(validatorForm, properties);

        assertEquals(Integer.MAX_VALUE, validatorForm.getPage());
    }

    public void testValidatorForm_pageIsZero() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(0));
        beanUtilsBean.populate(validatorForm, properties);

        assertEquals(0, validatorForm.getPage());
    }

    public void testValidatorForm_pageIsPlus() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(0));
        beanUtilsBean.populate(validatorForm, properties);

        assertEquals(0, validatorForm.getPage());
    }

    public void testBeanValidatorForm_pageIsNone() throws Exception {
        Map properties = new HashMap();
        beanUtilsBean.populate(beanValidatorForm, properties);

        assertEquals(0, beanValidatorForm.getPage());
    }

    public void testBeanValidatorForm_pageIsMinus() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(-1));
        beanUtilsBean.populate(beanValidatorForm, properties);

        assertEquals(Integer.MAX_VALUE, beanValidatorForm.getPage());
    }

    public void testBeanValidatorForm_pageIsZero() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(0));
        beanUtilsBean.populate(beanValidatorForm, properties);

        assertEquals(0, beanValidatorForm.getPage());
    }

    public void testBeanValidatorForm_pageIsPlus() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(1));
        beanUtilsBean.populate(beanValidatorForm, properties);

        assertEquals(1, beanValidatorForm.getPage());
    }

    public void testDynaValidatorForm_pageIsNone() throws Exception {
        Map properties = new HashMap();
        beanUtilsBean.populate(dynaValidatorForm, properties);

        assertEquals(new Integer(0), dynaValidatorForm.getMap().get("page"));
    }

    public void testDynaValidatorForm_pageIsMinus() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(-1));
        beanUtilsBean.populate(dynaValidatorForm, properties);

        assertEquals(new Integer(Integer.MAX_VALUE), dynaValidatorForm.getMap().get("page"));
    }

    public void testDynaValidatorForm_pageIsZero() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(0));
        beanUtilsBean.populate(dynaValidatorForm, properties);

        assertEquals(new Integer(0), dynaValidatorForm.getMap().get("page"));
    }

    public void testDynaValidatorForm_pageIsPlus() throws Exception {
        Map properties = new HashMap();
        properties.put("page", new Integer(1));
        beanUtilsBean.populate(dynaValidatorForm, properties);
        assertEquals(new Integer(1), dynaValidatorForm.getMap().get("page"));
    }

    public static class MyForm {

        private int page;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }
}
