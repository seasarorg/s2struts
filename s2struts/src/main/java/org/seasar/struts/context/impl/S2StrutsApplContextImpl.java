/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.struts.context.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.struts.context.ContentsType;
import org.seasar.struts.context.S2StrutsApplContext;

/**
 * {@link S2StrutsApplContext}の実装クラスです。
 * 
 * @author Satoshi Kimura
 */
public class S2StrutsApplContextImpl implements S2StrutsApplContext {
    private static final long serialVersionUID = -4835702530138078142L;

    private Map methodBindingExpressions = new HashMap();

    private Map cancelActions = new HashMap();

    public synchronized void clear(ContentsType type) {
        if (type == ContentsType.MethodBindingExpression) {
            this.methodBindingExpressions = new HashMap();
        } else if (type == ContentsType.CancelAction) {
            this.cancelActions = new HashMap();
        }
    }

    public String getMethodBindingExpression(String mappingName, String key, String value) {
        return (String) this.methodBindingExpressions.get(mappingName + key + value);
    }

    public void setMethodBindingExpression(String mappingName, String key, String value, String methodBindingExpression) {
        this.methodBindingExpressions.put(mappingName + key + value, methodBindingExpression);
    }

    public Boolean isCancelAction(String mappingName, String key, String value) {
        return (Boolean) this.cancelActions.get(mappingName + key + value);
    }

    public void setCancelAction(String mappingName, String key, String value) {
        this.cancelActions.put(mappingName + key + value, Boolean.TRUE);
    }

}
