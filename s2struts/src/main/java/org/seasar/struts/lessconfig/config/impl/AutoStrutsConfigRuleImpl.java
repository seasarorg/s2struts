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
package org.seasar.struts.lessconfig.config.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.seasar.struts.lessconfig.config.AutoStrutsConfigRule;

/**
 * {@link AutoStrutsConfigRule}の実装クラスです。
 * 
 * @author Satoshi Kimura
 */
public class AutoStrutsConfigRuleImpl implements AutoStrutsConfigRule {
    private String docRoot = "";

    private String actionClassPattern = ".*Action$";

    private String formClassPattern = "(.*Form$)|(.*Dto$)";

    private String[] viewExtension = { "jsp", "html" };

    public String getActionClassPattern() {
        return this.actionClassPattern;
    }

    public void setActionClassPattern(String actionClassPattern) {
        this.actionClassPattern = actionClassPattern;
    }

    public String getFormClassPattern() {
        return this.formClassPattern;
    }

    public void setFormClassPattern(String formClassPattern) {
        this.formClassPattern = formClassPattern;
    }

    public String getDocRoot() {
        return this.docRoot;
    }

    public void setDocRoot(String docRoot) {
        this.docRoot = docRoot.replaceFirst("/$", "");
    }

    public String[] getViewExtension() {
        return this.viewExtension;
    }

    public void setViewExtension(String viewExtension) {
        StringTokenizer tokenizer = new StringTokenizer(viewExtension, ",");
        List list = new ArrayList();
        while (tokenizer.hasMoreElements()) {
            list.add(tokenizer.nextElement().toString().trim());
        }
        this.viewExtension = (String[]) list.toArray(new String[list.size()]);
    }
}
