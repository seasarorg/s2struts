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
package org.seasar.struts.zeroconfig.config;


/**
 * @author Satoshi Kimura
 */
public class NullStrutsActionConfig implements StrutsActionConfig {
    public String path() {
        return DEFAULT_PATH;
    }

    public String name() {
        return DEFAULT_NAME;
    }

    public String scope() {
        return DEFAULT_SCOPE;
    }

    public boolean validate() {
        return DEFAULT_VALIDATE;
    }

    public String input() {
        return DEFAULT_INPUT;
    }

    public String parameter() {
        return DEFAULT_PARAMETER;
    }

    public String attribute() {
        return DEFAULT_ATTRIBUTE;
    }

    public String forward() {
        return DEFAULT_FORWARD;
    }

    public String include() {
        return DEFAULT_INCLUDE;
    }

    public String prefix() {
        return DEFAULT_PREFIX;
    }

    public String suffix() {
        return DEFAULT_SUFFIX;
    }

    public boolean unknown() {
        return DEFAULT_UNKNOWN;
    }

    public String roles() {
        return DEFAULT_ROLES;
    }
    
    public boolean cancellable() {
    	return DEFAULT_CANCELLABLE;
    }
}
