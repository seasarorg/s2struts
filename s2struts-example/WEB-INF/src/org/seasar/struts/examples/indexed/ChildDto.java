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

import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class ChildDto {
    
    private String value;

    public String getValue() {
        return value;
    }

    @Required
    @Args(keys = "child", resource = false)
    public void setValue(String value) {
        this.value = value;
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(value).append("]");
        return buf.toString();
    }

}
