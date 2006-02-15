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
package org.seasar.struts.examples.indexed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class IndexedDto {

    private List<ChildDto> children = new ArrayList<ChildDto>();

    private String[] array = new String[3];

    private Map map = new HashMap();

    private List<String> values = new ArrayList<String>();

    public ChildDto[] getChildren() {
        return children.toArray(new ChildDto[children.size()]);
    }
    
    public void setChildren(ChildDto[] children) {
        this.children = Arrays.asList(children);
    }

    public ChildDto getChild(int index) {
        while (children.size() < index + 1) {
            children.add(new ChildDto());
        }
        return children.get(index);
    }

    public String[] getArray() {
        return array;
    }

    @Required
    @Args(keys = "array", resource = false)
    public void setArray(String[] array) {
        this.array = array;
    }

    public Map getMap() {
        return map;
    }

    public String[] getValues() {
        return values.toArray(new String[values.size()]);
    }

    @Required
    @Args(keys = "list", resource = false)
    public void setValues(String[] values) {
        this.values = Arrays.asList(values);
    }

    public String getValues(int index) {
        while (values.size() < index + 1) {
            values.add("");
        }
        return values.get(index);
    }

    public void setValues(int index, String value) {
        while (values.size() < index + 1) {
            values.add("");
        }
        values.remove(index);
        values.add(index, value);
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(children).append(",");
        buf.append(array).append(",");
        buf.append(map).append(",");
        buf.append(values).append("]");
        return buf.toString();
    }

}
