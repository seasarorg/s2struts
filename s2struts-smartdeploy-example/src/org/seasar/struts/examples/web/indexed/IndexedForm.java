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
package org.seasar.struts.examples.web.indexed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.struts.annotation.tiger.StrutsActionForm;
import org.seasar.struts.validator.annotation.tiger.Required;

/**
 * @author taedium
 * 
 */
@StrutsActionForm
public class IndexedForm {

    private IndexedChildForm[] children = { new IndexedChildForm(), new IndexedChildForm(),
            new IndexedChildForm() };

    private String[] array = new String[3];

    private List<String> list = Arrays.asList("", "", "");

    private Map<String, String> map = new HashMap<String, String>();

    public IndexedChildForm[] getChildren() {
        return children;
    }

    public void setChildren(IndexedChildForm[] children) {
        this.children = children;
    }

    public String[] getArray() {
        return array;
    }

    @Required
    public void setArray(String[] array) {
        this.array = array;
    }

    public List<String> getList() {
        return list;
    }

    @Required
    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    @Required
    public void setMap(Map<String, String> map) {
        this.map = map;
    }

}
