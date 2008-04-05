/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.struts.lessconfig.autoregister.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.validator.Form;
import org.apache.commons.validator.FormSet;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.validator.ValidatorPlugIn;

/**
 * 複数の{@link ValidatorResources}を扱います。
 * <p>
 * {@link ValidatorPlugIn}で作成される{@link ValidatorResources}と、S2Strutsの自動登録機能で作成される{@link ValidatorResources}を扱います。
 * </p>
 * 
 * @author Katsuhiko Nagashima
 */
public class CompositeValidatorResources extends ValidatorResources {

    private static final long serialVersionUID = -2473704615510071543L;

    /**
     * インスタンスを構築します。
     */
    public CompositeValidatorResources() {
    }

    private List resourcesList = new ArrayList();

    /**
     * {@link ValidatorResources}を追加します。
     * 
     * @param resources
     */
    public void addResources(ValidatorResources resources) {
        this.resourcesList.add(resources);
    }

    public Form getForm(Locale locale, String formKey) {
        Form result = null;
        for (Iterator it = this.resourcesList.iterator(); it.hasNext();) {
            ValidatorResources resources = (ValidatorResources) it.next();
            result = resources.getForm(locale, formKey);
            if (result != null) {
                return result;
            }
        }
        return result;
    }

    public Form getForm(String language, String country, String variant, String formKey) {
        Form result = null;
        for (Iterator it = this.resourcesList.iterator(); it.hasNext();) {
            ValidatorResources resources = (ValidatorResources) it.next();
            result = resources.getForm(language, country, variant, formKey);
            if (result != null) {
                return result;
            }
        }
        return result;
    }

    public ValidatorAction getValidatorAction(String key) {
        ValidatorAction result = null;
        for (Iterator it = this.resourcesList.iterator(); it.hasNext();) {
            ValidatorResources resources = (ValidatorResources) it.next();
            result = resources.getValidatorAction(key);
            if (result != null) {
                return result;
            }
        }
        return result;
    }

    public Map getValidatorActions() {
        Map result = null;
        for (Iterator it = this.resourcesList.iterator(); it.hasNext();) {
            ValidatorResources resources = (ValidatorResources) it.next();
            result = resources.getValidatorActions();
            if (result != null) {
                return result;
            }
        }
        return result;
    }

    //
    //
    //

    /**
     * {@link ValidatorResources}を設定します。
     */
    public void init(ValidatorResources resources) {
        throw new UnsupportedOperationException();
    }

    public void addConstant(String arg0, String arg1) {
        throw new UnsupportedOperationException();
    }

    public void addFormSet(FormSet arg0) {
        throw new UnsupportedOperationException();
    }

    public void addValidatorAction(ValidatorAction arg0) {
        throw new UnsupportedOperationException();
    }

    public void process() {
        throw new UnsupportedOperationException();
    }

}
