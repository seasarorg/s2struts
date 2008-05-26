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
package org.seasar.struts.examples.web.iterate;

import org.seasar.struts.annotation.tiger.StrutsAction;

/**
 * @author taedium
 * 
 */
@StrutsAction(input = AbstractIterateAction.ITERATE)
public class IterateAction extends AbstractIterateAction {

    private String name;

    private int index;

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public String submit(int index) {
        name = "submit";
        this.index = index;
        return RESULT;
    }

    public String image(int index) {
        name = "image";
        this.index = index;
        return RESULT;
    }

    public String link(int index) {
        name = "link";
        this.index = index;
        return RESULT;
    }
}
