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
package org.seasar.struts.examples.mod;

import java.io.Serializable;

/**
 * @author Katsuhiko Nagashima
 */
public class ModDto implements Serializable {
    
    public static final String FORM = "name=modForm";

    private String arg1;

    private String arg2;

    private int result;

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public int getIntArg1() {
        try {
            return Integer.parseInt(arg1);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public int getIntArg2() {
        try {
            return Integer.parseInt(arg2);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}