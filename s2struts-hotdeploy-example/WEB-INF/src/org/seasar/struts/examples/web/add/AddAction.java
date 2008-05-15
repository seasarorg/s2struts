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
package org.seasar.struts.examples.web.add;

import org.seasar.struts.annotation.tiger.ScopeType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author taedium
 * 
 */
@StrutsAction(scope = ScopeType.REQUEST)
public class AddAction {

    @StrutsActionForward(path = Paths.ADD)
    public static String ADD = "add";

    private AddForm addForm;

    public AddForm getAddForm() {
        return addForm;
    }

    public void setAddForm(AddForm addForm) {
        this.addForm = addForm;
    }

    public String calculate() {
        int result = Integer.valueOf(addForm.getArg1())
                + Integer.valueOf(addForm.getArg2());
        addForm.setResult(String.valueOf(result));
        return ADD;
    }
}
