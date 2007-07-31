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
package org.seasar.struts.util;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class MethodBindingUtil {

    private MethodBindingUtil() {
    }

    public static String getComponentName(String expression) {
        if (expression == null) {
            return null;
        }
        int index = expression.indexOf('.');
        if (index > 0) {
            return expression.substring(2, index);
        } else {
            throw new IllegalArgumentException("component was not found. arg: "
                    + expression);
        }
    }

    public static String getMethodName(String expression) {
        if (expression == null) {
            return null;
        }
        int index = expression.indexOf('.');
        if (index > 0) {
            return expression.substring(index + 1, expression.length() - 1);
        } else {
            throw new IllegalArgumentException("method was not found. arg: "
                    + expression);
        }
    }

}
