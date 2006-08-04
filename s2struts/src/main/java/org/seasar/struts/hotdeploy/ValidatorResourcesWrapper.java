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
package org.seasar.struts.hotdeploy;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.validator.Form;
import org.apache.commons.validator.FormSet;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResources;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ValidatorResourcesWrapper extends ValidatorResources {

    private static final long serialVersionUID = 23638277930244549L;

    private ValidatorResources resources;

    public void init(ValidatorResources resources) {
        this.resources = resources;
    }

    public void addConstant(String arg0, String arg1) {
        resources.addConstant(arg0, arg1);
    }

    public void addFormSet(FormSet arg0) {
        resources.addFormSet(arg0);
    }

    public void addValidatorAction(ValidatorAction arg0) {
        resources.addValidatorAction(arg0);
    }

    public boolean equals(Object arg0) {
        return resources.equals(arg0);
    }

    public Form getForm(Locale arg0, String arg1) {
        return resources.getForm(arg0, arg1);
    }

    public Form getForm(String arg0, String arg1, String arg2, String arg3) {
        return resources.getForm(arg0, arg1, arg2, arg3);
    }

    public ValidatorAction getValidatorAction(String arg0) {
        return resources.getValidatorAction(arg0);
    }

    public Map getValidatorActions() {
        return resources.getValidatorActions();
    }

    public int hashCode() {
        return resources.hashCode();
    }

    public void process() {
        resources.process();
    }

    public String toString() {
        return resources.toString();
    }

}
