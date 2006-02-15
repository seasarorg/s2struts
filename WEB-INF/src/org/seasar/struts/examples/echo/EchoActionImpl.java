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
package org.seasar.struts.examples.echo;

import org.seasar.struts.examples.FowardNameConstants;
import org.seasar.struts.examples.form.StringForm;

/**
 * @author Satoshi Kimura
 */
public class EchoActionImpl implements EchoAction {	
    private StringForm strForm;
    
    public EchoActionImpl() {
    }

    public String echo() {
        strForm.setResult(strForm.getInput());

        return FowardNameConstants.SUCCESS;
    }

    public StringForm getStrForm() {
        return strForm;
    }
    public void setStrForm(StringForm strForm) {
        this.strForm = strForm;
    }
}