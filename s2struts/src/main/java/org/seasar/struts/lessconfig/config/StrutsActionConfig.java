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

import org.apache.struts.config.ActionConfig;
import org.seasar.struts.Constants;

/**
 * 無設定Strutsで{@link ActionConfig}に関する設定情報を扱うインタフェースです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public interface StrutsActionConfig {

    /** {@link #path()}のデフォルト値 */
    String DEFAULT_PATH = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setPath(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setPath(String)}に設定すべき値
     */
    String path();

    /** {@link #name()}のデフォルト値 */
    String DEFAULT_NAME = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setName(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setName(String)}に設定すべき値
     */
    String name();

    /** {@link #scope()}のデフォルト値 */
    String DEFAULT_SCOPE = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setScope(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setScope(String)}に設定すべき値
     */
    String scope();

    /** {@link #validate()}のデフォルト値 */
    Boolean DEFAULT_VALIDATE = null;

    /**
     * {@link ActionConfig#setValidate(boolean)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setValidate(boolean)}に設定すべき値
     */
    Boolean validate();

    /** {@link #input()}のデフォルト値 */
    String DEFAULT_INPUT = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setInput(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setInput(String)}に設定すべき値
     */
    String input();

    /** {@link #parameter()}のデフォルト値 */
    String DEFAULT_PARAMETER = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setParameter(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setParameter(String)}に設定すべき値
     */
    String parameter();

    /** {@link #attribute()}のデフォルト値 */
    String DEFAULT_ATTRIBUTE = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setAttribute(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setAttribute(String)}に設定すべき値
     */
    String attribute();

    /** {@link #forward()}のデフォルト値 */
    String DEFAULT_FORWARD = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setForward(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setForward(String)}に設定すべき値
     */
    String forward();

    /** {@link #include()}のデフォルト値 */
    String DEFAULT_INCLUDE = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setInclude(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setInclude(String)}に設定すべき値
     */
    String include();

    /** {@link #prefix()}のデフォルト値 */
    String DEFAULT_PREFIX = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setPrefix(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setPrefix(String)}に設定すべき値
     */
    String prefix();

    /** {@link #suffix()}のデフォルト値 */
    String DEFAULT_SUFFIX = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setSuffix(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setSuffix(String)}に設定すべき値
     */
    String suffix();

    /** {@link #unknown()}のデフォルト値 */
    Boolean DEFAULT_UNKNOWN = null;

    /**
     * {@link ActionConfig#setUnknown(boolean)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setUnknown(boolean)}に設定すべき値
     */
    Boolean unknown();

    /** {@link #roles()}のデフォルト値 */
    String DEFAULT_ROLES = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setRoles(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setRoles(String)}に設定すべき値
     */
    String roles();

    /** {@link #cancellable()}のデフォルト値 */
    Boolean DEFAULT_CANCELLABLE = null;

    /**
     * {@link ActionConfig#setCancellable(boolean)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setCancellable(boolean)}に設定すべき値
     */
    Boolean cancellable();

    /** {@link #catalog()}のデフォルト値 */
    String DEFAULT_CATALOG = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setCatalog(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setCatalog(String)}に設定すべき値
     */
    String catalog();

    /** {@link #command()}のデフォルト値 */
    String DEFAULT_COMMAND = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setCommand(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setCommand(String)}に設定すべき値
     */
    String command();

    /** {@link #inherit()}のデフォルト値 */
    String DEFAULT_INHERIT = Constants.UNDEFINED;

    /**
     * {@link ActionConfig#setExtends(String)}に設定すべき値を返します。
     * 
     * @return {@link ActionConfig#setExtends(String)}に設定すべき値
     */
    String inherit();

}
