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
package org.seasar.struts.lessconfig.factory;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.util.ConstantAnnotationUtil;

/**
 * プロパティに指定された定数アノテーション<code>ValidateOrder</code>を使い、{@link PropertyDesc}を比較します。
 * 
 * @author Katsuhiko Nagashima
 */
public class ConstantPropertyDescComparator extends AbstractPropertyDescComparator {

    private static final String ORDER_SUFFIX = "_VALIDATOR_ORDER";

    /**
     * インスタンスを構築します。
     * 
     * @param beanDesc
     */
    public ConstantPropertyDescComparator(BeanDesc beanDesc) {
        super(beanDesc);
    }

    protected int getOrder(PropertyDesc propDesc) {
        String fieldName = propDesc.getPropertyName() + ORDER_SUFFIX;
        if (!this.beanDesc.hasField(fieldName)) {
            return 999;
        }
        if (!ConstantAnnotationUtil.isConstantAnnotationIntField(this.beanDesc.getField(fieldName))) {
            return 999;
        }
        return ((Integer) this.beanDesc.getFieldValue(fieldName, null)).intValue();
    }

}
