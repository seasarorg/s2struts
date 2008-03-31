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

/**
 * 無設定Strutsで自動で設定情報を作成する際のルールを表すインタフェースです。
 * 
 * @author Satoshi Kimura
 */
public interface AutoStrutsConfigRule {

    /**
     * 無設定S2StrutsのActionを特定するためのクラス名パターンを返します。
     * 
     * @return Actionクラス名のパターン
     */
    String getActionClassPattern();

    /**
     * 無設定S2StrutsのActionを特定するためのクラス名パターンを設定します。
     * <p>
     * パターンは正規表現で設定します。
     * </p>
     * 
     * @param actionClassPattern
     *            Actionクラス名のパターン
     */
    void setActionClassPattern(String actionClassPattern);

    /**
     * 無設定S2StrutsのActionFormを特定するためのクラス名パターンを返します。
     * 
     * @return ActionFormのクラス名パターン
     */
    String getFormClassPattern();

    /**
     * 無設定S2StrutsのActionFormを特定するためのクラス名パターンを設定します。
     * <p>
     * パターンは正規表現で設定します。
     * </p>
     * 
     * @param formClassPattern
     *            ActionFormクラス名のパターン
     */
    void setFormClassPattern(String formClassPattern);

    /**
     * Viewテンプレートとなるファイルの置き場所のトップディレクトリを返します。
     * 
     * @return Viewテンプレートとなるファイルの置き場所のトップディレクトリ
     */
    String getDocRoot();

    /**
     * Viewテンプレートとなるファイルの置き場所のトップディレクトリを設定します。
     * 
     * @param docRoot
     *            Viewテンプレートとなるファイルの置き場所のトップディレクトリ
     */
    void setDocRoot(String docRoot);

    /**
     * Viewテンプレートとなるファイルの拡張子の配列を返します。
     * 
     * @return Viewテンプレートとなるファイルの拡張子の配列
     */
    String[] getViewExtension();

    /**
     * Viewテンプレートとなるファイルの拡張子を指定します。
     * <p>
     * 複数の拡張子を認める場合は、複数拡張子をカンマで区切った文字列として設定します。
     * </p>
     * 
     * @param viewExtension
     *            Viewテンプレートとなるファイルの拡張子のカンマ区切り文字列
     */
    void setViewExtension(String viewExtension);
}
