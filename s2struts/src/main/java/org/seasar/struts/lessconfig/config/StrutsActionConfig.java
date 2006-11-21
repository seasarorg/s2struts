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
package org.seasar.struts.lessconfig.config;

import org.seasar.struts.Constants;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public interface StrutsActionConfig {
    String DEFAULT_PATH = Constants.UNDEFINED;
    String path();

    String DEFAULT_NAME = Constants.UNDEFINED;
    String name();

    String DEFAULT_SCOPE = Constants.UNDEFINED;
    String scope();

    Boolean DEFAULT_VALIDATE = null;
    Boolean validate();

    String DEFAULT_INPUT = Constants.UNDEFINED;
    String input();

    String DEFAULT_PARAMETER = Constants.UNDEFINED;
    String parameter();

    String DEFAULT_ATTRIBUTE = Constants.UNDEFINED;
    String attribute();

    String DEFAULT_FORWARD = Constants.UNDEFINED;
    String forward();

    String DEFAULT_INCLUDE = Constants.UNDEFINED;
    String include();

    String DEFAULT_PREFIX = Constants.UNDEFINED;
    String prefix();

    String DEFAULT_SUFFIX = Constants.UNDEFINED;
    String suffix();

    Boolean DEFAULT_UNKNOWN = null;
    Boolean unknown();

    String DEFAULT_ROLES = Constants.UNDEFINED;
    String roles();
    
    Boolean DEFAULT_CANCELLABLE = null;
    Boolean cancellable();

}
